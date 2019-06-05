package com.thekingelessar.minecraftmagic.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.*;

import javax.annotation.Nullable;
import java.util.List;

public class ExtendedRange {

    private Minecraft mc;
    public Entity pointedEntity;
    public RayTraceResult mcObjectMouseOver;
    private double range;
    private RayTraceFluidMode fluidMode;
    private Entity requestingEntity;
    private Float partialTicks;

    public ExtendedRange(@Nullable Float partialTicks, double range, RayTraceFluidMode fluidMode, Minecraft mc, Entity entity) {
        this.mc = mc;
        if(partialTicks == null) {
            this.partialTicks = 1.0F;
        }
        this.range = range;
        this.fluidMode = fluidMode;
        this.requestingEntity = entity;
    }

    public void getMouseOver() {
        if (requestingEntity != null) {
            if (this.mc.world != null) {
                this.mc.profiler.startSection("pick");
                this.pointedEntity = null;
                double d0 = range; // block reach distance. default 5.0D, max 1024.0D;
                this.mcObjectMouseOver = requestingEntity.rayTrace(d0, partialTicks, fluidMode);
                Vec3d vec3d = requestingEntity.getEyePosition(partialTicks);
                boolean flag = false;
                int i = 3;
                double d1 = d0;
                if (d0 > 3.0D) {
                    flag = true;
                }

                d0 = d0;

                if (this.mcObjectMouseOver != null) {
                    d1 = this.mcObjectMouseOver.hitVec.distanceTo(vec3d);
                }

                Vec3d vec3d1 = requestingEntity.getLook(1.0F);
                Vec3d vec3d2 = vec3d.add(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0);
                this.pointedEntity = null;
                Vec3d vec3d3 = null;
                float f = 1.0F;
                List<Entity> list = this.mc.world.getEntitiesInAABBexcluding(requestingEntity, requestingEntity.getBoundingBox().expand(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0).grow(1.0D, 1.0D, 1.0D), EntitySelectors.NOT_SPECTATING.and(Entity::canBeCollidedWith));
                double d2 = d1;

                for(int j = 0; j < list.size(); ++j) {
                    Entity entity1 = list.get(j);
                    AxisAlignedBB axisalignedbb = entity1.getBoundingBox().grow((double)entity1.getCollisionBorderSize());
                    RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(vec3d, vec3d2);
                    if (axisalignedbb.contains(vec3d)) {
                        if (d2 >= 0.0D) {
                            this.pointedEntity = entity1;
                            vec3d3 = raytraceresult == null ? vec3d : raytraceresult.hitVec;
                            d2 = 0.0D;
                        }
                    } else if (raytraceresult != null) {
                        double d3 = vec3d.distanceTo(raytraceresult.hitVec);
                        if (d3 < d2 || d2 == 0.0D) {
                            if (entity1.getLowestRidingEntity() == requestingEntity.getLowestRidingEntity() && !entity1.canRiderInteract()) {
                                if (d2 == 0.0D) {
                                    this.pointedEntity = entity1;
                                    vec3d3 = raytraceresult.hitVec;
                                }
                            } else {
                                this.pointedEntity = entity1;
                                vec3d3 = raytraceresult.hitVec;
                                d2 = d3;
                            }
                        }
                    }
                }

                if (this.pointedEntity != null && flag && vec3d.distanceTo(vec3d3) > 3.0D) {
                    this.mcObjectMouseOver = new RayTraceResult(RayTraceResult.Type.MISS, vec3d3, (EnumFacing)null, new BlockPos(vec3d3));
                }

                if (this.pointedEntity != null && (d2 < d1 || this.mcObjectMouseOver == null)) {
                    this.mcObjectMouseOver = new RayTraceResult(this.pointedEntity, vec3d3);
                }

                this.mc.profiler.endSection();
            }
        }
    }


}
