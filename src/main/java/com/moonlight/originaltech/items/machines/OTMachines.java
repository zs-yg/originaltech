package com.moonlight.originaltech.items.machines;

import com.moonlight.originaltech.OriginalTech;
import com.moonlight.originaltech.setup.OTItemGroups;
import com.moonlight.originaltech.utils.RegisterUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class OTMachines {

    public static void setup(OriginalTech plugin) {
        OTBasicMachines.setup(plugin);

        // 原版压力机 - 多方块结构
        // 结构：活塞上方有活塞（可点击），左右铁栅栏，下方发射器
        RegisterUtils.register(
            new OTCompressor(OTItemGroups.BASIC_MACHINES_GROUP, OTCompressor.COMPRESSOR,
                RegisterUtils.recipe(
                    new ItemStack(Material.AIR), new ItemStack(Material.PISTON), new ItemStack(Material.AIR),
                    new ItemStack(Material.IRON_BARS), new ItemStack(Material.PISTON), new ItemStack(Material.IRON_BARS),
                    new ItemStack(Material.AIR), new ItemStack(Material.DISPENSER), new ItemStack(Material.AIR))),
            plugin, "原版压力机"
        );
    }
}
