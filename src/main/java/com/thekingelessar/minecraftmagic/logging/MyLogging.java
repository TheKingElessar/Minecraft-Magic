package com.thekingelessar.minecraftmagic.logging;

import com.thekingelessar.minecraftmagic.MinecraftMagic;

import java.util.logging.Level;

public class MyLogging
{
    public static void logInfo(String string) {
        MinecraftMagic.logger.log(Level.INFO, LogColors.LOG_INFO + string + LogColors.RESET);
    }
    
    public static void logVariable(String name, Object variable) {
        MinecraftMagic.logger.log(Level.INFO, LogColors.LOG_INFO + name + ": " + variable + LogColors.RESET);
    }
    
    public static void logWarning(String string) {
        MinecraftMagic.logger.log(Level.WARNING, LogColors.LOG_WARNING + string + LogColors.RESET);
    }
    
}
