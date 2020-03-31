package ru.unicorn.storm.unicommons.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import ru.unicorn.storm.unicommons.UniCommons;

import java.io.File;
import java.io.IOException;

public class CommandSave implements CommandExecutor {
    private FileConfiguration config;
    private FileConfiguration players;
    private UniCommons plugin;

    public CommandSave(FileConfiguration Config, FileConfiguration Players, UniCommons Plugin) {
        config = Config;
        players = Players;
        plugin = Plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("§eСохранение данных...");

        try {
            config.save(new File(plugin.getDataFolder(), "config.yml"));
            players.save(new File(plugin.getDataFolder(), "players.yml"));

            sender.sendMessage("§aДанные сохранены");
        } catch (IOException ex) {
            sender.sendMessage("§cНе удалось сохранить данные");
            ex.printStackTrace();
        }

        return true;
    }
}
