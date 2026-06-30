package com.moonlight.originaltech.setup;

import com.moonlight.originaltech.OriginalTech;
import com.moonlight.originaltech.items.OTAuthorItem;
import com.moonlight.originaltech.items.materials.OTIronMaterials;
import com.moonlight.originaltech.items.materials.OTMaterials;
import com.moonlight.originaltech.items.machines.OTMachines;
import com.moonlight.originaltech.items.machines.advanced.OTAdvancedMachines;
import com.moonlight.originaltech.items.machines.farming.OTFarming;
import com.moonlight.originaltech.items.machines.generator.OTGenerators;
import com.moonlight.originaltech.items.tools.OTTools;

public class OTSetup {

    public void setup(OriginalTech plugin) {
        plugin.getLogger().info("正在初始化物品组...");
        OTItemGroups.initialize();
        
        plugin.getLogger().info("正在注册物品组...");
        OTItemGroups.register(plugin);
        
        plugin.getLogger().info("正在注册基础材料...");
        OTMaterials.setup(plugin);
        OTIronMaterials.setup(plugin);
        
        plugin.getLogger().info("正在注册基础机器...");
        OTMachines.setup(plugin);
        
        plugin.getLogger().info("正在注册高级机器...");
        OTAdvancedMachines.setup(plugin);
        
        plugin.getLogger().info("正在注册发电机...");
        OTGenerators.setup(plugin);
        
        plugin.getLogger().info("正在注册工具...");
        OTTools.setup(plugin);
        
        plugin.getLogger().info("正在注册生电机器...");
        OTFarming.setup(plugin);
        
        plugin.getLogger().info("正在注册作者信息...");
        OTAuthorItem authorItem = new OTAuthorItem();
        authorItem.register(plugin);
        
        plugin.getLogger().info("物品注册完成");
        plugin.getLogger().info("原版科技插件已完全加载！");
    }
}
