package com.thekingelessar.minecraftmagic;

import com.thekingelessar.minecraftmagic.network.MinecraftMagicPacketHandler;
import com.thekingelessar.minecraftmagic.network.packets.PacketGlowSingleEntity;
import com.thekingelessar.minecraftmagic.network.packets.PacketSummonEvokerFang;
import com.thekingelessar.minecraftmagic.proxy.ClientProxy;
import com.thekingelessar.minecraftmagic.proxy.CommonProxy;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MinecraftMagic.modid)
public class MinecraftMagic
{

    // Mod ID stored as a variable to reduce errors and make it easier to change
    public static final String modid = "minecraftmagic";

    private final CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public MinecraftMagic() {
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        proxy.construct();

        MinecraftMagicPacketHandler.INSTANCE.registerMessage(
                MinecraftMagicPacketHandler.increaseId(),
                PacketSummonEvokerFang.class,
                PacketSummonEvokerFang::encode,
                PacketSummonEvokerFang::decode,
                PacketSummonEvokerFang.Handler::handle
        );

        MinecraftMagicPacketHandler.INSTANCE.registerMessage(
                MinecraftMagicPacketHandler.increaseId(),
                PacketGlowSingleEntity.class,
                PacketGlowSingleEntity::encode,
                PacketGlowSingleEntity::decode,
                PacketGlowSingleEntity.Handler::handle
        );

    }

    @SubscribeEvent
    public void setup(FMLCommonSetupEvent event) {
        proxy.setup();
    }

    @SubscribeEvent
    public void ready(FMLLoadCompleteEvent event) {
        proxy.complete();
    }

}
