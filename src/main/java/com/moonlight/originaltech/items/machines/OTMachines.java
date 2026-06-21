package com.moonlight.originaltech.items.machines;

import com.moonlight.originaltech.OriginalTech;
import com.moonlight.originaltech.setup.OTItemGroups;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class OTMachines {

    public static void setup(OriginalTech plugin) {
        OTBasicMachines.setup(plugin);

        // 原版压力机 - 多方块结构
        // 配方以活塞为中心，玩家右键活塞触发
        OTCompressor compressor = new OTCompressor(
            OTItemGroups.BASIC_MACHINES_GROUP,
            OTCompressor.COMPRESSOR,
            new ItemStack[]{
                new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR),
                new ItemStack(Material.IRON_BARS), new ItemStack(Material.PISTON), new ItemStack(Material.IRON_BARS),
                new ItemStack(Material.AIR), new ItemStack(Material.DISPENSER), new ItemStack(Material.AIR)
            }
        );
        compressor.register(plugin);
        plugin.getLogger().info("基础机器已加载 - 原版压力机");
    }
}
