package com.thekingelessar.minecraftmagic.common.spell.spells.conjuration;

import com.thekingelessar.minecraftmagic.client.renderer.ExtendedRange;
import com.thekingelessar.minecraftmagic.common.network.MinecraftMagicPacketHandler;
import com.thekingelessar.minecraftmagic.common.network.packets.spells.conjuration.PacketConjureFangRow;
import com.thekingelessar.minecraftmagic.common.spell.util.Castable;
import com.thekingelessar.minecraftmagic.common.spell.util.target.targets.TargetBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceFluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SpellConjureFangRow extends Castable
{
    // todo: update to use TargetDirection
    private final static float range = 10F;
    
    @OnlyIn (Dist.CLIENT)
    public SpellConjureFangRow(Entity caster)
    {
        this.caster = caster;
        
        ExtendedRange extendedRange = new ExtendedRange(null, range, RayTraceFluidMode.NEVER, Minecraft.getInstance(), caster);
        extendedRange.getMouseOver();
        
        if (!extendedRange.airTargeted)
        {
            RayTraceResult result = extendedRange.mcObjectMouseOver;
            
            if (result != null)
            {
                int blockX = result.getBlockPos().getX();
                int blockY = result.getBlockPos().getY();
                int blockZ = result.getBlockPos().getZ();
                
                this.target = new TargetBlock(blockX, blockY, blockZ, null);
            }
        }
        else
        {
            RayTraceResult result = extendedRange.mcObjectMouseOver;
            
            if (result != null)
            {
                BlockPos originalBlockPos = result.getBlockPos();
                int blockX = result.getBlockPos().getX();
                int blockY = result.getBlockPos().getY();
                int blockZ = result.getBlockPos().getZ();
                
                BlockPos newBlockPos = originalBlockPos.down();
                IBlockState ibs;
                double distance;
                
                while (true)
                {
                    ibs = caster.world.getBlockState(newBlockPos);
                    if (!(ibs.isAir(caster.world, newBlockPos)))
                    {
                        blockY = newBlockPos.getY();
                        break;
                    }
                    newBlockPos = newBlockPos.down();
                    distance = Math.sqrt(newBlockPos.distanceSq(new BlockPos(caster.posX, caster.posY, caster.posZ)));
                    
                    if (distance > range) break;
                    
                }
                
                this.target = new TargetBlock(blockX, blockY, blockZ, null);
            }
        }
    }
    
    @Override
    public void castClient()
    {
        TargetBlock target = (TargetBlock) this.target;
        if (target != null)
        {
            MinecraftMagicPacketHandler.INSTANCE.sendToServer(new PacketConjureFangRow(target.x, target.y, target.z));
        }
    }
    
    public static void castServer(Entity caster, TargetBlock target)
    {
        double targetX = target.x;
        double targetY = target.y;
        double targetZ = target.z;
        
        double d0 = Math.min(targetY, caster.posY);
        double d1 = Math.max(targetY, caster.posY) + 1.0D;
        float f = (float) MathHelper.atan2(targetZ - caster.posZ, targetX - caster.posX); // returns the polar angle of the coordinate in the params
        
        double maxFangsRough = 10D / 1.25D; // 10 is the range, 1.25 is the distance between each fang
        int maxFangs = (int) Math.ceil(maxFangsRough); // 8
        
        for (int l = 0; l < maxFangs; ++l)
        {
            double d2 = 1.25D * (double) (l + 1);
            //   int j = l;
            int j = l;
            SpellConjureFangRow.spawnFangs(caster.posX + (double) MathHelper.cos(f) * d2, caster.posZ + (double) MathHelper.sin(f) * d2, d0, d1, f, j, caster);
        }
        
    }
    
    //todo convert to targetdirection
    private static void spawnFangs(double targetX, double targetZ, double p_190876_5_, double targetY, float fangYaw, int warmupDelayTicks, Entity caster)
    {
        BlockPos blockpos = new BlockPos(targetX, targetY, targetZ);
        boolean flag = false;
        double d0 = 0.0D;
        
        while (true)
        {
            if (!caster.world.isTopSolid(blockpos) && caster.world.isTopSolid(blockpos.down()))
            {
                if (!caster.world.isAirBlock(blockpos))
                {
                    IBlockState iblockstate = caster.world.getBlockState(blockpos);
                    VoxelShape voxelshape = iblockstate.getCollisionShape(caster.world, blockpos);
                    if (!voxelshape.isEmpty())
                    {
                        d0 = voxelshape.getEnd(EnumFacing.Axis.Y);
                    }
                }
                
                flag = true;
                break;
            }
            
            blockpos = blockpos.down();
            if (blockpos.getY() < MathHelper.floor(p_190876_5_) - 1)
            {
                break;
            }
        }
        //todo: use null caster or don't, update single to match
        if (flag)
        {
            if (caster instanceof EntityLivingBase)
            {
                EntityLivingBase livingCaster = (EntityLivingBase) caster;
                EntityEvokerFangs entityevokerfangs = new EntityEvokerFangs(caster.world, targetX, (double) blockpos.getY() + d0, targetZ, fangYaw, warmupDelayTicks, livingCaster);
                caster.world.spawnEntity(entityevokerfangs);
            }
            else
            {
                EntityEvokerFangs entityevokerfangs = new EntityEvokerFangs(caster.world, targetX, (double) blockpos.getY() + d0, targetZ, fangYaw, warmupDelayTicks, null);
                caster.world.spawnEntity(entityevokerfangs);
            }
        }
        
    }
}
