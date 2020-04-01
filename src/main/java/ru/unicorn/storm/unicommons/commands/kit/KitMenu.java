package ru.unicorn.storm.unicommons.commands.kit;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class KitMenu implements InventoryProvider {
    public static final SmartInventory gui = SmartInventory.builder()
            .id("menu")
            .provider(new KitMenu())
            .size(3, 9)
            .title(ChatColor.BLUE + "Наборы")
            .build();

    @Override
    public void init(Player p, InventoryContents contents) {
        contents.fillBorders(ClickableItem.empty(new ItemStack(Material.STAINED_GLASS)));
        contents.set(1, 2, ClickableItem.of(
                initItem(Material.CHEST, "&r&eОфициальные", "&fНаборы от команды Unicorn Storm"),
                e -> p.performCommand("kit off"))
        );
        contents.set(1, 4, ClickableItem.of(
                initItem(Material.ENDER_CHEST, "&r&6Пользовательские", "&fНаборы от наших игроков"),
                e -> p.performCommand("kit usr")
        ));
        contents.set(1, 6, ClickableItem.of(
                initItem(Material.STONE_PICKAXE, "&r&cСоздать", "&fСоздать новый набор"),
                e -> {
                    p.closeInventory();
                    p.sendMessage(ChatColor.YELLOW + "Переместите в нижний ряд инвентаря предметы, которые хотите превратить в набор");
                    p.sendMessage(ChatColor.GOLD + "После этого введите: " + ChatColor.BLUE + "/kit create <название> <цена>");
                }
        ));
    }

    @Override
    public void update(Player player, InventoryContents contents) {
        //
    }

    private ItemStack initItem(Material m, String label, String... lore) {
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', label));

        ArrayList<String> lores = new ArrayList<>();
        Arrays.asList(lore).forEach(line -> lores.add(ChatColor.translateAlternateColorCodes('&', line)));

        item.setItemMeta(meta);
        item.setLore(lores);

        return item;
    }
}
