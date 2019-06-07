package com.thekingelessar.minecraftmagic.common.spell.conjuration.conjurefang;

import com.thekingelessar.minecraftmagic.MinecraftMagic;
import com.thekingelessar.minecraftmagic.client.renderer.ExtendedRange;
import com.thekingelessar.minecraftmagic.common.network.MinecraftMagicPacketHandler;
import com.thekingelessar.minecraftmagic.common.network.packets.PacketSummonEvokerFang;
import com.thekingelessar.minecraftmagic.common.spell.Castable;
import com.thekingelessar.minecraftmagic.common.spell.target.TargetBlock;
import com.thekingelessar.minecraftmagic.logging.LogColors;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.RayTraceFluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.logging.Level;

import static com.thekingelessar.minecraftmagic.MinecraftMagic.logger;

@OnlyIn(Dist.CLIENT)
public class SpellConjureFang extends Castable {

    private final static float range = 20F;
    private float pitch = 0F;
    private float yaw = 0F;

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
                EnumFacing blockSideHit = extendedRange.blockSideHit;

                switch (blockSideHit) {
                    case UP:
                        logger.log(Level.INFO, LogColors.LOG_INFO + "Hit UP" + pitch + LogColors.RESET);
                        break;
                    case DOWN:
                        this.pitch = 180F;
                        logger.log(Level.INFO, LogColors.LOG_INFO + "Hit DOWN" + pitch + LogColors.RESET);
                        break;
                    case EAST:
                        this.yaw = -90F;
                        this.pitch = 90F;
                        logger.log(Level.INFO, LogColors.LOG_INFO + "Hit EAST" + pitch + LogColors.RESET);
                        break;
                    case WEST:
                        this.yaw = 90F;
                        this.pitch = 90F;
                        logger.log(Level.INFO, LogColors.LOG_INFO + "Hit WEST" + pitch + LogColors.RESET);
                        break;
                    case NORTH:
                        this.pitch = 90F;
                        logger.log(Level.INFO, LogColors.LOG_INFO + "Hit NORTH" + pitch + LogColors.RESET);
                        break;
                    case SOUTH:
                        this.pitch = -90;
                        logger.log(Level.INFO, LogColors.LOG_INFO + "Hit SOUTH" + pitch + LogColors.RESET);
                        break;
                }

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
            MinecraftMagicPacketHandler.INSTANCE.sendToServer(new PacketSummonEvokerFang(target.x, target.y, target.z, this.pitch, this.yaw));
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
