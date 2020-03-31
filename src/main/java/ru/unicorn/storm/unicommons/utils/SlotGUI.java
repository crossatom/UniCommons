package ru.unicorn.storm.unicommons.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import ru.unicorn.storm.unicommons.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SlotGUI implements Listener {
    private Plugin plugin;
    private Player player;
    private Inventory gui;
    private Map<Integer, SlotTask> tasks;

    public SlotGUI(Plugin Plugin, Player P, String Title, int Rows) {
        plugin = Plugin;
        player = P;
        gui = plugin.getServer().createInventory(player, 9 * Rows, Title);
        tasks = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void addItem(int x, int y, Material m, String name, BukkitRunnable callback, String... lore) {
        ItemStack item = new ItemStack(m);
        ItemMeta  meta = item.getItemMeta();

        meta.setDisplayName(Utils.colorize(name));
        ArrayList<String> lores = new ArrayList<>();
        Arrays.asList(lore).forEach(line -> lores.add(Utils.colorize(line)));

        meta.setLore(lores);
        item.setItemMeta(meta);

        if(callback != null) {
            tasks.put(
                    tasks.size(),
                    new SlotTask(
                            Utils.calcSlot(x, y),
                            callback
                    )
            );
        }

        gui.setItem(Utils.calcSlot(x, y), item);
    }

    public void open() {
        player.openInventory(gui);
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent evt) {
        evt.setCancelled(true);

        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).equals(evt.getRawSlot())) tasks.get(i).execute(plugin);
        }
    }
}
