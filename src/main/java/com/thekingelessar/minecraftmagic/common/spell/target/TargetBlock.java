package com.thekingelessar.minecraftmagic.common.spell.target;

public class TargetBlock implements ITarget {
    public Double x;
    public Double y;
    public Double z;

    public TargetBlock(Double x, Double y, Double z) {
        if (x != null && y != null && z != null) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public boolean isNotNull() {
        return this.x != null && this.y != null && this.z != null;
    }
}
