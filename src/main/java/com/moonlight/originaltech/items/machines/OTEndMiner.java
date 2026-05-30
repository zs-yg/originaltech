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

public class OTEndMiner extends AContainer implements MachineProcessHolder<CraftingOperation>, RecipeDisplayItem {

    private static final int ENERGY_CONSUMPTION = 25;
    private static final int CAPACITY = 320;
    private static final int PROCESSING_TIME = 20;

    private static final Map<Material, Integer> ORE_WEIGHTS = new HashMap<>();
    static {
        ORE_WEIGHTS.put(Material.CHORUS_FLOWER, 70);
        ORE_WEIGHTS.put(Material.OBSIDIAN, 29);
        ORE_WEIGHTS.put(Material.DRAGON_BREATH, 1);
    }

    private static final int TOTAL_WEIGHT = ORE_WEIGHTS.values().stream().mapToInt(Integer::intValue).sum();

    private final MachineProcessor<CraftingOperation> processor = new MachineProcessor<>(this);

    public static final SlimefunItemStack END_MINER = new SlimefunItemStack(
        "OT_END_MINER",
        Material.END_STONE,
        "&5末地矿机",
        "",
        "&7将末地石转化为末地矿物",
        "&7消耗: &e末地石 + 25 J/s",
        "",
        "&8紫颂花 > 黑曜石 > 龙息"
    );

    public OTEndMiner(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
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
            if (item != null && item.getType() == Material.END_STONE) {
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
        return new ItemStack(Material.CHORUS_FLOWER);
    }

    @Override
    public String getInventoryTitle() {
        return "末地矿机";
    }

    @Override
    public String getMachineIdentifier() {
        return "OT_END_MINER";
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
            items.add(new ItemStack(Material.END_STONE));
            items.add(new ItemStack(entry.getKey()));
        }
        
        return items;
    }
}
