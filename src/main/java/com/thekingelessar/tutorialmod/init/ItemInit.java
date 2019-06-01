package com.thekingelessar.tutorialmod.init;

import com.thekingelessar.tutorialmod.TutorialMod;
import com.thekingelessar.tutorialmod.item.Jesses;
import com.thekingelessar.tutorialmod.item.ModItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemInit {

    public static final ModItem moditem = new ModItem();
    public static final Jesses jesses = new Jesses();


    @SubscribeEvent
    public static void register(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        moditem.setRegistryName(TutorialMod.modid, "moditem");
        registry.register(moditem);

        jesses.setRegistryName(TutorialMod.modid, "jesses");
        registry.register(jesses);

    }

}
