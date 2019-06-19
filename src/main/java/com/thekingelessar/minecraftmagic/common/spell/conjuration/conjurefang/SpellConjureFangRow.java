package com.thekingelessar.minecraftmagic.common.spell.conjuration.conjurefang;

import com.thekingelessar.minecraftmagic.client.renderer.ExtendedRange;
import com.thekingelessar.minecraftmagic.common.network.MinecraftMagicPacketHandler;
import com.thekingelessar.minecraftmagic.common.network.packets.PacketConjureFangRow;
import com.thekingelessar.minecraftmagic.common.spell.Castable;
import com.thekingelessar.minecraftmagic.common.spell.target.TargetBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.*;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SpellConjureFangRow extends Castable
{
    
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
                
                Vec3d lookingAt = result.hitVec;
                
                Double blockX = lookingAt.x;
                Double blockY = lookingAt.y;
                Double blockZ = lookingAt.z;
                
                this.target = new TargetBlock(blockX, blockY, blockZ, null);
            }
        }
        else
        {
            RayTraceResult result = extendedRange.mcObjectMouseOver;
            
            if (result != null)
            {
                
                Vec3d lookingAt = result.hitVec;
                
                Double blockX = lookingAt.x;
                Double blockY = lookingAt.y;
                Double blockZ = lookingAt.z;
                
                BlockPos originalBlockPos = new BlockPos(blockX, blockY, blockZ);
                
                BlockPos newBlockPos = originalBlockPos.down();
                IBlockState ibs;
                
                while (true)
                {
                    
                    ibs = caster.world.getBlockState(newBlockPos);
                    if (!(ibs.isAir(caster.world, newBlockPos)))
                    {
                        blockY = (Double) (double) newBlockPos.getY();
                        break;
                    }
                    newBlockPos = newBlockPos.down();
                    double distance = Math.sqrt(newBlockPos.distanceSq(new BlockPos(caster.posX, caster.posY, caster.posZ)));
                    
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
