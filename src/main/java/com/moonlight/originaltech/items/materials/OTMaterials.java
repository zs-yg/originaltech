package com.moonlight.originaltech.items.materials;

import com.moonlight.originaltech.OriginalTech;
import com.moonlight.originaltech.setup.OTItemGroups;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class OTMaterials {

    public static final SlimefunItemStack SILICON = new SlimefunItemStack(
        "OT_SILICON",
        Material.QUARTZ_BLOCK,
        "&b原版硅"
    );

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
    }
}
