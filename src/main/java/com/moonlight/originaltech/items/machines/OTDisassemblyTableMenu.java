package com.moonlight.originaltech.items.machines;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;

public class OTDisassemblyTableMenu extends BlockMenuPreset {
    // 布局来自乱序科技
    private static final int[] BORDER = new int[]{3, 4, 12, 21, 22};
    private static final int[] INPUT_BORDER = new int[]{0, 1, 2, 9, 11, 18, 19, 20};
    private static final int[] OUTPUT_BORDER = new int[]{5, 14, 23};
    private static final int INPUT_SLOT = 10;
    private static final int STATUS_SLOT = 13;
    private static final int[] OUTPUT_SLOTS = new int[]{6, 7, 8, 15, 16, 17, 24, 25, 26};

    private final OTDisassemblyTable machine;

    public OTDisassemblyTableMenu(OTDisassemblyTable machine) {
        super(machine.getId(), machine.getItemName());
        this.machine = machine;
    }

    @Override
    public void init() {
        ItemStack bg = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta bgMeta = bg.getItemMeta();
        if (bgMeta != null) {
            bgMeta.setDisplayName(" ");
            bg.setItemMeta(bgMeta);
        }

        ItemStack button = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta buttonMeta = button.getItemMeta();
        if (buttonMeta != null) {
            buttonMeta.setDisplayName("§a拆解");
            button.setItemMeta(buttonMeta);
        }

        // 普通边界
        for (int slot : BORDER) {
            this.addItem(slot, bg);
        }
        // 输入边界
        for (int slot : INPUT_BORDER) {
            this.addItem(slot, bg);
        }
        // 输出边界
        for (int slot : OUTPUT_BORDER) {
            this.addItem(slot, bg);
        }

        this.addItem(STATUS_SLOT, button);
    }

    @Override
    public void newInstance(@Nonnull BlockMenu blockMenu, @Nonnull Block block) {
        super.newInstance(blockMenu, block);

        // 边界防止点击
        for (int slot : BORDER) {
            blockMenu.addMenuClickHandler(slot, (p, s, is, a) -> false);
        }
        for (int slot : INPUT_BORDER) {
            blockMenu.addMenuClickHandler(slot, (p, s, is, a) -> false);
        }
        for (int slot : OUTPUT_BORDER) {
            blockMenu.addMenuClickHandler(slot, (p, s, is, a) -> false);
        }

        // 按钮点击事件
        blockMenu.addMenuClickHandler(STATUS_SLOT, (p, s, is, a) -> {
            performDisassemble(blockMenu);
            return false;
        });
    }

    @Override
    public boolean canOpen(Block block, Player player) {
        return true;
    }

    @Override
    public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
        if (flow == ItemTransportFlow.WITHDRAW) {
            return OUTPUT_SLOTS;
        } else {
            return new int[]{INPUT_SLOT};
        }
    }

    private void performDisassemble(BlockMenu blockMenu) {
        ItemStack input = blockMenu.getItemInSlot(INPUT_SLOT);

        if (input == null || input.getType() == Material.AIR) {
            return;
        }

        // 检查是否是拆解台本身
        if (isSimilar(input, OTDisassemblyTable.DISASSEMBLY_TABLE)) {
            return;
        }

        ItemStack[] recipe = findEnhancedCraftingRecipe(input);
        if (recipe != null) {
            // 检查输出槽是否已被占用（非空且与新配方不匹配）
            boolean canDisassemble = true;
            for (int i = 0; i < OUTPUT_SLOTS.length && i < recipe.length; i++) {
                if (recipe[i] != null) {
                    ItemStack currentOutput = blockMenu.getItemInSlot(OUTPUT_SLOTS[i]);
                    if (currentOutput != null && currentOutput.getType() != Material.AIR) {
                        if (!isSimilar(currentOutput, recipe[i])) {
                            canDisassemble = false;
                            break;
                        }
                    }
                }
            }

            if (!canDisassemble) {
                return;
            }

            // 执行拆解
            for (int i = 0; i < OUTPUT_SLOTS.length && i < recipe.length; i++) {
                if (recipe[i] != null) {
                    ItemStack currentOutput = blockMenu.getItemInSlot(OUTPUT_SLOTS[i]);
                    if (currentOutput == null || currentOutput.getType() == Material.AIR) {
                        // 槽位为空，直接放
                        blockMenu.replaceExistingItem(OUTPUT_SLOTS[i], recipe[i].clone());
                    } else if (isSimilar(currentOutput, recipe[i])) {
                        // 槽位有相同物品，叠加数量
                        int newAmount = currentOutput.getAmount() + recipe[i].getAmount();
                        currentOutput.setAmount(Math.min(newAmount, currentOutput.getMaxStackSize()));
                    }
                }
            }
            input.setAmount(input.getAmount() - 1);
        }
    }

    private ItemStack[] findEnhancedCraftingRecipe(ItemStack output) {
        for (SlimefunItem item : Slimefun.getRegistry().getAllSlimefunItems()) {
            if (item != null && item.getRecipeType() == RecipeType.ENHANCED_CRAFTING_TABLE) {
                ItemStack[] recipe = item.getRecipe();
                if (recipe != null) {
                    if (isSimilar(item.getItem(), output)) {
                        return recipe;
                    }
                }
            }
        }
        return null;
    }

    private boolean isSimilar(ItemStack a, ItemStack b) {
        if (a == null || b == null) return false;
        if (a.getType() != b.getType()) return false;
        if (a.hasItemMeta() != b.hasItemMeta()) return false;
        if (a.hasItemMeta()) {
            if (!a.getItemMeta().equals(b.getItemMeta())) return false;
        }
        return true;
    }
}
