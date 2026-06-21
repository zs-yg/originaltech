package com.moonlight.originaltech.items.machines;

import com.moonlight.originaltech.items.materials.OTIronMaterials;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class OTCompressor extends MultiBlockMachine {

    public static final SlimefunItemStack COMPRESSOR = new SlimefunItemStack(
        "OT_COMPRESSOR",
        Material.PISTON,
        "&f原版压力机"
    );

    public OTCompressor(ItemGroup category, SlimefunItemStack item, ItemStack[] recipe) {
        super(category, item, recipe, BlockFace.DOWN);
    }

    @Override
    protected void registerDefaultRecipes(List<ItemStack> recipes) {
        // 精热铁 x10 -> 压缩铁
        recipes.add(createNumberedItem(OTIronMaterials.HOT_IRON.clone(), 10));
        recipes.add(OTIronMaterials.COMPRESSED_IRON.clone());

        // 压缩精热铁 x10 -> 压缩铁块
        recipes.add(createNumberedItem(OTIronMaterials.COMPRESSED_HOT_IRON.clone(), 10));
        recipes.add(OTIronMaterials.COMPRESSED_IRON_BLOCK.clone());

        // 原版核心精热铁 x10 -> 核心铁块
        recipes.add(createNumberedItem(OTIronMaterials.CORE_HOT_IRON.clone(), 10));
        recipes.add(OTIronMaterials.CORE_IRON_BLOCK.clone());
    }

    private ItemStack createNumberedItem(ItemStack base, int count) {
        ItemStack item = base.clone();
        item.setAmount(count);
        return item;
    }

    @Override
    public void onInteract(Player p, Block b) {
        Block dispenserBlock = b.getRelative(BlockFace.DOWN);

        if (dispenserBlock.getType() != Material.DISPENSER) {
            return;
        }

        Dispenser dispenser = (Dispenser) dispenserBlock.getState();
        Inventory inv = dispenser.getInventory();

        ItemStack result = checkRecipes(inv);
        if (result == null) {
            Slimefun.getLocalization().sendMessage(p, "machines.unknown-material", true);
            return;
        }

        if (inv.firstEmpty() != -1) {
            inv.addItem(result);
        } else {
            b.getWorld().dropItemNaturally(b.getLocation(), result);
            Slimefun.getLocalization().sendMessage(p, "machines.full-inventory", true);
            return;
        }

        p.getWorld().playEffect(b.getLocation(), Effect.SMOKE, 1);
    }

    private ItemStack checkRecipes(Inventory inv) {
        // 原版核心精热铁 x10 -> 核心铁块
        if (countItem(inv, OTIronMaterials.CORE_HOT_IRON) >= 10) {
            removeItems(inv, OTIronMaterials.CORE_HOT_IRON, 10);
            return OTIronMaterials.CORE_IRON_BLOCK.clone();
        }

        // 压缩精热铁 x10 -> 压缩铁块
        if (countItem(inv, OTIronMaterials.COMPRESSED_HOT_IRON) >= 10) {
            removeItems(inv, OTIronMaterials.COMPRESSED_HOT_IRON, 10);
            return OTIronMaterials.COMPRESSED_IRON_BLOCK.clone();
        }

        // 精热铁 x10 -> 压缩铁
        if (countItem(inv, OTIronMaterials.HOT_IRON) >= 10) {
            removeItems(inv, OTIronMaterials.HOT_IRON, 10);
            return OTIronMaterials.COMPRESSED_IRON.clone();
        }

        return null;
    }

    private int countItem(Inventory inv, SlimefunItemStack target) {
        int count = 0;
        for (ItemStack item : inv.getContents()) {
            if (item != null && SlimefunItem.getByItem(item) != null
                    && SlimefunItem.getByItem(item).getId().equals(target.getItemId())) {
                count += item.getAmount();
            }
        }
        return count;
    }

    private void removeItems(Inventory inv, SlimefunItemStack target, int amount) {
        int remaining = amount;
        ItemStack[] contents = inv.getContents();
        for (int i = 0; i < contents.length && remaining > 0; i++) {
            ItemStack item = contents[i];
            if (item != null && SlimefunItem.getByItem(item) != null
                    && SlimefunItem.getByItem(item).getId().equals(target.getItemId())) {
                int remove = Math.min(remaining, item.getAmount());
                item.setAmount(item.getAmount() - remove);
                remaining -= remove;
                if (item.getAmount() <= 0) {
                    inv.setItem(i, null);
                }
            }
        }
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> items = new ArrayList<>();
        // 精热铁 x10 -> 压缩铁
        items.add(createDisplayItem(OTIronMaterials.HOT_IRON.clone(), "§c10x 精热铁"));
        items.add(OTIronMaterials.COMPRESSED_IRON.clone());

        // 压缩精热铁 x10 -> 压缩铁块
        items.add(createDisplayItem(OTIronMaterials.COMPRESSED_HOT_IRON.clone(), "§610x 压缩精热铁"));
        items.add(OTIronMaterials.COMPRESSED_IRON_BLOCK.clone());

        // 原版核心精热铁 x10 -> 核心铁块
        items.add(createDisplayItem(OTIronMaterials.CORE_HOT_IRON.clone(), "§e10x 原版核心精热铁"));
        items.add(OTIronMaterials.CORE_IRON_BLOCK.clone());
        return items;
    }

    private ItemStack createDisplayItem(ItemStack base, String displayName) {
        ItemStack item = base.clone();
        item.setAmount(10);
        var meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        item.setItemMeta(meta);
        return item;
    }
}