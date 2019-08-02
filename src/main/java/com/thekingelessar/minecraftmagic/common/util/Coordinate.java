package com.thekingelessar.minecraftmagic.common.util;

import net.minecraft.util.math.BlockPos;

public class Coordinate extends BlockPos
{
    public double xCoord;
    public double yCoord;
    public double zCoord;
    
    public Coordinate(double x, double y, double z)
    {
        super((int) x, (int) y, (int) z);
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
    }
    
    public double getXCoord()
    {
        return xCoord;
    }
    
    public double getYCoord()
    {
        return yCoord;
    }
    
    public double getZCoord()
    {
        return zCoord;
    }
}
