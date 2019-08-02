package com.thekingelessar.minecraftmagic.client.renderer.entity.render.spells;

import com.thekingelessar.minecraftmagic.client.renderer.entity.model.spells.ModelMagicProjectile;
import com.thekingelessar.minecraftmagic.common.entity.EntityMagicProjectile;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn (Dist.CLIENT)
public class RenderMagicProjectile extends Render<EntityMagicProjectile>
{
    private static final ResourceLocation MAGIC_PROJECTILE_TEXTURE = new ResourceLocation("minecraftmagic:textures/entity/magicprojectile.png");
    private final ModelMagicProjectile model = new ModelMagicProjectile();
    
    public RenderMagicProjectile(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
    }
    
    @Override
    public void doRender(EntityMagicProjectile entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        GlStateManager.enableAlphaTest();
        this.bindEntityTexture(entity);
        
        GlStateManager.translatef((float) x, (float) y, (float) z);
        
        this.model.render(entity, 0F, 0F, 1, 0, 0, 0.03125F); // todo: render pitch and yaw and all that
        
        GlStateManager.popMatrix();
        
        GlStateManager.enableCull();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
    
    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityMagicProjectile entity)
    {
        return RenderMagicProjectile.MAGIC_PROJECTILE_TEXTURE;
    }
    
}
