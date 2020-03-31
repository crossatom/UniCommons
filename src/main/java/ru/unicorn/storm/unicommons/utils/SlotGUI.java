package ru.unicorn.storm.unicommons.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class SlotGUI {
    private Plugin plugin;
    private Player player;
    private Inventory gui;

    public SlotGUI(Plugin Plugin, Player P, String Title, int Rows) {
        plugin = Plugin;
        player = P;
        gui = plugin.getServer().createInventory(player, 9 * Rows, Title);
    }
}
