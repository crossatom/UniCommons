package ru.unicorn.storm.unicommons.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.unicorn.storm.unicommons.UniCommons;
import ru.unicorn.storm.unicommons.commands.kit.KitMenu;

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
        new KitMenu();
    }

    private void openOfficial(Player p) {

    }

    private void openPlayers(Player p) {

    }
}
