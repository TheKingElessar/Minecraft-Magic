package com.thekingelessar.minecraftmagic.common.spell.target;

import javax.annotation.Nullable;

public class TargetBlock implements ITarget
{
    public Double x;
    public Double y;
    public Double z;
    public String blockSide;
    
    public TargetBlock(Double x, Double y, Double z, @Nullable String blockSide)
    {
        if (x != null && y != null && z != null)
        {
            this.x = x;
            this.y = y;
            this.z = z;
            this.blockSide = blockSide;
        }
    }
    
    public boolean isNotNull()
    {
        return this.x != null && this.y != null && this.z != null;
    }
}
