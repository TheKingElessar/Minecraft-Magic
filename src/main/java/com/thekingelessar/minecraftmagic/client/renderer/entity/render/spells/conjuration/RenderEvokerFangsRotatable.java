package com.thekingelessar.minecraftmagic.client.renderer.entity.render.spells.conjuration;

import com.thekingelessar.minecraftmagic.client.renderer.entity.model.spells.conjuration.ModelEvokerFangsRotatable;
import com.thekingelessar.minecraftmagic.common.entity.spells.conjuration.EntityEvokerFangsRotatable;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn (Dist.CLIENT)
public class RenderEvokerFangsRotatable extends Render<EntityEvokerFangsRotatable>
{
    private static final ResourceLocation EVOKER_ILLAGER_FANGS = new ResourceLocation("textures/entity/illager/evoker_fangs.png");
    private final ModelEvokerFangsRotatable model = new ModelEvokerFangsRotatable();
    
    public RenderEvokerFangsRotatable(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
    }
    
    /**
     * Renders the desired {@code T} type Entity.
     */
    @Override
    public void doRender(EntityEvokerFangsRotatable entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        float f = entity.getAnimationProgress(partialTicks);
        if (f != 0.0F)
        {
            float f1 = 2.0F;
            if (f > 0.9F)
            {
                f1 = (float) ((double) f1 * ((1.0D - (double) f) / (double) 0.1F));
            }
            
            GlStateManager.pushMatrix();
            GlStateManager.disableCull();
            GlStateManager.enableAlphaTest();
            this.bindEntityTexture(entity);
            GlStateManager.translatef((float) x, (float) y, (float) z);
            
            if (entity.blockSide != null)
            {
                switch (EnumFacing.valueOf(entity.blockSide))
                {
                    case UP:
                        break;
                    case DOWN:
                        GlStateManager.rotatef(180.0F, 0.0F, 0.0F, 1.0F);
                        break;
                    case EAST:
                        entity.rotationYaw = -90F;
                        GlStateManager.rotatef(-90.0F, 0.0F, 0.0F, 1.0F);
                        break;
                    case WEST:
                        entity.rotationYaw = 90F;
                        GlStateManager.rotatef(90.0F, 0.0F, 0.0F, 1.0F);
                        break;
                    case NORTH:
                        GlStateManager.rotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                        break;
                    case SOUTH:
                        GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
                        break;
                    default:
                        break;
                }
            }
            
            GlStateManager.rotatef(90.0F - entity.rotationYaw, 0.0F, 1.0F, 0.0F);
            GlStateManager.scalef(-f1, -f1, f1);
            float f2 = 0.03125F;
            GlStateManager.translatef(0.0F, -0.626F, 0.0F);
            this.model.render(entity, f, 0.0F, 0.0F, entity.rotationYaw, entity.rotationPitch, 0.03125F);
            GlStateManager.popMatrix();
            GlStateManager.enableCull();
            GlStateManager.color4f(5.0F, 1.0F, 1.0F, 1.0F);
            super.doRender(entity, x, y, z, entityYaw, partialTicks);
        }
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityEvokerFangsRotatable entity)
    {
        return EVOKER_ILLAGER_FANGS;
    }
    
}
