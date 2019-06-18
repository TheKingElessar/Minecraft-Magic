package com.thekingelessar.minecraftmagic.common.register;

import com.thekingelessar.minecraftmagic.MinecraftMagic;
import com.thekingelessar.minecraftmagic.common.entity.EntityEvokerFangsRotatable;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber (bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterEntity
{
    @SubscribeEvent
    public static void onEntitiesRegistry(final RegistryEvent.Register<EntityType<?>> entityRegistryEvent)
    {
        IForgeRegistry<EntityType<?>> registry = entityRegistryEvent.getRegistry();
        
        registry.register(EntityType.Builder.create(EntityEvokerFangsRotatable.class, EntityEvokerFangsRotatable::new).tracker(60, 24, true).disableSerialization().build("evokerfangsrotatable").setRegistryName(MinecraftMagic.modid, "evokerfangsrotatable"));
    }
}
