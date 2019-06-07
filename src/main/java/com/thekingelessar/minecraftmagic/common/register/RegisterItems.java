package com.thekingelessar.minecraftmagic.register;

import com.thekingelessar.minecraftmagic.MinecraftMagic;
import com.thekingelessar.minecraftmagic.item.ItemModItem;
import com.thekingelessar.minecraftmagic.item.spell.conjuration.ItemConjureFang;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterItems {

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        ItemModItem moditem = new ItemModItem();
        moditem.setRegistryName(MinecraftMagic.modid, "moditem");
        registry.register(moditem);

        ItemConjureFang conjurefang = new ItemConjureFang();
        conjurefang.setRegistryName(MinecraftMagic.modid, "conjurefang");
        registry.register(conjurefang);


    }

}
