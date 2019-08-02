package com.thekingelessar.minecraftmagic.client.renderer.entity.model.spells;

import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelBox;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelMagicProjectile extends ModelBase
{
    private final ModelRenderer main;
    private final ModelRenderer internal;
    private final ModelRenderer internalnorth;
    private final ModelRenderer northvertical;
    private final ModelRenderer internalsouth;
    private final ModelRenderer southvertical;
    private final ModelRenderer internaleast;
    private final ModelRenderer internalwest;
    private final ModelRenderer north;
    private final ModelRenderer down;
    private final ModelRenderer up;
    private final ModelRenderer south;
    private final ModelRenderer east;
    private final ModelRenderer west;
    
    private double degreesRotated = 0;
    
    public ModelMagicProjectile()
    {
        textureWidth = 128;
        textureHeight = 128;
        
        main = new ModelRenderer(this);
        main.setRotationPoint(0.0F, 24.0F, 0.0F);
        
        internal = new ModelRenderer(this);
        internal.setRotationPoint(0.0F, 52.0F, -4.0F);
        main.addChild(internal);
        
        internalnorth = new ModelRenderer(this);
        internalnorth.setRotationPoint(0.0F, 0.0F, 0.0F);
        internal.addChild(internalnorth);
        internalnorth.cubeList.add(new ModelBox(internalnorth, 0, 32, -6.5F, -59.5F, -3.5F, 13, 1, 1, 0.0F, false));
        internalnorth.cubeList.add(new ModelBox(internalnorth, 0, 30, -6.5F, -45.5F, -3.5F, 13, 1, 1, 0.0F, false));
        
        northvertical = new ModelRenderer(this);
        northvertical.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(northvertical, 0.0F, 0.0F, -1.5708F);
        internalnorth.addChild(northvertical);
        northvertical.cubeList.add(new ModelBox(northvertical, 68, 40, 45.5F, -7.5F, -3.5F, 13, 1, 1, 0.0F, false));
        northvertical.cubeList.add(new ModelBox(northvertical, 20, 40, 45.5F, 6.5F, -3.5F, 13, 1, 1, 0.0F, false));
        
        internalsouth = new ModelRenderer(this);
        internalsouth.setRotationPoint(0.0F, 0.0F, 0.0F);
        internal.addChild(internalsouth);
        internalsouth.cubeList.add(new ModelBox(internalsouth, 84, 28, -6.5F, -59.5F, 10.5F, 13, 1, 1, 0.0F, false));
        internalsouth.cubeList.add(new ModelBox(internalsouth, 56, 28, -6.5F, -45.5F, 10.5F, 13, 1, 1, 0.0F, false));
        
        southvertical = new ModelRenderer(this);
        southvertical.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(southvertical, 0.0F, 0.0F, -1.5708F);
        internalsouth.addChild(southvertical);
        southvertical.cubeList.add(new ModelBox(southvertical, 0, 50, 45.5F, -7.5F, 10.5F, 13, 1, 1, 0.0F, false));
        southvertical.cubeList.add(new ModelBox(southvertical, 48, 30, 45.5F, 6.5F, 10.5F, 13, 1, 1, 0.0F, false));
        
        internaleast = new ModelRenderer(this);
        internaleast.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(internaleast, 0.0F, 1.5708F, 0.0F);
        internal.addChild(internaleast);
        internaleast.cubeList.add(new ModelBox(internaleast, 28, 28, -10.5F, -59.5F, -7.5F, 13, 1, 1, 0.0F, false));
        internaleast.cubeList.add(new ModelBox(internaleast, 0, 28, -10.5F, -45.5F, -7.5F, 13, 1, 1, 0.0F, false));
        
        internalwest = new ModelRenderer(this);
        internalwest.setRotationPoint(0.0F, 0.0F, 0.0F);
        setRotationAngle(internalwest, 0.0F, 1.5708F, 0.0F);
        internal.addChild(internalwest);
        internalwest.cubeList.add(new ModelBox(internalwest, 48, 50, -10.5F, -59.5F, 6.5F, 13, 1, 1, 0.0F, false));
        internalwest.cubeList.add(new ModelBox(internalwest, 0, 52, -10.5F, -45.5F, 6.5F, 13, 1, 1, 0.0F, false));
        
        north = new ModelRenderer(this);
        north.setRotationPoint(0.0F, 52.0F, -4.0F);
        main.addChild(north);
        north.cubeList.add(new ModelBox(north, 28, 0, -6.5F, -58.5F, -4.5F, 13, 13, 1, 0.0F, false));
        north.cubeList.add(new ModelBox(north, 48, 40, -4.5F, -56.5F, -5.5F, 9, 9, 1, 0.0F, false));
        north.cubeList.add(new ModelBox(north, 24, 70, -2.5F, -54.5F, -6.5F, 5, 5, 1, 0.0F, false));
        north.cubeList.add(new ModelBox(north, 36, 74, -0.5F, -52.5F, -7.5F, 1, 1, 1, 0.0F, false));
        
        down = new ModelRenderer(this);
        down.setRotationPoint(0.0F, 52.0F, -4.0F);
        setRotationAngle(down, 1.5708F, 0.0F, 0.0F);
        main.addChild(down);
        down.cubeList.add(new ModelBox(down, 0, 0, -6.5F, -2.5F, 43.5F, 13, 13, 1, 0.0F, false));
        down.cubeList.add(new ModelBox(down, 28, 30, -4.5F, -0.5F, 42.5F, 9, 9, 1, 0.0F, false));
        down.cubeList.add(new ModelBox(down, 64, 60, -2.5F, 1.5F, 41.5F, 5, 5, 1, 0.0F, false));
        down.cubeList.add(new ModelBox(down, 40, 70, -0.5F, 3.5F, 40.5F, 1, 1, 1, 0.0F, false));
        
        up = new ModelRenderer(this);
        up.setRotationPoint(0.0F, 52.0F, -4.0F);
        setRotationAngle(up, -1.5708F, 0.0F, 0.0F);
        main.addChild(up);
        up.cubeList.add(new ModelBox(up, 0, 14, -6.5F, -10.5F, -60.5F, 13, 13, 1, 0.0F, false));
        up.cubeList.add(new ModelBox(up, 28, 50, -4.5F, -8.5F, -61.5F, 9, 9, 1, 0.0F, false));
        up.cubeList.add(new ModelBox(up, 52, 60, -2.5F, -6.5F, -62.5F, 5, 5, 1, 0.0F, false));
        up.cubeList.add(new ModelBox(up, 36, 70, -0.5F, -4.5F, -63.5F, 1, 1, 1, 0.0F, false));
        
        south = new ModelRenderer(this);
        south.setRotationPoint(0.0F, 52.0F, -4.0F);
        setRotationAngle(south, 3.1416F, 0.0F, 0.0F);
        main.addChild(south);
        south.cubeList.add(new ModelBox(south, 56, 14, -6.5F, 45.5F, -12.5F, 13, 13, 1, 0.0F, false));
        south.cubeList.add(new ModelBox(south, 0, 60, -4.5F, 47.5F, -13.5F, 9, 9, 1, 0.0F, false));
        south.cubeList.add(new ModelBox(south, 40, 60, -2.5F, 49.5F, -14.5F, 5, 5, 1, 0.0F, false));
        south.cubeList.add(new ModelBox(south, 40, 72, -0.5F, 51.5F, -15.5F, 1, 1, 1, 0.0F, false));
        
        east = new ModelRenderer(this);
        east.setRotationPoint(0.0F, 52.0F, -4.0F);
        setRotationAngle(east, 3.1416F, -1.5708F, 0.0F);
        main.addChild(east);
        east.cubeList.add(new ModelBox(east, 28, 14, -2.5F, 45.5F, -8.5F, 13, 13, 1, 0.0F, false));
        east.cubeList.add(new ModelBox(east, 20, 60, -0.5F, 47.5F, -9.5F, 9, 9, 1, 0.0F, false));
        east.cubeList.add(new ModelBox(east, 0, 70, 1.5F, 49.5F, -10.5F, 5, 5, 1, 0.0F, false));
        east.cubeList.add(new ModelBox(east, 36, 72, 3.5F, 51.5F, -11.5F, 1, 1, 1, 0.0F, false));
        
        west = new ModelRenderer(this);
        west.setRotationPoint(0.0F, 52.0F, -4.0F);
        setRotationAngle(west, 3.1416F, 1.5708F, 0.0F);
        main.addChild(west);
        west.cubeList.add(new ModelBox(west, 56, 0, -10.5F, 45.5F, -8.5F, 13, 13, 1, 0.0F, false));
        west.cubeList.add(new ModelBox(west, 0, 40, -8.5F, 47.5F, -9.5F, 9, 9, 1, 0.0F, false));
        west.cubeList.add(new ModelBox(west, 12, 70, -6.5F, 49.5F, -10.5F, 5, 5, 1, 0.0F, false));
        west.cubeList.add(new ModelBox(west, 40, 74, -4.5F, 51.5F, -11.5F, 1, 1, 1, 0.0F, false));
    }
    
    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        main.render(f5);
    }
    
    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        degreesRotated += 45;
        if (degreesRotated >= 360) {
            degreesRotated = 0;
        }
        this.main.rotateAngleX = (float) degreesRotated;
        
    }
}