package com.thekingelessar.minecraftmagic.common;

import net.minecraft.world.World;

public class ServerProxy
{
    
    public void preInit()
    {
        System.out.println("Server proxy preInit");
    }
    
    public World getClientWorld()
    {
        return null;
    }
}
