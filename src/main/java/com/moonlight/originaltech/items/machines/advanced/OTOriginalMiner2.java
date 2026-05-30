package com.moonlight.originaltech.items.machines.advanced;

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

public class OTOriginalMiner2 extends AContainer implements MachineProcessHolder<CraftingOperation>, RecipeDisplayItem {

    private static final int ENERGY_CONSUMPTION = 18;
    private static final int CAPACITY = 200;
    private static final int PROCESSING_TIME = 20;

    private static final Map<Material, Integer> ORE_WEIGHTS = new HashMap<>();
    static {
        ORE_WEIGHTS.put(Material.LAPIS_LAZULI, 40);
        ORE_WEIGHTS.put(Material.REDSTONE, 35);
        ORE_WEIGHTS.put(Material.GOLD_INGOT, 20);
        ORE_WEIGHTS.put(Material.DIAMOND, 5);
    }

    private static final int TOTAL_WEIGHT = ORE_WEIGHTS.values().stream().mapToInt(Integer::intValue).sum();

    private final MachineProcessor<CraftingOperation> processor = new MachineProcessor<>(this);

    public static final SlimefunItemStack ORIGINAL_MINER_2 = new SlimefunItemStack(
        "OT_ORIGINAL_MINER_2",
        Material.DIAMOND_BLOCK,
        "&b原版矿机 2.0",
        "",
        "&7升级版原版矿机",
        "&7消耗: &e石头 + 18 J/s",
        "",
        "&8青金石 > 红石 > 金 > 钻石"
    );

    public OTOriginalMiner2(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
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

        ItemStack input = null;
        int inputSlot = -1;
        for (int slot : getInputSlots()) {
            ItemStack item = menu.getItemInSlot(slot);
            if (item != null && item.getType() == Material.STONE) {
                input = item;
                inputSlot = slot;
                break;
            }
        }

        if (input == null || inputSlot == -1) {
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
        return new ItemStack(Material.LAPIS_LAZULI);
    }

    @Override
    public String getInventoryTitle() {
        return "原版矿机 2.0";
    }

    @Override
    public String getMachineIdentifier() {
        return "OT_ORIGINAL_MINER_2";
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
