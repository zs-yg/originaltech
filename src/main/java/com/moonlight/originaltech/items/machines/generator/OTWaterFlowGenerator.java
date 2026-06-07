package com.moonlight.originaltech.items.machines.generator;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class OTWaterFlowGenerator extends SlimefunItem implements EnergyNetProvider, RecipeDisplayItem {

    private static final int ENERGY_PRODUCTION = 5;
    private static final int CAPACITY = 60;

    public static final SlimefunItemStack WATER_FLOW_GENERATOR = new SlimefunItemStack(
        "OT_WATER_FLOW_GENERATOR",
        Material.LIGHT_BLUE_STAINED_GLASS,
        "&9水流发电机",
        "",
        "&7利用水流发电",
        "&7产能: &e5 J/s",
        "&7储存: &660 J"
    );

    public OTWaterFlowGenerator(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public int getGeneratedOutput(Location location, Config config) {
        return ENERGY_PRODUCTION;
    }

    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> items = new ArrayList<>();
        items.add(new ItemStack(Material.AIR));
        items.add(new ItemStack(Material.REDSTONE));
        return items;
    }
}
