package com.thekingelessar.minecraftmagic.common.network.packets.spells.conjuration;

import com.thekingelessar.minecraftmagic.common.spell.spells.conjuration.SpellConjureFangRow;
import com.thekingelessar.minecraftmagic.common.spell.util.target.targets.TargetBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketConjureFangRow
{
    
    private final double x;
    private final double y;
    private final double z;
    
    private Entity caster;
    
    public PacketConjureFangRow(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public static void encode(PacketConjureFangRow msg, PacketBuffer buf)
    {
        buf.writeDouble(msg.x);
        buf.writeDouble(msg.y);
        buf.writeDouble(msg.z);
        
    }
    
    public static PacketConjureFangRow decode(PacketBuffer buf)
    {
        double x = buf.readDouble();
        double y = buf.readDouble();
        double z = buf.readDouble();
        
        return new PacketConjureFangRow(x, y, z);
    }
    
    public static class Handler
    {
        public static void handle(final PacketConjureFangRow message, Supplier<NetworkEvent.Context> ctx)
        {
            ctx.get().enqueueWork(() ->
            {
                // Work that needs to be threadsafe (most work)
                EntityPlayerMP sender = ctx.get().getSender();
                World senderWorld = sender.world;
                
                message.caster = sender;
                
                message.castSpell(message.caster, message);
                
            });
            
            ctx.get().setPacketHandled(true);
        }
    }
    
    private void castSpell(Entity caster, PacketConjureFangRow message)
    {
        TargetBlock targetBlock = new TargetBlock((int) message.x, (int) message.y, (int) message.z, null);
        SpellConjureFangRow.castServer(caster, targetBlock);
    }
    
}
