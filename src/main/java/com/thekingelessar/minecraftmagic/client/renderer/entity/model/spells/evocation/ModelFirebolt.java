package com.thekingelessar.minecraftmagic.client.renderer.entity.model.spells.evocation;

import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelBox;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn (Dist.CLIENT)
public class ModelFirebolt extends ModelBase
{
    private final ModelRenderer firebolt;
    private final ModelRenderer nose_pyramid;
    private final ModelRenderer main_body;
    private final ModelRenderer tail_pyramid;
    
    public ModelFirebolt() {
        textureWidth = 64;
        textureHeight = 64;
    
        firebolt = new ModelRenderer(this);
        firebolt.setRotationPoint(0.0F, 0.0F, 0.0F);
    
        nose_pyramid = new ModelRenderer(this);
        nose_pyramid.setRotationPoint(0.0F, 0.0F, 0.0F);
        firebolt.addChild(nose_pyramid);
        nose_pyramid.cubeList.add(new ModelBox(nose_pyramid, 24, 33, -1.5F, -1.5F, -6.75F, 3, 3, 1, 0.0F, false));
        nose_pyramid.cubeList.add(new ModelBox(nose_pyramid, 6, 42, -0.5F, -0.5F, -7.75F, 1, 1, 1, 0.0F, false));
    
        main_body = new ModelRenderer(this);
        main_body.setRotationPoint(0.0F, 0.0F, 0.0F);
        firebolt.addChild(main_body);
        main_body.cubeList.add(new ModelBox(main_body, 0, 14, -1.5F, -2.5F, -5.75F, 3, 1, 11, 0.0F, false));
        main_body.cubeList.add(new ModelBox(main_body, 28, 0, -1.5F, 1.5F, -5.75F, 3, 1, 11, 0.0F, false));
        main_body.cubeList.add(new ModelBox(main_body, 0, 28, 1.5F, -1.5F, -5.75F, 1, 3, 11, 0.0F, false));
        main_body.cubeList.add(new ModelBox(main_body, 28, 14, -2.5F, -1.5F, -5.75F, 1, 3, 11, 0.0F, false));
    
        tail_pyramid = new ModelRenderer(this);
        tail_pyramid.setRotationPoint(0.0F, 0.0F, 0.0F);
        firebolt.addChild(tail_pyramid);
        tail_pyramid.cubeList.add(new ModelBox(tail_pyramid, 24, 28, -1.5F, -1.5F, 5.25F, 3, 3, 2, 0.0F, false));
        tail_pyramid.cubeList.add(new ModelBox(tail_pyramid, 0, 42, -0.5F, -0.5F, 7.25F, 1, 1, 2, 0.0F, false));
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        firebolt.render(f5);
    }
    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
}
