package com.thekingelessar.minecraftmagic.common.entity.spells.evocation;

import com.thekingelessar.minecraftmagic.common.entity.ModEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityFirebolt extends Entity
{
    
    public EntityFirebolt(World worldIn)
    {
        super(ModEntities.firebolt, worldIn);
        this.setSize(0.5F, 0.166666667F);
    }
    
    @Override
    public EntityType<?> getType()
    {
        return ModEntities.firebolt;
    }
    
    @Override
    protected void registerData()
    {
    
    }
    
    @Override
    protected void readAdditional(NBTTagCompound compound)
    {
    
    }
    
    @Override
    protected void writeAdditional(NBTTagCompound compound)
    {
    
    }
    
}
