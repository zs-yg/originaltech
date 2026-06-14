package com.moonlight.originaltech.items.machines;

import com.moonlight.originaltech.OriginalTech;
import com.moonlight.originaltech.items.materials.OTMaterials;
import com.moonlight.originaltech.setup.OTItemGroups;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class OTBasicMachines {

    @SuppressWarnings("null")
    public static void setup(OriginalTech plugin) {
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

        OTCobblestoneHeater cobblestoneHeater = new OTCobblestoneHeater(
            OTItemGroups.BASIC_MACHINES_GROUP,
            OTCobblestoneHeater.COBBLESTONE_HEATER,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                new ItemStack(Material.COBBLESTONE), new ItemStack(Material.FURNACE), new ItemStack(Material.COBBLESTONE),
                new ItemStack(Material.FURNACE), new ItemStack(Material.REDSTONE_BLOCK), new ItemStack(Material.FURNACE),
                new ItemStack(Material.COBBLESTONE), new ItemStack(Material.FURNACE), new ItemStack(Material.COBBLESTONE)
            }
        );
        cobblestoneHeater.register(plugin);
        plugin.getLogger().info("基础机器已加载 - 原石加热工厂");

        OTElectricMixer electricMixer = new OTElectricMixer(
            OTItemGroups.BASIC_MACHINES_GROUP,
            OTElectricMixer.ELECTRIC_MIXER,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                OTMaterials.SILICON, OTMaterials.SILICON, OTMaterials.SILICON,
                OTMaterials.SILICON, io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.COMPOSTER, OTMaterials.SILICON,
                OTMaterials.SILICON, OTMaterials.SILICON, OTMaterials.SILICON
            }
        );
        electricMixer.register(plugin);
        plugin.getLogger().info("基础机器已加载 - 电动搅拌机");
    }
}
