package com.thekingelessar.minecraftmagic.client;

import com.thekingelessar.minecraftmagic.common.ServerProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class ClientProxy extends ServerProxy {

    public ClientProxy()
    {

    }

    @Override
    public void preInit()
    {
        super.preInit();

        // Here you'd register stuff that only needs to be registered on the client side
    }

    @Override
    public World getClientWorld()
    {
        return Minecraft.getInstance().world;
    }

}
