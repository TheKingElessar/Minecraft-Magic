package com.thekingelessar.minecraftmagic.client;

import com.thekingelessar.minecraftmagic.client.renderer.entity.RenderEvokerFangsRotatable;
import com.thekingelessar.minecraftmagic.common.ServerProxy;
import com.thekingelessar.minecraftmagic.common.entity.EntityEvokerFangsRotatable;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy
{
    
    public ClientProxy()
    {
    
    }
    
    @Override
    public void preInit()
    {
        super.preInit();
        System.out.println("Client proxy preInit");
        RenderingRegistry.registerEntityRenderingHandler(EntityEvokerFangsRotatable.class, RenderEvokerFangsRotatable::new);
        
        // Here you'd register stuff that only needs to be registered on the client side
    }
    
    @Override
    public World getClientWorld()
    {
        return Minecraft.getInstance().world;
    }
    
}
