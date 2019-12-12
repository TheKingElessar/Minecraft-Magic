package com.thekingelessar.minecraftmagic.common.network.packets.spells.conjuration;

import com.thekingelessar.minecraftmagic.common.spell.spells.conjuration.SpellConjureFang;
import com.thekingelessar.minecraftmagic.common.spell.util.target.targets.TargetBlock;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketConjureFang
{
    
    private final double x;
    private final double y;
    private final double z;
    private final String blockSide;
    
    public PacketConjureFang(double x, double y, double z, String blockSide)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        if (blockSide != null)
        {
            this.blockSide = blockSide;
        } else {
            this.blockSide = "null";
        }
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
                Entity sender = ctx.get().getSender();
                TargetBlock target = new TargetBlock((int) message.x, (int) message.y, (int) message.z, message.blockSide);
                
                SpellConjureFang.castServer(sender, target);
            });
            
            ctx.get().setPacketHandled(true);
        }
    }
    
}
// TODO: Use the "needed information" in README.md to start redoing the packet structure! You got this! You are the best person in the world for this job! Yeah, Nathan!
