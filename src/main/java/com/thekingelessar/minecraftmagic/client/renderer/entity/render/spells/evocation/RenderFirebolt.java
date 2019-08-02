package com.thekingelessar.minecraftmagic.client.renderer.entity.render.spells.evocation;

import com.thekingelessar.minecraftmagic.client.renderer.entity.model.spells.evocation.ModelFirebolt;
import com.thekingelessar.minecraftmagic.common.entity.spells.evocation.EntityFirebolt;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn (Dist.CLIENT)
public class RenderFirebolt extends Render<EntityFirebolt>
{
    private static final ResourceLocation FIREBOLT_TEXTURE = new ResourceLocation("minecraftmagic:textures/entity/firebolt.png");
    private final ModelFirebolt model = new ModelFirebolt();
    
    public RenderFirebolt(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
    }
    
    @Override
    public void doRender(EntityFirebolt entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        GlStateManager.enableAlphaTest();
        this.bindEntityTexture(entity);
        
        GlStateManager.translatef((float) x, (float) y, (float) z);
        GlStateManager.rotatef(180.0F - entity.rotationYaw, 0.0F, 1.0F, 0.0F);
        // GlStateManager.rotatef(entity.rotationPitch, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotatef(-1 * entity.rotationPitch, 1.0F, 0.0F, 0.0F);
        
        
        this.model.render(entity, 0F, 0F, 1, 0, 0, 0.03125F);
        
        GlStateManager.popMatrix();
        
        GlStateManager.enableCull();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
    
    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityFirebolt entity)
    {
        return RenderFirebolt.FIREBOLT_TEXTURE;
    }
    
    void applyRotations(EntityFirebolt entityLiving, float rotationYaw, float partialTicks)
    {
        GlStateManager.rotatef(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
        
    }
}
