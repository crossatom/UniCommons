package ru.unicorn.storm.unicommons.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import ru.unicorn.storm.unicommons.utils.Title;

public class CommandHome implements CommandExecutor {
    private FileConfiguration players;

    public CommandHome(FileConfiguration Players) {
        players = Players;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 2) return false;
        if(args[0] == "tp") {
            if(players.getString(((Player)sender).getDisplayName() + ".home." + args[1]) == null) {
                sender.sendMessage("§eУ Вас нет дома с названием §c" + args[1]);
            } else {
                double  x = players.getInt(((Player) sender).getDisplayName() + ".home." + args[1] + ".x"),
                        y = players.getInt(((Player) sender).getDisplayName() + ".home." + args[1] + ".y"),
                        z = players.getInt(((Player) sender).getDisplayName() + ".home." + args[1] + ".z");
                Location point = new Location(((Player) sender).getWorld(), x, y, z);
                new Title().send((Player) sender, "Перемещение", "Немного подождите", 1, 2, 1);
                ((Player) sender).teleport(point);
            }
        } else if(args[0] == "set") {
            if(players.getString(((Player) sender).getDisplayName() + ".home." + args[1]) != null) {
                sender.sendMessage("§eУ Вас уже есть дом с названием §c" + args[1]);
            } else {
                double  x = ((Player) sender).getLocation().getX(),
                        y = ((Player) sender).getLocation().getY(),
                        z = ((Player) sender).getLocation().getZ();

                players.set(((Player) sender).getDisplayName() + ".home." + args[1] + ".x", x);
                players.set(((Player) sender).getDisplayName() + ".home." + args[1] + ".y", y);
                players.set(((Player) sender).getDisplayName() + ".home." + args[1] + ".z", z);
            }
        } else if(args[0] == "del") {
            if(players.getString(((Player) sender).getDisplayName() + ".home." + args[1]) == null) {
                sender.sendMessage("§eУ Вас нет дома с названием §c" + args[1]);
            } else {
                players.set(((Player) sender).getDisplayName() + ".home." + args[1], null);
            }
        } else {
            return false;
        }

        return true;
    }
}
