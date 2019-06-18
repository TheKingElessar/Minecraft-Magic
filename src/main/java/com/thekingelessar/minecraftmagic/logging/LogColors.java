package com.thekingelessar.minecraftmagic.logging;

public class LogColors
{
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    public static final String BLACK_BB = "\u001B[40m";
    public static final String RED_BB = "\u001B[41m";
    public static final String GREEN_BB = "\u001B[42m";
    public static final String YELLOW_BB = "\u001B[43m";
    public static final String BLUE_BB = "\u001B[44m";
    public static final String PURPLE_BB = "\u001B[45m";
    public static final String CYAN_BB = "\u001B[46m";
    public static final String WHITE_BB = "\u001B[47m";
    
    public static final String LOG_INFO = BLUE_BB + WHITE;
    public static final String LOG_WARNING = RED_BB + WHITE;
    
    public enum LogLevels
    {
        WARNING, // Bad stuff, use red background, white text
        INFO, // Normal logging, use green background, white text
    }
}
