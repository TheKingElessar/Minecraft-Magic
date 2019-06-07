package com.thekingelessar.minecraftmagic.spell;

import com.thekingelessar.minecraftmagic.spell.target.ITarget;
import net.minecraft.entity.Entity;

public abstract class Castable {

    public Entity caster;
    public ITarget target;


    public abstract void cast();

}
