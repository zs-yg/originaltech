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

        OTOriginalMiner miner = new OTOriginalMiner(
            OTItemGroups.BASIC_MACHINES_GROUP,
            OTOriginalMiner.ORIGINAL_MINER,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                new ItemStack(Material.DIAMOND_PICKAXE), new ItemStack(Material.DIAMOND_PICKAXE), new ItemStack(Material.DIAMOND_PICKAXE),
                new ItemStack(Material.DIAMOND_PICKAXE), new ItemStack(Material.STONE), new ItemStack(Material.DIAMOND_PICKAXE),
                new ItemStack(Material.DIAMOND_PICKAXE), new ItemStack(Material.DIAMOND_PICKAXE), new ItemStack(Material.DIAMOND_PICKAXE)
            }
        );
        miner.register(plugin);
        plugin.getLogger().info("基础机器已加载 - 原版矿机");

        OTNetherMiner netherMiner = new OTNetherMiner(
            OTItemGroups.BASIC_MACHINES_GROUP,
            OTNetherMiner.NETHER_MINER,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                new ItemStack(Material.NETHERITE_PICKAXE), new ItemStack(Material.NETHERITE_PICKAXE), new ItemStack(Material.NETHERITE_PICKAXE),
                new ItemStack(Material.NETHERITE_PICKAXE), new ItemStack(Material.NETHERRACK), new ItemStack(Material.NETHERITE_PICKAXE),
                new ItemStack(Material.NETHERITE_PICKAXE), new ItemStack(Material.NETHERITE_PICKAXE), new ItemStack(Material.NETHERITE_PICKAXE)
            }
        );
        netherMiner.register(plugin);
        plugin.getLogger().info("基础机器已加载 - 下界矿机");

        OTEndMiner endMiner = new OTEndMiner(
            OTItemGroups.BASIC_MACHINES_GROUP,
            OTEndMiner.END_MINER,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                new ItemStack(Material.DIAMOND_PICKAXE), new ItemStack(Material.DIAMOND_PICKAXE), new ItemStack(Material.DIAMOND_PICKAXE),
                new ItemStack(Material.DIAMOND_PICKAXE), new ItemStack(Material.END_STONE), new ItemStack(Material.DIAMOND_PICKAXE),
                new ItemStack(Material.DIAMOND_PICKAXE), new ItemStack(Material.DIAMOND_PICKAXE), new ItemStack(Material.DIAMOND_PICKAXE)
            }
        );
        endMiner.register(plugin);
        plugin.getLogger().info("基础机器已加载 - 末地矿机");
    }
}
