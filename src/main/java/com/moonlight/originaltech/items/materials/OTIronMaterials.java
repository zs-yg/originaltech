package com.moonlight.originaltech.items.materials;

import com.moonlight.originaltech.OriginalTech;
import com.moonlight.originaltech.setup.OTItemGroups;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class OTIronMaterials {

    public static final SlimefunItemStack SILICON = new SlimefunItemStack(
        "OT_SILICON",
        Material.QUARTZ_BLOCK,
        "&b原版硅"
    );

    public static final SlimefunItemStack HOT_IRON = createItem("OT_HOT_IRON", Material.IRON_INGOT, "§c精热铁", "§7经过高温加热炉处理后的铁");
    public static final SlimefunItemStack COMPRESSED_IRON = createItem("OT_COMPRESSED_IRON", Material.IRON_BLOCK, "§f压缩铁", "§7由10个精热铁压缩而成");
    public static final SlimefunItemStack COMPRESSED_HOT_IRON = createItem("OT_COMPRESSED_HOT_IRON", Material.RAW_IRON_BLOCK, "§6压缩精热铁", "§7由10个压缩铁加热而成");
    public static final SlimefunItemStack COMPRESSED_IRON_BLOCK = createItem("OT_COMPRESSED_IRON_BLOCK", Material.IRON_BLOCK, "§f压缩铁块", "§7由10个压缩精热铁压缩而成");
    public static final SlimefunItemStack CORE_HOT_IRON = createItem("OT_CORE_HOT_IRON", Material.NETHERITE_INGOT, "§6原版核心精热铁", "§7由压缩精热铁块加热而成");
    public static final SlimefunItemStack CORE_IRON_BLOCK = createItem("OT_CORE_IRON_BLOCK", Material.NETHERITE_BLOCK, "§e核心铁块", "§7由10个原版核心精热铁压缩而成");

    private static SlimefunItemStack createItem(String id, Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        if (lore.length > 0) {
            List<String> loreList = new ArrayList<>();
            for (String l : lore) {
                loreList.add(l);
            }
            meta.setLore(loreList);
        }
        item.setItemMeta(meta);
        return new SlimefunItemStack(id, item);
    }

    @SuppressWarnings("null")
    public static void setup(OriginalTech plugin) {
        new SlimefunItem(
            OTItemGroups.BASIC_MATERIALS_GROUP,
            SILICON,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                new ItemStack(Material.SAND), new ItemStack(Material.SAND), new ItemStack(Material.SAND),
                new ItemStack(Material.SAND), null, new ItemStack(Material.SAND),
                new ItemStack(Material.SAND), new ItemStack(Material.SAND), new ItemStack(Material.SAND)
            }
        ).register(plugin);
        plugin.getLogger().info("基础材料已加载 - 原版硅");

        registerItem(HOT_IRON, plugin, "基础材料已加载 - 精热铁");
        registerItem(COMPRESSED_IRON, plugin, "基础材料已加载 - 压缩铁");
        registerItem(COMPRESSED_HOT_IRON, plugin, "基础材料已加载 - 压缩精热铁");
        registerItem(COMPRESSED_IRON_BLOCK, plugin, "基础材料已加载 - 压缩铁块");
        registerItem(CORE_HOT_IRON, plugin, "基础材料已加载 - 原版核心精热铁");
        registerItem(CORE_IRON_BLOCK, plugin, "基础材料已加载 - 核心铁块");
    }

    private static void registerItem(SlimefunItemStack item, OriginalTech plugin, String logMessage) {
        new SlimefunItem(
            OTItemGroups.BASIC_MATERIALS_GROUP,
            item,
            RecipeType.NULL,
            new ItemStack[9]
        ).register(plugin);
        plugin.getLogger().info(logMessage);
    }
}