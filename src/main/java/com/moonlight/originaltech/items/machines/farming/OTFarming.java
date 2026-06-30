package com.moonlight.originaltech.items.machines.farming;

import com.moonlight.originaltech.OriginalTech;
import com.moonlight.originaltech.setup.OTItemGroups;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class OTFarming {

    public static void setup(OriginalTech plugin) {
        new OTStringFarm(
            OTItemGroups.FARMING_GROUP,
            OTStringFarm.STRING_FARM,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                new ItemStack(Material.SMOOTH_STONE), new ItemStack(Material.OAK_TRAPDOOR), new ItemStack(Material.LEVER),
                new ItemStack(Material.TRIPWIRE_HOOK), new ItemStack(Material.STRING), new ItemStack(Material.TRIPWIRE_HOOK),
                new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.HOPPER), new ItemStack(Material.WATER_BUCKET)
            }
        ).register(plugin);
        
        new OTTNTGenerator(
            OTItemGroups.FARMING_GROUP,
            OTTNTGenerator.TNT_GENERATOR,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                new ItemStack(Material.REDSTONE), new ItemStack(Material.REDSTONE), new ItemStack(Material.REDSTONE),
                new ItemStack(Material.OBSERVER), new ItemStack(Material.SMOOTH_STONE_SLAB), new ItemStack(Material.SMOOTH_STONE),
                new ItemStack(Material.PISTON), new ItemStack(Material.TNT), new ItemStack(Material.PISTON)

            }
        ).register(plugin);
        
        plugin.getLogger().info("生电机器已加载 - 1.21.1刷线机");
        plugin.getLogger().info("生电机器已加载 - TNT复制机");
    }
}
