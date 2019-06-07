package com.thekingelessar.minecraftmagic.client.renderer;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.*;
import net.minecraft.world.chunk.IChunkProvider;

import javax.annotation.Nullable;
import java.util.List;

public class ExtendedRange {

    private Minecraft mc;
    public RayTraceResult mcObjectMouseOver;
    private final double range;
    private final RayTraceFluidMode fluidMode;
    private final Entity requestingEntity;
    private Float partialTicks;
    public boolean airTargeted;
    public EnumFacing blockSideHit;

    private IChunkProvider chunkProvider;

    public ExtendedRange(@Nullable Float partialTicks, double range, RayTraceFluidMode fluidMode, Minecraft mc, Entity entity)
    {
        this.mc = mc;
        if(partialTicks == null) {
            this.partialTicks = 1.0F;
        }
        this.range = range;
        this.fluidMode = fluidMode;
        this.requestingEntity = entity;
    }

    public void getMouseOver()
    {
        if (requestingEntity != null)
        {
            if (this.mc.world != null)
            {
                this.mc.profiler.startSection("pick");
                Entity pointedEntity = null;
                double d0 = range; // block reach distance. default 5.0D, max 1024.0D;

                this.mcObjectMouseOver = requestingEntity.rayTrace(d0, partialTicks, fluidMode);
                BlockPos blockPos = this.mcObjectMouseOver.getBlockPos();
                IBlockState state = mc.world.getBlockState(blockPos);
                this.blockSideHit = mcObjectMouseOver.sideHit;

                this.airTargeted = state.isAir(mc.world, blockPos);

                Vec3d vec3d = requestingEntity.getEyePosition(partialTicks);
                boolean flag = false;
                int i = 3;
                double d1 = d0;
                if (d0 > 3.0D) // if range is larger than 3 blocks
                {
                    flag = true;
                }

                if (this.mcObjectMouseOver != null)
                {
                    d1 = this.mcObjectMouseOver.hitVec.distanceTo(vec3d); // distance between ray trace and eye position
                }

                Vec3d vec3d1 = requestingEntity.getLook(1.0F); // get vector from angle of look
                Vec3d vec3d2 = vec3d.add(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0); // add range multiplied by where entity is looking
                Vec3d vec3d3 = null;
                float f = 1.0F;
                List<Entity> list = this.mc.world.getEntitiesInAABBexcluding(requestingEntity, requestingEntity.getBoundingBox().expand(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0).grow(1.0D, 1.0D, 1.0D), EntitySelectors.NOT_SPECTATING.and(Entity::canBeCollidedWith));
                double d2 = d1; // d1 is either range or distance between ray trace and eye position

                for (Entity entity1 : list)
                {
                    AxisAlignedBB axisalignedbb = entity1.getBoundingBox().grow((double) entity1.getCollisionBorderSize());
                    RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(vec3d, vec3d2); // vector between eye position and range location
                    if (axisalignedbb.contains(vec3d)) // if entity is intersected, set entity as intersected
                    {
                        if (d2 >= 0.0D)
                        {
                            pointedEntity = entity1;
                            this.airTargeted = false;

                            vec3d3 = raytraceresult == null ? vec3d : raytraceresult.hitVec;
                            d2 = 0.0D;
                        }
                    } else if (raytraceresult != null) // run when entity is targeted...
                    {
                        double d3 = vec3d.distanceTo(raytraceresult.hitVec); // distance between eye position and range location
                        if (d3 < d2 || d2 == 0.0D)
                        {
                            if (entity1.getLowestRidingEntity() == requestingEntity.getLowestRidingEntity() && !entity1.canRiderInteract()) // if the entity found is what the requesting entity is riding
                            {
                                if (d2 == 0.0D)
                                {
                                    pointedEntity = entity1;
                                    this.airTargeted = false;

                                    vec3d3 = raytraceresult.hitVec;
                                }
                            } else
                            {
                                pointedEntity = entity1;
                                this.airTargeted = false;

                                vec3d3 = raytraceresult.hitVec;
                                d2 = d3;
                            }
                        }
                    }

                }

                if (pointedEntity != null && flag && vec3d.distanceTo(vec3d3) > 3.0D) // if * AND reach is greater than 3 blocks AND
                {
                    this.mcObjectMouseOver = new RayTraceResult(RayTraceResult.Type.MISS, vec3d3, (EnumFacing)null, new BlockPos(vec3d3));
                }

                if (pointedEntity != null && (d2 < d1 || this.mcObjectMouseOver == null))
                {
                    this.mcObjectMouseOver = new RayTraceResult(pointedEntity, vec3d3);
                }

                this.mc.profiler.endSection();
            }
        }
    }
}
