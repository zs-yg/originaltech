package com.moonlight.originaltech.items.machines.advanced;

import com.moonlight.originaltech.OriginalTech;
import com.moonlight.originaltech.items.machines.OTOriginalMiner;
import com.moonlight.originaltech.items.materials.OTMaterials;
import com.moonlight.originaltech.setup.OTItemGroups;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class OTAdvancedMachines {

    public static void setup(OriginalTech plugin) {
        OTOriginalMiner2 miner2 = new OTOriginalMiner2(
            OTItemGroups.ADVANCED_MACHINES_GROUP,
            OTOriginalMiner2.ORIGINAL_MINER_2,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                new ItemStack(Material.DIAMOND_BLOCK), new ItemStack(Material.DIAMOND_BLOCK), new ItemStack(Material.DIAMOND_BLOCK),
                new ItemStack(Material.DIAMOND_BLOCK), OTOriginalMiner.ORIGINAL_MINER, new ItemStack(Material.DIAMOND_BLOCK),
                new ItemStack(Material.DIAMOND_BLOCK), new ItemStack(Material.DIAMOND_BLOCK), new ItemStack(Material.DIAMOND_BLOCK)
            }
        );
        miner2.register(plugin);
        plugin.getLogger().info("高级机器已加载 - 原版矿机 2.0");

        OTOriginalMiner3 miner3 = new OTOriginalMiner3(
            OTItemGroups.ADVANCED_MACHINES_GROUP,
            OTOriginalMiner3.ORIGINAL_MINER_3,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                OTOriginalMiner2.ORIGINAL_MINER_2, OTOriginalMiner2.ORIGINAL_MINER_2, OTOriginalMiner2.ORIGINAL_MINER_2,
                OTOriginalMiner2.ORIGINAL_MINER_2, new ItemStack(Material.DIAMOND_PICKAXE), OTOriginalMiner2.ORIGINAL_MINER_2,
                OTOriginalMiner2.ORIGINAL_MINER_2, OTOriginalMiner2.ORIGINAL_MINER_2, OTOriginalMiner2.ORIGINAL_MINER_2
            }
        );
        miner3.register(plugin);
        plugin.getLogger().info("高级机器已加载 - 原版矿机 3.0");
    }
}
