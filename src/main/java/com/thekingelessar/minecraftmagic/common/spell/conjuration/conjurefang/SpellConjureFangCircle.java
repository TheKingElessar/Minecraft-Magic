package com.thekingelessar.minecraftmagic.common.spell.conjuration.conjurefang;

import com.thekingelessar.minecraftmagic.MinecraftMagic;
import com.thekingelessar.minecraftmagic.client.renderer.ExtendedRange;
import com.thekingelessar.minecraftmagic.common.entity.EntityEvokerFangsRotatable;
import com.thekingelessar.minecraftmagic.common.network.MinecraftMagicPacketHandler;
import com.thekingelessar.minecraftmagic.common.network.packets.PacketConjureFang;
import com.thekingelessar.minecraftmagic.common.spell.Castable;
import com.thekingelessar.minecraftmagic.common.spell.target.TargetBlock;
import com.thekingelessar.minecraftmagic.logging.LogColors;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.RayTraceFluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.logging.Level;

public class SpellConjureFang extends Castable
{
    
    private final static float range = 20F;
    private String blockSide;
    
    @OnlyIn (Dist.CLIENT)
    public SpellConjureFang(Entity caster)
    {
        this.caster = caster;
        
        ExtendedRange extendedRange = new ExtendedRange(null, range, RayTraceFluidMode.NEVER, Minecraft.getInstance(), caster);
        extendedRange.getMouseOver();
        if (!extendedRange.airTargeted)
        {
            RayTraceResult result = extendedRange.mcObjectMouseOver;
            
            Double blockX;
            Double blockY;
            Double blockZ;
            
            if (result != null)
            {
                Entity hitEntity = result.entity;
                
                if (hitEntity != null)
                {
                    blockX = hitEntity.posX;
                    blockY = hitEntity.posY;
                    blockZ = hitEntity.posZ;
                    
                }
                else
                {
                    EnumFacing blockSideHit = extendedRange.blockSideHit;
                    
                    switch (blockSideHit)
                    {
                        case UP:
                            this.blockSide = "MM-UP";
                            break;
                        case DOWN:
                            this.blockSide = "MM-DOWN";
                            break;
                        case EAST:
                            this.blockSide = "MM-EAST";
                            break;
                        case WEST:
                            this.blockSide = "MM-WEST";
                            break;
                        case NORTH:
                            this.blockSide = "MM-NORTH";
                            break;
                        case SOUTH:
                            this.blockSide = "MM-SOUTH";
                            break;
                        
                    }
                    
                    Vec3d lookingAt = result.hitVec;
                    
                    blockX = lookingAt.x;
                    blockY = lookingAt.y;
                    blockZ = lookingAt.z;
                }
                
                this.target = new TargetBlock(blockX, blockY, blockZ, blockSide);
                
            }
        }
        else
        {
            MinecraftMagic.logger.log(Level.INFO, LogColors.LOG_INFO + "Spell failed; nothing targeted" + LogColors.RESET);
        }
    }
    
    @OnlyIn (Dist.CLIENT)
    @Override
    public void castClient()
    {
        TargetBlock target = (TargetBlock) this.target;
        if (target != null)
        {
            MinecraftMagicPacketHandler.INSTANCE.sendToServer(new PacketConjureFang(target.x, target.y, target.z, this.blockSide));
        }
    }
    
    public static void castServer(Entity caster, TargetBlock target)
    {
        
        World casterWorld = caster.world;
        
        EntityEvokerFangsRotatable toSpawn = new EntityEvokerFangsRotatable(casterWorld);
        toSpawn.setPosition(target.x, target.y, target.z);
        
        if (target.blockSide.equals("MM-UP"))
        {
            toSpawn.rotationYaw = caster.rotationYaw + 90;
        }
        else if (target.blockSide.equals("MM-DOWN"))
        {
            toSpawn.rotationYaw = -1 * (caster.rotationYaw - 90);
        }
        
        toSpawn.blockSide = target.blockSide;
        casterWorld.spawnEntity(toSpawn);
        
    }
}
