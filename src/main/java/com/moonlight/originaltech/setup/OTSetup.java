package com.moonlight.originaltech.setup;

import com.moonlight.originaltech.OriginalTech;
import com.moonlight.originaltech.items.materials.OTMaterials;
import com.moonlight.originaltech.items.machines.OTMachines;
import com.moonlight.originaltech.items.machines.advanced.OTAdvancedMachines;

public class OTSetup {

    public void setup(OriginalTech plugin) {
        plugin.getLogger().info("正在初始化物品组...");
        OTItemGroups.initialize();
        
        plugin.getLogger().info("正在注册物品组...");
        OTItemGroups.register(plugin);
        
        plugin.getLogger().info("正在注册物品...");
        OTMaterials.setup(plugin);
        OTMachines.setup(plugin);
        OTAdvancedMachines.setup(plugin);
        plugin.getLogger().info("物品注册完成");
    }
}