package com.moonlight.originaltech.items.machines;

import com.moonlight.originaltech.OriginalTech;
import com.moonlight.originaltech.items.materials.OTMaterials;
import com.moonlight.originaltech.setup.OTItemGroups;
import com.moonlight.originaltech.utils.RegisterUtils;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class OTBasicMachines {

    public static void setup(OriginalTech plugin) {
        // 原版矿机 - 8钻石镐围石头
        RegisterUtils.register(
            new OTOriginalMiner(OTItemGroups.BASIC_MACHINES_GROUP, OTOriginalMiner.ORIGINAL_MINER,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                RegisterUtils.surroundRecipe(new ItemStack(Material.STONE), new ItemStack(Material.DIAMOND_PICKAXE))),
            plugin, "原版矿机"
        );

        // 下界矿机 - 8下界合金镐围下界岩
        RegisterUtils.register(
            new OTNetherMiner(OTItemGroups.BASIC_MACHINES_GROUP, OTNetherMiner.NETHER_MINER,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                RegisterUtils.surroundRecipe(new ItemStack(Material.NETHERRACK), new ItemStack(Material.NETHERITE_PICKAXE))),
            plugin, "下界矿机"
        );

        // 末地矿机 - 8钻石镐围末地石
        RegisterUtils.register(
            new OTEndMiner(OTItemGroups.BASIC_MACHINES_GROUP, OTEndMiner.END_MINER,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                RegisterUtils.surroundRecipe(new ItemStack(Material.END_STONE), new ItemStack(Material.DIAMOND_PICKAXE))),
            plugin, "末地矿机"
        );

        // 原石加热工厂 - 原石和熔炉围红石块
        RegisterUtils.register(
            new OTCobblestoneHeater(OTItemGroups.BASIC_MACHINES_GROUP, OTCobblestoneHeater.COBBLESTONE_HEATER,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                RegisterUtils.recipe(
                    new ItemStack(Material.COBBLESTONE), new ItemStack(Material.FURNACE), new ItemStack(Material.COBBLESTONE),
                    new ItemStack(Material.FURNACE), new ItemStack(Material.REDSTONE_BLOCK), new ItemStack(Material.FURNACE),
                    new ItemStack(Material.COBBLESTONE), new ItemStack(Material.FURNACE), new ItemStack(Material.COBBLESTONE))),
            plugin, "原石加热工厂"
        );

        // 电动搅拌机 - 8原版硅围堆肥桶
        RegisterUtils.register(
            new OTElectricMixer(OTItemGroups.BASIC_MACHINES_GROUP, OTElectricMixer.ELECTRIC_MIXER,
                RecipeType.ENHANCED_CRAFTING_TABLE,
                RegisterUtils.surroundRecipe(SlimefunItems.COMPOSTER, OTMaterials.SILICON)),
            plugin, "电动搅拌机"
        );

        // 高温加热炉 - 多方块结构
        RegisterUtils.register(
            new OTHighTempHeater(OTItemGroups.BASIC_MACHINES_GROUP, OTHighTempHeater.HIGH_TEMP_HEATER,
                RegisterUtils.recipe(
                    new ItemStack(Material.AIR), new ItemStack(Material.BLAST_FURNACE), new ItemStack(Material.AIR),
                    OTMaterials.SILICON, new ItemStack(Material.DISPENSER), OTMaterials.SILICON,
                    new ItemStack(Material.AIR), new ItemStack(Material.FLINT_AND_STEEL), new ItemStack(Material.AIR))),
            plugin, "高温加热炉"
        );
    }
}
