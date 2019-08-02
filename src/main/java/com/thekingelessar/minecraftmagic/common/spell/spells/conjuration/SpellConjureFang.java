package com.thekingelessar.minecraftmagic.common.spell.spells.conjuration;

import com.thekingelessar.minecraftmagic.common.entity.spells.conjuration.EntityEvokerFangsRotatable;
import com.thekingelessar.minecraftmagic.common.network.MinecraftMagicPacketHandler;
import com.thekingelessar.minecraftmagic.common.network.packets.spells.conjuration.PacketConjureFang;
import com.thekingelessar.minecraftmagic.common.spell.util.Castable;
import com.thekingelessar.minecraftmagic.common.spell.util.target.FindTarget;
import com.thekingelessar.minecraftmagic.common.spell.util.target.targets.TargetBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SpellConjureFang extends Castable
{
    // todo: make TargetEntity and use either that or TargetBlock depending on what's targeted
    private final static float range = 20F;
    private EnumFacing blockSide;
    
    @OnlyIn (Dist.CLIENT)
    public SpellConjureFang(Entity caster)
    {
        this.caster = caster;
        TargetBlock target = FindTarget.targetBlock(caster.world, caster, range, true);
        this.target = target;
    }
    
    @OnlyIn (Dist.CLIENT)
    @Override
    public void castClient()
    {
        TargetBlock target = (TargetBlock) this.target;
        if (target != null)
        {
            MinecraftMagicPacketHandler.INSTANCE.sendToServer(new PacketConjureFang(target.x, target.y, target.z, this.blockSide.name()));
        }
    }
    
    public static void castServer(Entity caster, TargetBlock target)
    {
        World casterWorld = caster.world;
        
        EntityEvokerFangsRotatable toSpawn = new EntityEvokerFangsRotatable(casterWorld);
        toSpawn.setPosition(target.x, target.y, target.z);
        
        if (target.blockSide.equals(EnumFacing.UP))
        {
            toSpawn.rotationYaw = caster.rotationYaw + 90;
        }
        else if (target.blockSide.equals(EnumFacing.DOWN))
        {
            toSpawn.rotationYaw = -1 * (caster.rotationYaw - 90);
        }
        
        toSpawn.blockSide = target.blockSide;
        casterWorld.spawnEntity(toSpawn);
        
    }
}
