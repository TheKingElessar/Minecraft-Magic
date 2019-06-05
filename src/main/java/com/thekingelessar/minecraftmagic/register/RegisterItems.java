package com.thekingelessar.minecraftmagic.init;

import com.thekingelessar.minecraftmagic.MinecraftMagic;
import com.thekingelessar.minecraftmagic.item.Jesses;
import com.thekingelessar.minecraftmagic.item.ModItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemInit {

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        ModItem moditem = new ModItem();
        moditem.setRegistryName(MinecraftMagic.modid, "moditem");
        registry.register(moditem);

        Jesses jesses = new Jesses();
        jesses.setRegistryName(MinecraftMagic.modid, "jesses");
        registry.register(jesses);

    }

}
