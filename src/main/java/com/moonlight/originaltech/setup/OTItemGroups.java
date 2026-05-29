package com.moonlight.originaltech.setup;

import com.moonlight.originaltech.OriginalTech;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.md_5.bungee.api.ChatColor;

public class OTItemGroups {

    public static final ItemStack OT_ICON;
    static {
        OT_ICON = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta meta = OT_ICON.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "原版科技");
        OT_ICON.setItemMeta(meta);
    }

    public static final ItemGroup OT_ITEM_GROUP = new ItemGroup(
        new NamespacedKey(OriginalTech.getInstance(), "original_tech"),
        OT_ICON
    );

    public static final ItemStack BASIC_MATERIALS_ICON;
    static {
        BASIC_MATERIALS_ICON = new ItemStack(Material.OAK_LOG);
        ItemMeta meta = BASIC_MATERIALS_ICON.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "基础材料");
        BASIC_MATERIALS_ICON.setItemMeta(meta);
    }

    public static final ItemGroup BASIC_MATERIALS_GROUP = new ItemGroup(
        new NamespacedKey(OriginalTech.getInstance(), "basic_materials"),
        BASIC_MATERIALS_ICON
    );

    public static final ItemStack BASIC_MACHINES_ICON;
    static {
        BASIC_MACHINES_ICON = new ItemStack(Material.FURNACE);
        ItemMeta meta = BASIC_MACHINES_ICON.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "基础机器");
        BASIC_MACHINES_ICON.setItemMeta(meta);
    }

    public static final ItemGroup BASIC_MACHINES_GROUP = new ItemGroup(
        new NamespacedKey(OriginalTech.getInstance(), "basic_machines"),
        BASIC_MACHINES_ICON
    );
}
