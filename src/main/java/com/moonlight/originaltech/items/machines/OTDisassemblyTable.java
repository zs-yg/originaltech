package com.moonlight.originaltech.items.machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

public class OTDisassemblyTable extends SlimefunItem {
    public static final SlimefunItemStack DISASSEMBLY_TABLE = new SlimefunItemStack(
            "OT_DISASSEMBLY_TABLE",
            Material.CAULDRON,
            "§6拆解台",
            "",
            "§7将增强工作台配方中的物品拆解为材料",
            "§7放入物品后点击按钮拆解",
            "",
            "§7无需电力"
    );

    private OTDisassemblyTableMenu menu;

    public OTDisassemblyTable(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        super.preRegister();
        this.addItemHandler(onBlockPlace());
        this.addItemHandler(onBlockBreak());
        this.menu = new OTDisassemblyTableMenu(this);
    }

    @Nonnull
    protected BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(BlockPlaceEvent e) {
            }
        };
    }

    @Nonnull
    protected BlockBreakHandler onBlockBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack tool, List<ItemStack> drops) {
                Block b = e.getBlock();
                BlockStorage.clearBlockInfo(b);
            }
        };
    }
}
