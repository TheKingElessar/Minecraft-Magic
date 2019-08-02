package com.thekingelessar.minecraftmagic.common.network.packets.spells.conjuration;

import com.thekingelessar.minecraftmagic.common.spell.spells.conjuration.SpellConjureFangCircle;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketConjureFangCircle
{
    
    public PacketConjureFangCircle()
    {
    }
    
    public static void encode(PacketConjureFangCircle msg, PacketBuffer buf)
    {
    }
    
    public static PacketConjureFangCircle decode(PacketBuffer buf)
    {
        
        return new PacketConjureFangCircle();
    }
    
    public static class Handler
    {
        public static void handle(final PacketConjureFangCircle message, Supplier<NetworkEvent.Context> ctx)
        {
            ctx.get().enqueueWork(() ->
            {
                // Work that needs to be threadsafe (most work)
                EntityPlayerMP sender = ctx.get().getSender();
                
                SpellConjureFangCircle.castServer(sender);

            });
            
            ctx.get().setPacketHandled(true);
        }
    }
    
}
