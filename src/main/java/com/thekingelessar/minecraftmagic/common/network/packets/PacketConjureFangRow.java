package com.thekingelessar.minecraftmagic.common.network.packets;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketConjureFang
{

    private final double x;
    private final double y;
    private final double z;
    private final float pitch;
    private final float yaw;

    public PacketConjureFang(double x, double y, double z, float pitch, float yaw)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public static void encode(PacketConjureFang msg, PacketBuffer buf)
    {
        buf.writeDouble(msg.x);
        buf.writeDouble(msg.y);
        buf.writeDouble(msg.z);
        buf.writeFloat(msg.pitch);
        buf.writeFloat(msg.yaw);

    }

    public static PacketConjureFang decode(PacketBuffer buf)
    {
        double x = buf.readDouble();
        double y = buf.readDouble();
        double z = buf.readDouble();
        float pitch = buf.readFloat();
        float yaw = buf.readFloat();

        return new PacketConjureFang(x, y, z, pitch, yaw);
    }

    public static class Handler
    {
        public static void handle(final PacketConjureFang message, Supplier<NetworkEvent.Context> ctx)
        {
            ctx.get().enqueueWork(() -> {
                // Work that needs to be threadsafe (most work)
                EntityPlayerMP sender = ctx.get().getSender();
                World senderWorld = sender.world;
                EntityEvokerFangs toSpawn = new EntityEvokerFangs(senderWorld);

                toSpawn.setPosition(message.x, message.y, message.z);
                toSpawn.rotationPitch = message.pitch;
                System.out.println("Pitch: " + toSpawn.rotationPitch);
                toSpawn.rotationYaw = message.yaw;
                System.out.println("Yaw: " + toSpawn.rotationYaw);
                senderWorld.spawnEntity(toSpawn);
            });

            ctx.get().setPacketHandled(true);
        }
    }

}
