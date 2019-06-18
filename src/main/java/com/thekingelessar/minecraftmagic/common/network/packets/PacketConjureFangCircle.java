package com.thekingelessar.minecraftmagic.common.network.packets;

import com.thekingelessar.minecraftmagic.common.spell.conjuration.conjurefang.SpellConjureFang;
import com.thekingelessar.minecraftmagic.common.spell.target.TargetBlock;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class PacketConjureFang
{
    
    public static UUID uuid;
    private final double x;
    private final double y;
    private final double z;
    private final String blockSide;
    
    public PacketConjureFang(double x, double y, double z, String blockSide)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.blockSide = blockSide;
    }
    
    public static void encode(PacketConjureFang msg, PacketBuffer buf)
    {
        buf.writeDouble(msg.x);
        buf.writeDouble(msg.y);
        buf.writeDouble(msg.z);
        buf.writeString(msg.blockSide);
        
    }
    
    public static PacketConjureFang decode(PacketBuffer buf)
    {
        double x = buf.readDouble();
        double y = buf.readDouble();
        double z = buf.readDouble();
        String blockSide = buf.readString(8);
        
        return new PacketConjureFang(x, y, z, blockSide);
    }
    
    public static class Handler
    {
        public static void handle(final PacketConjureFang message, Supplier<NetworkEvent.Context> ctx)
        {
            ctx.get().enqueueWork(() ->
            {
                // Work that needs to be threadsafe (most work)
                EntityPlayerMP sender = ctx.get().getSender(); // TODO: Make this so it doesn't have to be a player, for example make a spawning class that takes the parameters from the message
                TargetBlock target = new TargetBlock(message.x, message.y, message.z, message.blockSide);
                
                SpellConjureFang.castServer(sender, target);

                /* TODO: follow this model for future spells. When cast, the client should gather the needed data (most likely the target),
                    then send it to the server in packets. The packets should be unwrapped and the data inside should be used to finish the
                    casting on the server.
                    Update the README to reflect this and provide instructions!
                 */
            });
            
            ctx.get().setPacketHandled(true);
        }
    }
    
}
