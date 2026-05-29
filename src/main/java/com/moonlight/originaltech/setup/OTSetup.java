package com.moonlight.originaltech.setup;

import com.moonlight.originaltech.OriginalTech;
import com.moonlight.originaltech.items.materials.OTMaterials;
import com.moonlight.originaltech.items.machines.OTMachines;

public class OTSetup {

    public void setup(OriginalTech plugin) {
        plugin.getLogger().info("正在注册物品...");
        OTMaterials.setup(plugin);
        OTMachines.setup(plugin);
        plugin.getLogger().info("物品注册完成");
    }
}
