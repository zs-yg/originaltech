package com.moonlight.originaltech.items.machines;

import com.moonlight.originaltech.OriginalTech;
import com.moonlight.originaltech.items.materials.OTMaterials;
import com.moonlight.originaltech.setup.OTItemGroups;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class OTBasicMachines {

    public static final SlimefunItemStack ORIGINAL_WORKBENCH = new SlimefunItemStack(
        "OT_ORIGINAL_WORKBENCH",
        Material.CRAFTING_TABLE,
        "&e原版？工作台"
    );

    public static void setup(OriginalTech plugin) {
        new SlimefunItem(
            OTItemGroups.BASIC_MACHINES_GROUP,
            ORIGINAL_WORKBENCH,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                OTMaterials.SILICON, new ItemStack(Material.DIAMOND), OTMaterials.SILICON,
                new ItemStack(Material.DIAMOND), new ItemStack(Material.CRAFTING_TABLE), new ItemStack(Material.DIAMOND),
                OTMaterials.SILICON, new ItemStack(Material.DIAMOND), OTMaterials.SILICON
            }
        ).register(plugin);
        plugin.getLogger().info("基础机器已加载 - 原版？工作台");
    }
}
