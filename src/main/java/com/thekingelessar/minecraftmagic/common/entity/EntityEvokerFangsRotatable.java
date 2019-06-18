package com.thekingelessar.minecraftmagic.common.entity;

import io.netty.handler.codec.DecoderException;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityEvokerFangsRotatable extends EntityEvokerFangs implements IEntityAdditionalSpawnData
{
    
    public String blockSide;
    
    
    public EntityEvokerFangsRotatable(World worldIn)
    {
        super(worldIn);
    }
    
    @Override
    public EntityType<?> getType()
    {
        return ModEntities.evokerfangsrotatable;
    }
    
    @Override
    public void writeSpawnData(PacketBuffer buffer)
    {
        if (this.blockSide != null)
        {
            buffer.writeString(this.blockSide);
        }
    }
    
    @Override
    public void readSpawnData(PacketBuffer additionalData)
    {
        try
        {
            this.blockSide = additionalData.readString(8);
        }
        catch (DecoderException exeption)
        {
            this.blockSide = null;
        }
    }
}
