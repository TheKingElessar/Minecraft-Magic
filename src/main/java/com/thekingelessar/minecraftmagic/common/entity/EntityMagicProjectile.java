package com.thekingelessar.minecraftmagic.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import java.util.UUID;

public class EntityMagicProjectile extends Entity implements IEntityAdditionalSpawnData
{
    public UUID owner;
    
    public EntityMagicProjectile(World worldIn)
    {
        super(ModEntities.magicprojectile, worldIn);
        this.setSize(1F, 1F);
    }
    
    public EntityMagicProjectile(World worldIn, Entity owner)
    {
        this(worldIn);
        this.owner = owner.getUniqueID();
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
    
    @Override
    public void writeSpawnData(PacketBuffer buffer)
    {
        if (this.owner != null)
        {
            buffer.writeUniqueId(this.owner);
        }
    }
    
    @Override
    public void readSpawnData(PacketBuffer additionalData)
    {
        this.owner = additionalData.readUniqueId();
    }
}
