package com.moonlight.originaltech.setup;

import com.moonlight.originaltech.OriginalTech;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.md_5.bungee.api.ChatColor;

public class OTItemGroups {

    public static ItemStack createIcon(Material material, String name) {
        ItemStack icon = new ItemStack(material);
        ItemMeta meta = icon.getItemMeta();
        meta.setDisplayName(name);
        icon.setItemMeta(meta);
        return icon;
    }

    public static NestedItemGroup OT_ITEM_GROUP;
    public static SubItemGroup BASIC_MATERIALS_GROUP;
    public static SubItemGroup BASIC_MACHINES_GROUP;
    public static SubItemGroup ADVANCED_MACHINES_GROUP;

    public static void initialize() {
        OT_ITEM_GROUP = new NestedItemGroup(
            new NamespacedKey(OriginalTech.getInstance(), "original_tech"),
            createIcon(Material.GRASS_BLOCK, ChatColor.GREEN + "原版科技")
        );

        BASIC_MATERIALS_GROUP = new SubItemGroup(
            new NamespacedKey(OriginalTech.getInstance(), "basic_materials"),
            OT_ITEM_GROUP,
            createIcon(Material.OAK_LOG, ChatColor.YELLOW + "基础材料")
        );

        BASIC_MACHINES_GROUP = new SubItemGroup(
            new NamespacedKey(OriginalTech.getInstance(), "basic_machines"),
            OT_ITEM_GROUP,
            createIcon(Material.FURNACE, ChatColor.GOLD + "基础机器")
        );

        ADVANCED_MACHINES_GROUP = new SubItemGroup(
            new NamespacedKey(OriginalTech.getInstance(), "advanced_machines"),
            OT_ITEM_GROUP,
            createIcon(Material.ANVIL, ChatColor.DARK_PURPLE + "高级机器")
        );
    }
    
    public static void register(SlimefunAddon addon) {
        OT_ITEM_GROUP.register(addon);
        BASIC_MATERIALS_GROUP.register(addon);
        BASIC_MACHINES_GROUP.register(addon);
        ADVANCED_MACHINES_GROUP.register(addon);
    }
}