package ru.unicorn.storm.unicommons.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Reflection {
    public void sendPacket(Player p, Object packet) {
        try {
            Object handle = p.getClass().getMethod("getHandle").invoke(p);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception ex) {}
    }

    public Class<?> getNMClass(String name) {
        try {
            return Class.forName("net.minecraft.server." +
                    Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
        } catch (ClassNotFoundException ex) {}
        return null;
    }
}
