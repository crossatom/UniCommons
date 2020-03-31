package ru.unicorn.storm.unicommons.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import ru.unicorn.storm.unicommons.UniCommons;
import ru.unicorn.storm.unicommons.utils.Title;

public class CommandSpawn implements CommandExecutor {
    private FileConfiguration config;

    public CommandSpawn(FileConfiguration Config) {
        config = Config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        new Title().send((Player) sender, "Перемещение", "Немного подождите", 1, 2, 1);
        double  x = config.getInt("spawn.x"),
                y = config.getInt("spawn.y"),
                z = config.getInt("spawn.z");
        Location spawn = new Location(((Player) sender).getWorld(), x, y, z);
        ((Player) sender).teleport(spawn);
        return true;
    }
}
