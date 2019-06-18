package com.thekingelessar.minecraftmagic.common.network.packets;

import com.thekingelessar.minecraftmagic.logging.LogColors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEvoker;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;
import java.util.logging.Level;

import static com.thekingelessar.minecraftmagic.MinecraftMagic.logger;

public class PacketSummonEvokerFang {

    private final double x;
    private final double y;
    private final double z;
    private final float pitch;
    private final float yaw;

    public PacketSummonEvokerFang(double x, double y, double z, float pitch, float yaw)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public static void encode(PacketSummonEvokerFang msg, PacketBuffer buf)
    {
        buf.writeDouble(msg.x);
        buf.writeDouble(msg.y);
        buf.writeDouble(msg.z);
        buf.writeFloat(msg.pitch);
        buf.writeFloat(msg.yaw);

    }

    public static PacketSummonEvokerFang decode(PacketBuffer buf)
    {
        double x = buf.readDouble();
        double y = buf.readDouble();
        double z = buf.readDouble();
        float pitch = buf.readFloat();
        float yaw = buf.readFloat();

        return new PacketSummonEvokerFang(x, y, z, pitch, yaw);
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
