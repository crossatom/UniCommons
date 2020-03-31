package ru.unicorn.storm.unicommons;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.unicorn.storm.unicommons.utils.SlotGUI;

public class CommandKit implements CommandExecutor {
    private UniCommons plugin;

    public CommandKit(UniCommons Plugin) {
        plugin = Plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        openMenu(((Player) sender));
        return true;
    }

    private void openMenu(Player p) {
        SlotGUI kits = new SlotGUI(plugin, p, "Наборы", 3);

        kits.addItem(2, 1, Material.CHEST, "&r&eОфициальные", new BukkitRunnable() {
            @Override
            public void run() {
                openOfficial(p);
            }
        }, "&eИгровые наборы от команды Unicorn Storm");
        kits.addItem(4, 1, Material.CAULDRON_ITEM, "&r&6Пользовательские", new BukkitRunnable() {
            @Override
            public void run() {
                openPlayers(p);
            }
        }, "&eНаборы от игроков");
        kits.addItem(6, 1, Material.STONE_PICKAXE, "&r&cСоздать", new BukkitRunnable() {
            @Override
            public void run() {
                p.closeInventory();
                p.sendMessage(Utils.colorize("&eСоздание набора: &c/kit create <название> <цена>"));
            }
        }, "&eСоздать свой набор", "&6До 9-ти предметов!");

        kits.open();
    }

    private void openOfficial(Player p) {
        SlotGUI kits = new SlotGUI(plugin, p, "Наборы > Официальные", 4);

        kits.addItem(4, 3, Material.BARRIER, "&r&eНазад", new BukkitRunnable() {
            @Override
            public void run() {
                openMenu(p);
            }
        }, "&fВернуться в меню наборов");

        kits.open();
    }

    private void openPlayers(Player p) {
        SlotGUI kits = new SlotGUI(plugin, p, "Наборы > Пользовательские", 4);

        kits.addItem(4, 3, Material.BARRIER, "&r&eНазад", new BukkitRunnable() {
            @Override
            public void run() {
                openMenu(p);
            }
        }, "&fВернуться в меню наборов");

        kits.open();
    }
}
