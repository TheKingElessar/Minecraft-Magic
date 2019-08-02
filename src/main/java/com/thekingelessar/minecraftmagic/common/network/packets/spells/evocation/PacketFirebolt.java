package com.thekingelessar.minecraftmagic.common.network.packets.spells.evocation;

import com.thekingelessar.minecraftmagic.common.spell.spells.evocation.SpellFirebolt;
import com.thekingelessar.minecraftmagic.common.spell.util.target.targets.TargetDirection;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketFirebolt
{
    
    private final double yaw;
    private final double pitch;

    public PacketFirebolt(double yaw, double pitch)
    {
        this.yaw = yaw;
        this.pitch = pitch;
    }
    
    public static void encode(PacketFirebolt msg, PacketBuffer buf)
    {
        buf.writeDouble(msg.yaw);
        buf.writeDouble(msg.pitch);
    }
    
    public static PacketFirebolt decode(PacketBuffer buf)
    {
        double yaw = buf.readDouble();
        double pitch = buf.readDouble();
        
        return new PacketFirebolt(yaw, pitch);
    }
    
    public static class Handler
    {
        public static void handle(final PacketFirebolt message, Supplier<NetworkEvent.Context> ctx)
        {
            ctx.get().enqueueWork(() ->
            {
                // Work that needs to be threadsafe (most work)
                EntityPlayerMP sender = ctx.get().getSender();
                
                TargetDirection target = new TargetDirection(message.yaw, message.pitch);
                
                SpellFirebolt.castServer(sender, target);
                
            });
            
            ctx.get().setPacketHandled(true);
        }
    }
    
}
