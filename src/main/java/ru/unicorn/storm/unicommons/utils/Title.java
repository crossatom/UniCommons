package ru.unicorn.storm.unicommons.utils;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class Title extends Reflection {
    public void send(Player p, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        try {
            Object chatTitle = getNMClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class)
                    .invoke(null, "{\"text\": \"" + title + "\"}");
            Constructor<?> titleConstructor = getNMClass("PacketPlayOutTitle").getConstructor(
                    getNMClass("PacketPlayOutTitle").getDeclaredClasses()[0],
                    getNMClass("IChatBaseComponent"),
                    int.class,
                    int.class,
                    int.class
            );
            Object packet = titleConstructor.newInstance(
                    getNMClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null),
                    chatTitle,
                    fadeIn,
                    stay,
                    fadeOut
            );
            Object chatsTitle = getNMClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class)
                    .invoke(null, "{\"text\": \"" + subtitle + "\"}");
            Constructor<?> stitleConstructor = getNMClass("PacketPlayOutTitle").getConstructor(
                    getNMClass("PacketPlayOutTitle").getDeclaredClasses()[0],
                    getNMClass("IChatBaseComponent"),
                    int.class,
                    int.class,
                    int.class
            );
            Object spacket = stitleConstructor.newInstance(
                    getNMClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null),
                    chatsTitle,
                    fadeIn,
                    stay,
                    fadeOut
            );

            sendPacket(p, packet);
            sendPacket(p, spacket);
        } catch (Exception ex) {}
    }
}
