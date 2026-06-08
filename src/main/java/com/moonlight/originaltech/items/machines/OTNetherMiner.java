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

@SuppressWarnings("unused")
public class OTNetherMiner extends AContainer implements MachineProcessHolder<CraftingOperation>, RecipeDisplayItem {

    private static final int ENERGY_CONSUMPTION = 20;
    private static final int CAPACITY = 256;
    private static final int PROCESSING_TIME = 20;

    private static final Map<Material, Integer> ORE_WEIGHTS = new HashMap<>();
    static {
        ORE_WEIGHTS.put(Material.GLOWSTONE, 4000);
        ORE_WEIGHTS.put(Material.QUARTZ, 4000);
        ORE_WEIGHTS.put(Material.GOLD_NUGGET, 1999);
        ORE_WEIGHTS.put(Material.NETHERITE_INGOT, 1);
    }

    private static final int TOTAL_WEIGHT = ORE_WEIGHTS.values().stream().mapToInt(Integer::intValue).sum();

    private final MachineProcessor<CraftingOperation> processor = new MachineProcessor<>(this);

    public static final SlimefunItemStack NETHER_MINER = new SlimefunItemStack(
        "OT_NETHER_MINER",
        Material.NETHERRACK,
        "&c下界矿机",
        "",
        "&7将下界岩转化为下界矿物",
        "&7消耗: &e下界岩 + 20 J/s",
        "",
        "&8萤石 > 下界石英 > 金粒 > 下界合金锭(0.01%)"
    );

    public OTNetherMiner(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @SuppressWarnings("null")
    @Override
    public MachineProcessor<CraftingOperation> getMachineProcessor() {
        return processor;
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.NETHERITE_PICKAXE);
    }

    @SuppressWarnings("null")
    @Override
    public void tick(Block block) {
        BlockMenu menu = BlockStorage.getInventory(block);
        if (menu == null) return;

        ItemStack input = null;
        int inputSlot = -1;
        for (int slot : getInputSlots()) {
            ItemStack item = menu.getItemInSlot(slot);
            if (item != null && item.getType() == Material.NETHERRACK) {
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
        return new ItemStack(Material.GLOWSTONE);
    }

    @SuppressWarnings("null")
    @Override
    public String getInventoryTitle() {
        return "下界矿机";
    }

    @SuppressWarnings("null")
    @Override
    public String getMachineIdentifier() {
        return "OT_NETHER_MINER";
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

    @SuppressWarnings("null")
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> items = new ArrayList<>();
        
        for (Map.Entry<Material, Integer> entry : ORE_WEIGHTS.entrySet()) {
            items.add(new ItemStack(Material.NETHERRACK));
            items.add(new ItemStack(entry.getKey()));
        }
        
        return items;
    }
}