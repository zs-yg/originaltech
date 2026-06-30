package com.moonlight.originaltech.setup;

import com.moonlight.originaltech.OriginalTech;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import net.md_5.bungee.api.ChatColor;

public class OTItemGroups {

    @SuppressWarnings("deprecation")
    public static ItemStack createIcon(Material material, String name) {
        ItemStack icon = new ItemStack(material);
        ItemMeta meta = icon.getItemMeta();
        meta.setDisplayName(name);
        icon.setItemMeta(meta);
        return icon;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createPlayerHead(String playerName, String name) {
        ItemStack icon = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) icon.getItemMeta();
        meta.setDisplayName(name);
        meta.setOwner(playerName);
        icon.setItemMeta(meta);
        return icon;
    }

    public static NestedItemGroup OT_ITEM_GROUP;
    public static SubItemGroup BASIC_MATERIALS_GROUP;
    public static SubItemGroup BASIC_MACHINES_GROUP;
    public static SubItemGroup ADVANCED_MACHINES_GROUP;
    public static SubItemGroup GENERATORS_GROUP;
    public static SubItemGroup TOOLS_GROUP;
    public static SubItemGroup AUTHOR_GROUP;
    public static SubItemGroup FARMING_GROUP;

    @SuppressWarnings("null")
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
        
        GENERATORS_GROUP = new SubItemGroup(
            new NamespacedKey(OriginalTech.getInstance(), "generators"),
            OT_ITEM_GROUP,
            createIcon(Material.REDSTONE_BLOCK, ChatColor.RED + "发电机")
        );
        
        TOOLS_GROUP = new SubItemGroup(
            new NamespacedKey(OriginalTech.getInstance(), "tools"),
            OT_ITEM_GROUP,
            createIcon(Material.NETHERITE_PICKAXE, ChatColor.AQUA + "工具")
        );
        
        FARMING_GROUP = new SubItemGroup(
            new NamespacedKey(OriginalTech.getInstance(), "farming"),
            OT_ITEM_GROUP,
            createIcon(Material.WATER_BUCKET, ChatColor.BLUE + "原版生电")
        );
        
        AUTHOR_GROUP = new SubItemGroup(
            new NamespacedKey(OriginalTech.getInstance(), "author"),
            OT_ITEM_GROUP,
            createPlayerHead("moonlight_awa", ChatColor.LIGHT_PURPLE + "作者")
        );
    }
    
    @SuppressWarnings("null")
    public static void register(SlimefunAddon addon) {
        OT_ITEM_GROUP.register(addon);
        BASIC_MATERIALS_GROUP.register(addon);
        BASIC_MACHINES_GROUP.register(addon);
        ADVANCED_MACHINES_GROUP.register(addon);
        GENERATORS_GROUP.register(addon);
        TOOLS_GROUP.register(addon);
        FARMING_GROUP.register(addon);
        AUTHOR_GROUP.register(addon);
    }
}