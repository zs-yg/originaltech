package com.moonlight.originaltech.items.machines;

import com.moonlight.originaltech.OriginalTech;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class OTOriginalWorkbench extends SlimefunItem {

    public static final SlimefunItemStack ORIGINAL_WORKBENCH = new SlimefunItemStack(
        "OT_ORIGINAL_WORKBENCH",
        Material.CRAFTING_TABLE,
        "&e原版？工作台",
        "",
        "&7可以合成原版科技的专属物品"
    );

    public static final RecipeType ORIGINAL_WORKBENCH_RECIPE_TYPE = new RecipeType(
        new NamespacedKey(OriginalTech.getInstance(), "original_workbench"),
        ORIGINAL_WORKBENCH,
        "&7在原版？工作台中合成"
    );

    private static final int[] INPUT_SLOTS = {0, 1, 2, 9, 10, 11, 18, 19, 20};
    private static final int OUTPUT_SLOT = 24;

    public OTOriginalWorkbench(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        new BlockMenuPreset(getId(), "&e原版？工作台") {
            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public boolean canOpen(org.bukkit.block.Block b, org.bukkit.entity.Player p) {
                return true;
            }

            @Override
            public void newInstance(BlockMenu menu, org.bukkit.block.Block b) {
                if (BlockStorage.getLocationInfo(b.getLocation(), "owner") == null) {
                    BlockStorage.addBlockInfo(b, "owner", "public");
                }
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow == ItemTransportFlow.INSERT) {
                    return INPUT_SLOTS;
                } else {
                    return new int[]{OUTPUT_SLOT};
                }
            }
        };
    }

    private void constructMenu(BlockMenuPreset preset) {
        for (int i = 0; i < 27; i++) {
            if (i == 13) {
                preset.addItem(i, new ItemStack(Material.ARROW), (p, slot, item, action) -> false);
            } else if (i == OUTPUT_SLOT) {
                continue;
            } else if (isInputSlot(i)) {
                continue;
            } else {
                preset.addItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE), (p, slot, item, action) -> false);
            }
        }
    }

    private boolean isInputSlot(int slot) {
        for (int inputSlot : INPUT_SLOTS) {
            if (inputSlot == slot) {
                return true;
            }
        }
        return false;
    }
}
