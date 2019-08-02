package com.thekingelessar.minecraftmagic.common.spell.util.target.targets;

import com.thekingelessar.minecraftmagic.common.spell.util.target.ITarget;

import javax.annotation.Nullable;

public class TargetBlock implements ITarget
{
    public Integer x;
    public Integer y;
    public Integer z;
    public String blockSide;
    
    public TargetBlock(Integer x, Integer y, Integer z, @Nullable String blockSide)
    {
        if (x != null && y != null && z != null)
        {
            this.x = x;
            this.y = y;
            this.z = z;
            this.blockSide = blockSide;
        }
    }
}
//todo switch to double instead of int