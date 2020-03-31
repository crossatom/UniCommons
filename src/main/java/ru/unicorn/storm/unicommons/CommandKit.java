package ru.unicorn.storm.unicommons;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandKit implements CommandExecutor {
    private UniCommons plugin;

    public CommandKit(UniCommons Plugin) {
        plugin = Plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }
}
