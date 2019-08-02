package com.thekingelessar.minecraftmagic.common.spell.util.target.targets;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class TargetDirectionGround extends TargetDirection
{
    public TargetDirectionGround(Entity caster, int targetX, int targetY, int targetZ, double range, @Nullable Double frequency)
    {
        super(caster, targetX, targetY, targetZ, frequency);
        
        BlockPos originalBlockPos= new BlockPos(targetX, targetY, targetZ);
        IBlockState blockState = caster.world.getBlockState(originalBlockPos);
        boolean isAir = blockState.isAir(caster.world, originalBlockPos);
        
        if (isAir)
        {
            int blockX = originalBlockPos.getX();
            int blockY = originalBlockPos.getY();
            int blockZ = originalBlockPos.getZ();
            
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
            
            this.x = newBlockPos.getX();
        }
    }
    
    //todo: move stuff from fangrow to here
}
