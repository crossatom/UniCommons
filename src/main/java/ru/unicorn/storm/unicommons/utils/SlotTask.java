package ru.unicorn.storm.unicommons.utils;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SlotTask {
    private int id;
    private BukkitRunnable task;

    public SlotTask(int Id, BukkitRunnable Task) {
        id = Id;
        task = Task;
    }

    public boolean equals(int slot) {
        return id == slot;
    }

    public void execute(Plugin plugin) {
        try {
            if(!task.isCancelled()) task.cancel();
            task.runTask(plugin);
        } catch (Exception ex) {
            task.runTask(plugin);
        }
    }
}
