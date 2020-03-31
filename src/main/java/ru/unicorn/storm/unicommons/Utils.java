package ru.unicorn.storm.unicommons;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {
    public static String colorize(String m) {
        String ret = ChatColor.translateAlternateColorCodes('&', m);
        return ret;
    }
}
