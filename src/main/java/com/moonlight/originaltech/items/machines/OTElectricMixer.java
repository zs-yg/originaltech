package com.moonlight.originaltech.items.machines;

import com.moonlight.originaltech.items.materials.OTMaterials;
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

public class OTElectricMixer extends AContainer implements MachineProcessHolder<CraftingOperation>, RecipeDisplayItem {

    private static final int ENERGY_CONSUMPTION = 10;
    private static final int CAPACITY = 100;
    private static final int PROCESSING_TIME = 20;

    private final MachineProcessor<CraftingOperation> processor = new MachineProcessor<>(this);

    public static final SlimefunItemStack ELECTRIC_MIXER = new SlimefunItemStack(
        "OT_ELECTRIC_MIXER",
        Material.CAULDRON,
        "&6电动搅拌机",
        "",
        "&7将材料混合转换",
        "&7消耗: &e10 J/s",
        "&7储存: &6100 J",
        "",
        "&8石头 x4 > 下界岩",
        "&8沙子 x2 > 灵魂沙",
        "&8小麦 x4 > 下界疣"
    );

    public OTElectricMixer(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public MachineProcessor<CraftingOperation> getMachineProcessor() {
        return processor;
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.WATER_BUCKET);
    }

    @Override
    public void tick(Block block) {
        BlockMenu menu = BlockStorage.getInventory(block);
        if (menu == null) return;

        ItemStack input = null;
        int inputSlot = -1;
        
        for (int slot : getInputSlots()) {
            ItemStack item = menu.getItemInSlot(slot);
            if (item != null && (item.getType() == Material.STONE || item.getType() == Material.SAND || item.getType() == Material.WHEAT)) {
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

        ItemStack output = null;
        int requiredAmount = 0;

        switch (input.getType()) {
            case STONE:
                if (input.getAmount() >= 4) {
                    output = new ItemStack(Material.NETHERRACK);
                    requiredAmount = 4;
                }
                break;
            case SAND:
                if (input.getAmount() >= 2) {
                    output = new ItemStack(Material.SOUL_SAND);
                    requiredAmount = 2;
                }
                break;
            case WHEAT:
                if (input.getAmount() >= 4) {
                    output = new ItemStack(Material.NETHER_WART);
                    requiredAmount = 4;
                }
                break;
        }

        if (output == null) {
            return;
        }

        if (!menu.fits(output, getOutputSlots())) {
            return;
        }

        removeCharge(block.getLocation(), ENERGY_CONSUMPTION);
        input.setAmount(input.getAmount() - requiredAmount);
        menu.pushItem(output, getOutputSlots());
    }

    @Override
    public String getInventoryTitle() {
        return "电动搅拌机";
    }

    @Override
    public String getMachineIdentifier() {
        return "OT_ELECTRIC_MIXER";
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
        items.add(new ItemStack(Material.STONE, 4));
        items.add(new ItemStack(Material.NETHERRACK));
        items.add(new ItemStack(Material.SAND, 2));
        items.add(new ItemStack(Material.SOUL_SAND));
        items.add(new ItemStack(Material.WHEAT, 4));
        items.add(new ItemStack(Material.NETHER_WART));
        return items;
    }
}