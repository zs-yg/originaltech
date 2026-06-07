package com.moonlight.originaltech.items.machines.generator;

import com.moonlight.originaltech.OriginalTech;
import com.moonlight.originaltech.setup.OTItemGroups;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class OTGenerators {

    public static void setup(OriginalTech plugin) {
        OTWaterFlowGenerator generator = new OTWaterFlowGenerator(
            OTItemGroups.GENERATORS_GROUP,
            OTWaterFlowGenerator.WATER_FLOW_GENERATOR,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.WATER_BUCKET),
                new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.DISPENSER), new ItemStack(Material.WATER_BUCKET),
                new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.WATER_BUCKET)
            }
        );
        generator.register(plugin);
        plugin.getLogger().info("发电机已加载 - 水流发电机");
    }
}
