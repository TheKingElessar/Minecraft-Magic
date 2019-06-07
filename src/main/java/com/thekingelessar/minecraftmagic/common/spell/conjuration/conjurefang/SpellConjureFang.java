package com.thekingelessar.minecraftmagic.common.spell.conjuration;

import com.thekingelessar.minecraftmagic.MinecraftMagic;
import com.thekingelessar.minecraftmagic.client.renderer.ExtendedRange;
import com.thekingelessar.minecraftmagic.common.network.MinecraftMagicPacketHandler;
import com.thekingelessar.minecraftmagic.common.network.packets.PacketSummonEvokerFang;
import com.thekingelessar.minecraftmagic.common.spell.Castable;
import com.thekingelessar.minecraftmagic.common.spell.target.TargetBlock;
import com.thekingelessar.minecraftmagic.logging.LogColors;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.RayTraceFluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.logging.Level;

@OnlyIn(Dist.CLIENT)
public class SpellConjureFang extends Castable {

    private final static float range = 20F;

    public SpellConjureFang(Entity caster)
    {
        this.caster = caster;

        ExtendedRange extendedRange = new ExtendedRange(null, range, RayTraceFluidMode.NEVER, Minecraft.getInstance(), caster);
        extendedRange.getMouseOver();
        if(!extendedRange.airTargeted)
        {
            RayTraceResult result = extendedRange.mcObjectMouseOver;

            Double blockX;
            Double blockY;
            Double blockZ;

            if (result != null)
            {
                Vec3d lookingAt = result.hitVec;

                blockX = lookingAt.x;
                blockY = lookingAt.y;
                blockZ = lookingAt.z;

                this.target = new TargetBlock(blockX, blockY, blockZ);
            }
        } else {
            MinecraftMagic.logger.log(Level.INFO, LogColors.LOG_INFO + "Spell failed; nothing targeted" + LogColors.RESET);
        }

    }

    @Override
    public void cast()
    {
        TargetBlock target = (TargetBlock) this.target;
        if(target != null)
        {
            MinecraftMagicPacketHandler.INSTANCE.sendToServer(new PacketSummonEvokerFang(target.x, target.y, target.z));
        }

           /*     Entity lookingAtEntity = result.entity;

                if(lookingAtEntity != null) {
                    UUID entityID = lookingAtEntity.getUniqueID();
                    String stringEntityID = entityID.toString();
//this part is used for glowing
                    MinecraftMagicPacketHandler.INSTANCE.sendToServer(new PacketGlowSingleEntity(stringEntityID));
                }
*/
    }

}
