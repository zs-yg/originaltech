package com.moonlight.originaltech.setup;

import com.moonlight.originaltech.OriginalTech;
import com.moonlight.originaltech.items.materials.OTMaterials;
import com.moonlight.originaltech.items.machines.OTMachines;
import com.moonlight.originaltech.items.machines.advanced.OTAdvancedMachines;
import com.moonlight.originaltech.items.machines.generator.OTGenerators;

public class OTSetup {

    public void setup(OriginalTech plugin) {
        plugin.getLogger().info("正在初始化物品组...");
        OTItemGroups.initialize();
        
        plugin.getLogger().info("正在注册物品组...");
        OTItemGroups.register(plugin);
        
        plugin.getLogger().info("正在注册基础材料...");
        OTMaterials.setup(plugin);
        
        plugin.getLogger().info("正在注册基础机器...");
        OTMachines.setup(plugin);
        
        plugin.getLogger().info("正在注册高级机器...");
        OTAdvancedMachines.setup(plugin);
        
        plugin.getLogger().info("正在注册发电机...");
        OTGenerators.setup(plugin);
        
        plugin.getLogger().info("物品注册完成");
        plugin.getLogger().info("原版科技插件已完全加载！");
    }
}
