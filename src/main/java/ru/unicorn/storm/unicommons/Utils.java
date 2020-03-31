package ru.unicorn.storm.unicommons;

import org.bukkit.ChatColor;

public class Utils {
    public static String colorize(String m) {
        return ChatColor.translateAlternateColorCodes('&', m);
    }

    public static int calcSlot(int x, int y) {
        return 9 * y + x;
    }
}
