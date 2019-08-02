package com.thekingelessar.minecraftmagic.common.spell.util.target;

import com.thekingelessar.minecraftmagic.client.renderer.ExtendedRange;
import com.thekingelessar.minecraftmagic.common.spell.util.target.targets.TargetBlock;
import com.thekingelessar.minecraftmagic.common.spell.util.target.targets.TargetDirectionGround;
import com.thekingelessar.minecraftmagic.common.util.Coordinate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceFluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;

public class FindTarget
{
    public static TargetBlock targetBlock(World world, Entity caster, double range, boolean canTargetEntity)
    {
        if (world.isRemote())
        {
            ExtendedRange extendedRange = new ExtendedRange(null, range, RayTraceFluidMode.NEVER, Minecraft.getInstance(), caster);
            extendedRange.getMouseOver();
            
            if (canTargetEntity)
            {
                Entity hitEntity = extendedRange.mcObjectMouseOver.entity;
                return new TargetBlock((int) hitEntity.posX, (int) hitEntity.posY, (int) hitEntity.posZ, extendedRange.blockSideHit.name());
            }
            
            else
            {
                BlockPos blockHit = extendedRange.blockHit;
                if (!extendedRange.airTargeted)
                {
                    return new TargetBlock(blockHit.getX(), blockHit.getY(), blockHit.getZ(), extendedRange.blockSideHit.name());
                }
                else
                {
                    return null;
                }
            }
        }
        else
        {
            return null;
        }
    }
    
    public static TargetDirectionGround targetDirectionGround(Entity caster, double range)
    {
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
                
                return new TargetDirectionGround((double) caster.rotationYaw, (double) caster.rotationPitch, blockX, blockY, blockZ);
            }
        }
        else
        {
            RayTraceResult result = extendedRange.mcObjectMouseOver;
            
            if (result != null)
            {
                BlockPos originalBlockPos = result.getBlockPos();
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
                
                return new TargetDirectionGround((double) caster.rotationYaw, (double) caster.rotationPitch, blockX, blockY, blockZ);
            }
        }
        return null;
    }
    
    public static class Util
    {
        static ArrayList<Double> getAnglesToBlock(Vec3d target, Vec3d start)
        {
            double targetX = target.x;
            double targetY = target.y;
            double targetZ = target.z;
            
            double dirx = start.x - targetX;
            double diry = start.y - targetY;
            double dirz = start.z - targetZ;
            
            double len = Math.sqrt(dirx * dirx + diry * diry + dirz * dirz);
            
            dirx /= len;
            diry /= len;
            dirz /= len;
            
            double pitch = Math.asin(diry);
            double yaw = Math.atan2(dirz, dirx);
            
            pitch = pitch * 180.0 / Math.PI;
            yaw = yaw * 180.0 / Math.PI;
            
            yaw += 90f;
            
            ArrayList<Double> angles = new ArrayList<>();
            angles.add(pitch);
            angles.add(yaw);
            
            return angles;
        }
        
        public static ArrayList<BlockPos> getBlocksInBetween(Vec3d target, Vec3d start)
        {
            ArrayList<Double> angles = Util.getAnglesToBlock(target, start);
            double pitch = angles.get(0);
            double yaw = angles.get(1);
            double startingPosX = start.x;
            double startingPosY = start.y;
            double startingPosZ = start.z;
            
            ArrayList<BlockPos> blocks = new ArrayList<>();
            double posX;
            double posY;
            double posZ;
            BlockPos newPos;
            
            double range = start.distanceTo(target);
            for (double blocksStepped = 0; blocksStepped < range; blocksStepped += 0.1)
            {
                posX = blocksStepped * Math.cos(Math.toRadians(pitch)) * Math.sin(Math.toRadians(-1 * yaw));
                posY = blocksStepped * Math.sin(Math.toRadians(180 + pitch));
                posZ = blocksStepped * Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));
                
                posX += startingPosX;
                posY += startingPosY;
                posZ += startingPosZ;
                
                newPos = new BlockPos(posX, posY, posZ);
                
                for (BlockPos blockPos : blocks)
                {
                    if (blockPos.equals(newPos))
                    {
                        blocks.remove(blockPos);
                    }
                }
                
                blocks.add(newPos);
            }
            
            return blocks;
        }
        
        public static ArrayList<Coordinate> getCoordsInBetween(Vec3d target, Vec3d start, double distanceBetweenEach)
        {
            ArrayList<Double> angles = Util.getAnglesToBlock(target, start);
            double pitch = angles.get(0);
            double yaw = angles.get(1);
            double startingPosX = start.x;
            double startingPosY = start.y;
            double startingPosZ = start.z;
            
            ArrayList<Coordinate> coords = new ArrayList<>();
            double posX;
            double posY;
            double posZ;
            Coordinate newPos;
            
            double range = start.distanceTo(target);
            for (double blocksStepped = 0; blocksStepped < range; blocksStepped += distanceBetweenEach)
            {
                posX = blocksStepped * Math.cos(Math.toRadians(pitch)) * Math.sin(Math.toRadians(-1 * yaw));
                posY = blocksStepped * Math.sin(Math.toRadians(180 + pitch));
                posZ = blocksStepped * Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));
                
                posX += startingPosX;
                posY += startingPosY;
                posZ += startingPosZ;
                
                newPos = new Coordinate(posX, posY, posZ);
                
                coords.add(newPos);
            }
            
            return coords;
        }
        
    }
}

