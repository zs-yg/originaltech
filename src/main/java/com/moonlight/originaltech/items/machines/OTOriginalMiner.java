package com.moonlight.originaltech.items.machines;

import com.moonlight.originaltech.setup.OTItemGroups;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineProcessHolder;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineProcessor;
import io.github.thebusybiscuit.slimefun4.implementation.operations.CraftingOperation;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class OTOriginalMiner extends AContainer implements MachineProcessHolder<CraftingOperation>, RecipeDisplayItem {

    private static final int ENERGY_CONSUMPTION = 10;
    private static final int CAPACITY = 128;
    private static final int PROCESSING_TIME = 20;

    private static final Map<Material, Integer> ORE_WEIGHTS = new HashMap<>();
    static {
        ORE_WEIGHTS.put(Material.COPPER_INGOT, 40);
        ORE_WEIGHTS.put(Material.COAL, 30);
        ORE_WEIGHTS.put(Material.IRON_INGOT, 20);
        ORE_WEIGHTS.put(Material.LAPIS_LAZULI, 8);
        ORE_WEIGHTS.put(Material.DIAMOND, 2);
        ORE_WEIGHTS.put(Material.EMERALD, 1);
    }

    private static final int TOTAL_WEIGHT = ORE_WEIGHTS.values().stream().mapToInt(Integer::intValue).sum();

    private final MachineProcessor<CraftingOperation> processor = new MachineProcessor<>(this);

    public static final SlimefunItemStack ORIGINAL_MINER = new SlimefunItemStack(
        "OT_ORIGINAL_MINER",
        Material.STONE,
        "&e原版矿机",
        "",
        "&7将石头转化为原版矿物",
        "&7消耗: &e石头 + 10 J/s",
        "",
        "&8铜 > 煤炭 > 铁 > 青金石 > 钻石 > 绿宝石"
    );

    public OTOriginalMiner(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public MachineProcessor<CraftingOperation> getMachineProcessor() {
        return processor;
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.DIAMOND_PICKAXE);
    }

    @Override
    public void tick(Block block) {
        BlockMenu menu = BlockStorage.getInventory(block);
        if (menu == null) return;

        ItemStack input = menu.getItemInSlot(getInputSlots()[0]);
        if (input == null || input.getType() != Material.STONE) {
            return;
        }

        if (getCharge(block.getLocation()) < ENERGY_CONSUMPTION) {
            return;
        }

        removeCharge(block.getLocation(), ENERGY_CONSUMPTION);

        ItemStack ore = getRandomOre();

        if (!menu.fits(ore, getOutputSlots())) {
            return;
        }

        input.setAmount(input.getAmount() - 1);
        menu.pushItem(ore, getOutputSlots());
    }

    private ItemStack getRandomOre() {
        Random random = ThreadLocalRandom.current();
        int value = random.nextInt(TOTAL_WEIGHT) + 1;
        
        int cumulative = 0;
        for (Map.Entry<Material, Integer> entry : ORE_WEIGHTS.entrySet()) {
            cumulative += entry.getValue();
            if (value <= cumulative) {
                return new ItemStack(entry.getKey());
            }
        }
        return new ItemStack(Material.EMERALD);
    }

    @Override
    public String getInventoryTitle() {
        return "原版矿机";
    }

    @Override
    public String getMachineIdentifier() {
        return "OT_ORIGINAL_MINER";
    }

    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    @Override
    public int getEnergyConsumption() {
        return ENERGY_CONSUMPTION;
    }

    @Override
    public int getSpeed() {
        return PROCESSING_TIME;
    }

    @Override
    protected void registerDefaultRecipes() {
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> items = new ArrayList<>();
        
        for (Map.Entry<Material, Integer> entry : ORE_WEIGHTS.entrySet()) {
            items.add(new ItemStack(Material.STONE));
            items.add(new ItemStack(entry.getKey()));
        }
        
        return items;
    }
}
