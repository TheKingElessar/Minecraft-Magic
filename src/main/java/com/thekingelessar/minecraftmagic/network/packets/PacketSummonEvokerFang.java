package com.thekingelessar.minecraftmagic.network.packets;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSummonEvokerFang {

    private final double x;
    private final double y;
    private final double z;

    public PacketSummonEvokerFang(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static void encode(PacketSummonEvokerFang msg, PacketBuffer buf)
    {
        buf.writeDouble(msg.x);
        buf.writeDouble(msg.y);
        buf.writeDouble(msg.z);

    }

    public static PacketSummonEvokerFang decode(PacketBuffer buf)
    {
        double x = buf.readDouble();
        double y = buf.readDouble();
        double z = buf.readDouble();

        return new PacketSummonEvokerFang(x, y, z);
    }

    public static class Handler
    {
        public static void handle(final PacketSummonEvokerFang message, Supplier<NetworkEvent.Context> ctx)
        {
            ctx.get().enqueueWork(() -> {
                // Work that needs to be threadsafe (most work)
                EntityPlayerMP sender = ctx.get().getSender();
                World senderWorld = sender.world;

                EntityEvokerFangs toSpawn = new EntityEvokerFangs(senderWorld);

                toSpawn.setPosition(message.x, message.y, message.z);
                senderWorld.spawnEntity(toSpawn);


            });

            ctx.get().setPacketHandled(true);
        }
    }

}
