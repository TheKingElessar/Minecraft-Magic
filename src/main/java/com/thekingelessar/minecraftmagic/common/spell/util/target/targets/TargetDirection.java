package com.thekingelessar.minecraftmagic.common.spell.util.target.targets;

import com.thekingelessar.minecraftmagic.common.spell.util.target.FindTarget;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class TargetDirection extends TargetBlock
{
    public double yaw;
    public double pitch;
    public ArrayList<? extends Vec3i> blocks;
    
    public TargetDirection(Entity caster, int targetX, int targetY, int targetZ, @Nullable Double frequency)
    {
        super(targetZ, targetZ, targetZ, null);
        
        Vec3d targetPos = new Vec3d(targetX, targetY, targetZ);
        Vec3d casterPos = new Vec3d(caster.posX, caster.posY, caster.posZ);
        
        if (frequency == null)
        {
            this.blocks = FindTarget.Util.getBlocksInBetween(targetPos, casterPos);
        }
        else
        {
            this.blocks = FindTarget.Util.getCoordsInBetween(targetPos, casterPos, frequency);
        }
        
        this.yaw = (double) caster.rotationYaw;
        
        this.pitch = (double) caster.rotationPitch;
    }
}
