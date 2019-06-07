package com.thekingelessar.minecraftmagic.common.network.packets;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.MobEffects;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketGlowSingleEntity {

    private final String UUID;

    public PacketGlowSingleEntity(String UUID)
    {
        this.UUID = UUID;
    }

    public static void encode(PacketGlowSingleEntity msg, PacketBuffer buf)
    {
        buf.writeString(msg.UUID);
    }

    public static PacketGlowSingleEntity decode(PacketBuffer buf)
    {
        String UUID = buf.readString(36);

        return new PacketGlowSingleEntity(UUID);
    }

    public static class Handler
    {
        public static void handle(final PacketGlowSingleEntity message, Supplier<NetworkEvent.Context> ctx)
        {
            ctx.get().enqueueWork(() -> {

                java.util.UUID uuid = java.util.UUID.fromString(message.UUID);
                NetworkEvent.Context context = ctx.get();
                World world = context.getSender().world;

                for (Entity entityInWorld : world.loadedEntityList) {

                    if(entityInWorld.getUniqueID().equals(uuid)) {
                        EntityLiving livingEntity = (EntityLiving) entityInWorld;
                        PotionEffect glowing = new PotionEffect(MobEffects.GLOWING, 100);
                        livingEntity.addPotionEffect(glowing);
                    }
                }
            });

            ctx.get().setPacketHandled(true);
        }
    }

}
