package ru.unicorn.storm.unicommons;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import ru.unicorn.storm.unicommons.commands.CommandHome;
import ru.unicorn.storm.unicommons.commands.CommandSave;
import ru.unicorn.storm.unicommons.commands.CommandSpawn;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class UniCommons extends JavaPlugin implements Listener {

    private FileConfiguration config;
    private FileConfiguration players;

    @Override
    public void onEnable() {
        // Plugin startup logic
        if(!(new File(getDataFolder(), "config.yml").exists()))
            saveDefaultConfig();
        config = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "config.yml"));

        if(!(new File(getDataFolder(), "players.yml").exists()))
            saveResource("players.yml", false);
        players = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "players.yml"));

        // Register commands
        getCommand("spawn").setExecutor(new CommandSpawn(config));
        getCommand("home").setExecutor(new CommandHome(players));
        getCommand("save").setExecutor(new CommandSave(config, players, this));
        getCommand("kit").setExecutor(new CommandKit(this));

        // Register events
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent evt) {
        if(players.getString(evt.getPlayer().getDisplayName()) == null) {
            players.set(evt.getPlayer().getDisplayName() + ".online", true);
        }

        if(players.getBoolean(evt.getPlayer().getDisplayName() + ".banned")) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy-HH:mm");
            Date now;
            Date exp;

            try {
                now = sdf.parse(sdf.format(new Date()));
                exp = sdf.parse(players.getString(evt.getPlayer().getDisplayName() + ".ban-expire"));
            } catch (ParseException ex) {
                ex.printStackTrace();
                return;
            }

            if(exp.compareTo(now) < 0) {
                players.set(evt.getPlayer().getDisplayName() + ".banned", false);
                players.set(evt.getPlayer().getDisplayName() + ".ban-expire", false);
                evt.setJoinMessage(ChatColor.translateAlternateColorCodes('&', config.getString("connection.join").replace("{NICKNAME}", evt.getPlayer().getDisplayName())));
            } else {
                evt.setJoinMessage(null);

                long time = now.getTime() - exp.getTime();
                long days = time / (24 * 60 * 60 * 1000);
                long hours = time / (60 * 60 * 1000) % 24;
                long minutes = time / (60 * 1000) % 60;

                evt.getPlayer().kickPlayer("Вы забанены на этом сервере. Бан истекает через " + days + "д " +
                        hours + "ч " + minutes + "м");
            }
        } else {
            evt.setJoinMessage(ChatColor.translateAlternateColorCodes('&', config.getString("connection.join").replace("{NICKNAME}", evt.getPlayer().getDisplayName())));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent evt) {
        evt.setQuitMessage(ChatColor.translateAlternateColorCodes('&', config.getString("connection.quit").replace("{NICKNAME}", evt.getPlayer().getDisplayName())));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            config.save(new File(getDataFolder(), "config.yml"));
            players.save(new File(getDataFolder(), "players.yml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
