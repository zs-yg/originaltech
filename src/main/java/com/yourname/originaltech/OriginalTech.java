package com.yourname.originaltech;

import com.yourname.originaltech.setup.OTSetup;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import org.bukkit.plugin.java.JavaPlugin;

public class OriginalTech extends JavaPlugin implements SlimefunAddon {

    private static OriginalTech instance;

    @Override
    public void onEnable() {
        instance = this;
        Config cfg = new Config(this);

        new OTSetup().setup(this);
    }

    @Override
    public void onDisable() {
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
