package com.thekingelessar.minecraftmagic.common.spell.conjuration.conjurefang;

import com.thekingelessar.minecraftmagic.common.network.MinecraftMagicPacketHandler;
import com.thekingelessar.minecraftmagic.common.network.packets.PacketConjureFangCircle;
import com.thekingelessar.minecraftmagic.common.spell.Castable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SpellConjureFangCircle extends Castable
{
    
    @OnlyIn (Dist.CLIENT)
    public SpellConjureFangCircle(Entity caster)
    {
        this.caster = caster;
        
    }
    
    @OnlyIn (Dist.CLIENT)
    @Override
    public void castClient()
    {
        MinecraftMagicPacketHandler.INSTANCE.sendToServer(new PacketConjureFangCircle());
    }
    
    public static void castServer(Entity caster)
    {
        
        double targetX = caster.posX;
        double targetZ = caster.posZ;
        
        double d0 = caster.posY; //Math.min(targetY, caster.posY);
        double d1 = caster.posY; // Math.max(targetY, caster.posY) + 1.0D;
        float f = (float) MathHelper.atan2(targetZ - caster.posZ, targetX - caster.posX); // returns the polar angle of the coordinate in the params
        
        for (int i = 0; i < 5; ++i)
        {
            float f1 = f + (float) i * (float) Math.PI * 0.4F;
            SpellConjureFangCircle.spawnFangs(caster.posX + (double) MathHelper.cos(f1) * 1.5D, caster.posZ + (double) MathHelper.sin(f1) * 1.5D, d0, d1, f1, 0, caster);
        }
        
        for (int k = 0; k < 8; ++k)
        {
            float f2 = f + (float) k * (float) Math.PI * 2.0F / 8.0F + 1.2566371F;
            SpellConjureFangCircle.spawnFangs(caster.posX + (double) MathHelper.cos(f2) * 2.5D, caster.posZ + (double) MathHelper.sin(f2) * 2.5D, d0, d1, f2, 3, caster);
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
