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

public class OTMaterials {

    public static final SlimefunItemStack SILICON = new SlimefunItemStack(
        "OT_SILICON",
        Material.QUARTZ_BLOCK,
        "&b原版硅"
    );

    public static final SlimefunItemStack HOT_IRON = createHotIron();

    private static SlimefunItemStack createHotIron() {
        ItemStack item = new ItemStack(Material.IRON_INGOT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§c精热铁");
        List<String> lore = new ArrayList<>();
        lore.add("§7经过高温加热炉处理后的铁");
        lore.add("§7具有特殊的热能");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return new SlimefunItemStack("OT_HOT_IRON", item);
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

        new SlimefunItem(
            OTItemGroups.BASIC_MATERIALS_GROUP,
            HOT_IRON,
            RecipeType.NULL,
            new ItemStack[9]
        ).register(plugin);
        plugin.getLogger().info("基础材料已加载 - 精热铁");
    }
}