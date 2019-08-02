package com.thekingelessar.minecraftmagic.common.spell.spells.evocation;

import com.thekingelessar.minecraftmagic.common.entity.spells.evocation.EntityFirebolt;
import com.thekingelessar.minecraftmagic.common.network.MinecraftMagicPacketHandler;
import com.thekingelessar.minecraftmagic.common.network.packets.spells.evocation.PacketFirebolt;
import com.thekingelessar.minecraftmagic.common.spell.util.Castable;
import com.thekingelessar.minecraftmagic.common.spell.util.target.targets.TargetDirection;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SpellFirebolt extends Castable
{
    
    private final static float range = 20F;
    
    @OnlyIn (Dist.CLIENT)
    public SpellFirebolt(Entity caster)
    {
        this.caster = caster;
        this.target = new TargetDirection((Double) (double) this.caster.rotationYaw, (Double) (double) this.caster.rotationPitch);
    }
    
    @OnlyIn (Dist.CLIENT)
    @Override
    public void castClient()
    {
        TargetDirection target = (TargetDirection) this.target;
        if (target != null)
        {
            MinecraftMagicPacketHandler.INSTANCE.sendToServer(new PacketFirebolt(target.yaw, target.pitch));
        }
    }
    
    public static void castServer(Entity caster, TargetDirection target)
    {
        World casterWorld = caster.world;
        EntityFirebolt toSpawn = new EntityFirebolt(casterWorld);
        
        double distanceMoving = 2;
        double pitch = target.pitch;
        double yaw = target.yaw;
        
        double motionX = distanceMoving * Math.cos(Math.toRadians(pitch)) * Math.sin(Math.toRadians(-1 * yaw));
        double motionY = distanceMoving * Math.sin(Math.toRadians(180 + pitch));
        double motionZ = distanceMoving * Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));
        
        double newHeight = caster.posY + (double) caster.getEyeHeight() + motionY;
        
        toSpawn.setPositionAndRotation(caster.posX + motionX, newHeight, caster.posZ + motionZ, (float) target.yaw, (float) target.pitch);
        
        casterWorld.spawnEntity(toSpawn);
        
    }
    
}
