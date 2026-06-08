package com.moonlight.originaltech.items.machines;

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
import java.util.List;

public class OTCobblestoneHeater extends AContainer implements MachineProcessHolder<CraftingOperation>, RecipeDisplayItem {

    private static final int ENERGY_CONSUMPTION = 40;
    private static final int CAPACITY = 200;
    private static final int PROCESSING_TIME = 20;

    private final MachineProcessor<CraftingOperation> processor = new MachineProcessor<>(this);

    public static final SlimefunItemStack COBBLESTONE_HEATER = new SlimefunItemStack(
        "OT_COBBLESTONE_HEATER",
        Material.FURNACE,
        "&c原石加热工厂",
        "",
        "&7将原石加热生成石头",
        "&7消耗: &e40 J/s",
        "&7储存: &6200 J"
    );

    public OTCobblestoneHeater(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public MachineProcessor<CraftingOperation> getMachineProcessor() {
        return processor;
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.FLINT_AND_STEEL);
    }

    @Override
    public void tick(Block block) {
        BlockMenu menu = BlockStorage.getInventory(block);
        if (menu == null) return;

        // 检查所有输入槽，找到有原石的槽
        ItemStack input = null;
        int inputSlot = -1;
        for (int slot : getInputSlots()) {
            ItemStack item = menu.getItemInSlot(slot);
            if (item != null && item.getType() == Material.COBBLESTONE) {
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

        ItemStack stone = new ItemStack(Material.STONE);

        if (!menu.fits(stone, getOutputSlots())) {
            return;
        }

        input.setAmount(input.getAmount() - 1);
        menu.pushItem(stone, getOutputSlots());
    }

    @Override
    public String getInventoryTitle() {
        return "原石加热工厂";
    }

    @Override
    public String getMachineIdentifier() {
        return "OT_COBBLESTONE_HEATER";
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
        items.add(new ItemStack(Material.COBBLESTONE));
        items.add(new ItemStack(Material.STONE));
        return items;
    }
}