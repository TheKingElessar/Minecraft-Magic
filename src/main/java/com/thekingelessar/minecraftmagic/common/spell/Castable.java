package com.thekingelessar.minecraftmagic.common.spell;

import com.thekingelessar.minecraftmagic.common.spell.target.ITarget;
import net.minecraft.entity.Entity;

public abstract class Castable
{
    
    protected Entity caster;
    public ITarget target = null;
    
    public abstract void castClient();
    
}
