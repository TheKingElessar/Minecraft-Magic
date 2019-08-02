package com.thekingelessar.minecraftmagic.common.spell.util;

import com.thekingelessar.minecraftmagic.common.spell.util.target.ITarget;
import net.minecraft.entity.Entity;

public abstract class Castable
{
    protected Entity caster;
    public ITarget target;
    
    public abstract void castClient();
    
}
