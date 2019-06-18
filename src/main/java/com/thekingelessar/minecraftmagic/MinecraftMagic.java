package com.thekingelessar.minecraftmagic;

import com.thekingelessar.minecraftmagic.client.ClientProxy;
import com.thekingelessar.minecraftmagic.common.ServerProxy;
import com.thekingelessar.minecraftmagic.common.network.MinecraftMagicPacketHandler;
import com.thekingelessar.minecraftmagic.common.network.packets.PacketConjureFang;
import com.thekingelessar.minecraftmagic.common.network.packets.PacketConjureFangCircle;
import com.thekingelessar.minecraftmagic.common.network.packets.PacketConjureFangRow;
import com.thekingelessar.minecraftmagic.common.network.packets.PacketGlowSingleEntity;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.logging.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod (MinecraftMagic.modid)
public class MinecraftMagic
{
    
    // Mod ID stored as a variable to reduce errors and make it easier to change
    public static final String modid = "minecraftmagic";
    
    public static ServerProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    
    public static final Logger logger = Logger.getLogger("minecraftmagic");
    
    
    public MinecraftMagic()
    {
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
        
        MinecraftMagicPacketHandler.INSTANCE.registerMessage(
                MinecraftMagicPacketHandler.increaseId(),
                PacketConjureFang.class,
                PacketConjureFang::encode,
                PacketConjureFang::decode,
                PacketConjureFang.Handler::handle
        );
        
        MinecraftMagicPacketHandler.INSTANCE.registerMessage(
                MinecraftMagicPacketHandler.increaseId(),
                PacketGlowSingleEntity.class,
                PacketGlowSingleEntity::encode,
                PacketGlowSingleEntity::decode,
                PacketGlowSingleEntity.Handler::handle
        );
        
        MinecraftMagicPacketHandler.INSTANCE.registerMessage(
                MinecraftMagicPacketHandler.increaseId(),
                PacketConjureFangRow.class,
                PacketConjureFangRow::encode,
                PacketConjureFangRow::decode,
                PacketConjureFangRow.Handler::handle
        );
        
        MinecraftMagicPacketHandler.INSTANCE.registerMessage(
                MinecraftMagicPacketHandler.increaseId(),
                PacketConjureFangCircle.class,
                PacketConjureFangCircle::encode,
                PacketConjureFangCircle::decode,
                PacketConjureFangCircle.Handler::handle
        );
        
    }
    
    private void preInit(final FMLCommonSetupEvent event)
    {
        System.out.println("@Mod preInit");
        proxy.preInit();
    }
    
}
