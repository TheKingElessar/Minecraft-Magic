package com.thekingelessar.minecraftmagic.common.register;

import com.thekingelessar.minecraftmagic.MinecraftMagic;
import com.thekingelessar.minecraftmagic.common.entity.spells.conjuration.EntityEvokerFangsRotatable;
import com.thekingelessar.minecraftmagic.common.entity.spells.evocation.EntityFirebolt;
import com.thekingelessar.minecraftmagic.common.entity.EntityMagicProjectile;
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
        registry.register(EntityType.Builder.create(EntityFirebolt.class, EntityFirebolt::new).tracker(60, 24, true).disableSerialization().build("firebolt").setRegistryName(MinecraftMagic.modid, "firebolt"));
        registry.register(EntityType.Builder.create(EntityMagicProjectile.class, EntityMagicProjectile::new).tracker(60, 24, true).disableSerialization().build("magicprojectile").setRegistryName(MinecraftMagic.modid, "magicprojectile"));
    
    }
}
