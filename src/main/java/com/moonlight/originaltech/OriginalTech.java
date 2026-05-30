package com.moonlight.originaltech;

import com.moonlight.originaltech.setup.OTSetup;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import org.bukkit.plugin.java.JavaPlugin;

public class OriginalTech extends JavaPlugin implements SlimefunAddon {

    private static OriginalTech instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("原版科技插件正在加载...");
        Config cfg = new Config(this);

        new OTSetup().setup(this);
        
        getLogger().info("原版科技插件加载成功！");
    }

    @Override
    public void onDisable() {
        getLogger().info("原版科技插件已卸载");
        instance = null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return null;
    }

    public static OriginalTech getInstance() {
        return instance;
    }
}