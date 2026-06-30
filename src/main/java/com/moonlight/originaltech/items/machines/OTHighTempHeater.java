package com.moonlight.originaltech.items.machines;

import com.moonlight.originaltech.items.materials.OTIronMaterials;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.IgnitionChamber;
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

public class OTHighTempHeater extends MultiBlockMachine {

    public static final SlimefunItemStack HIGH_TEMP_HEATER = new SlimefunItemStack(
        "OT_HIGH_TEMP_HEATER",
        Material.BLAST_FURNACE,
        "&c高温加热炉"
    );

    public OTHighTempHeater(ItemGroup category, SlimefunItemStack item, ItemStack[] recipe) {
        super(category, item, recipe, BlockFace.DOWN);
    }

    @Override
    protected void registerDefaultRecipes(List<ItemStack> recipes) {
        // 铁锭 -> 精热铁
        recipes.add(new ItemStack(Material.IRON_INGOT));
        recipes.add(OTIronMaterials.HOT_IRON.clone());

        // 压缩铁 -> 压缩精热铁
        recipes.add(OTIronMaterials.COMPRESSED_IRON.clone());
        recipes.add(OTIronMaterials.COMPRESSED_HOT_IRON.clone());

        // 压缩铁块 -> 原版核心精热铁
        recipes.add(OTIronMaterials.COMPRESSED_IRON_BLOCK.clone());
        recipes.add(OTIronMaterials.CORE_HOT_IRON.clone());
    }

    @Override
    public void onInteract(Player p, Block b) {
        Block dispenserBlock = b.getRelative(BlockFace.DOWN);
        Block fireBlock = dispenserBlock.getRelative(BlockFace.DOWN);

        if (dispenserBlock.getType() != Material.DISPENSER) {
            return;
        }

        if (fireBlock.getType() != Material.FIRE) {
            Slimefun.getLocalization().sendMessage(p, "machines.unknown-material", true);
            return;
        }

        Dispenser dispenser = (Dispenser) dispenserBlock.getState();
        Inventory inv = dispenser.getInventory();

        // Check for each recipe
        HeatingRecipe recipe = findRecipe(inv);
        if (recipe == null) {
            Slimefun.getLocalization().sendMessage(p, "machines.unknown-material", true);
            return;
        }

        // Remove input item
        ItemStack inputItem = inv.getItem(recipe.slot);
        inputItem.setAmount(inputItem.getAmount() - 1);
        if (inputItem.getAmount() <= 0) {
            inv.setItem(recipe.slot, null);
        }

        // Add output
        if (inv.firstEmpty() != -1) {
            inv.addItem(recipe.output.clone());
        } else {
            b.getWorld().dropItemNaturally(b.getLocation(), recipe.output.clone());
            Slimefun.getLocalization().sendMessage(p, "machines.full-inventory", true);
            return;
        }

        // Try to use Ignition Chamber first
        boolean fireRenewed = IgnitionChamber.useFlintAndSteel(p, dispenserBlock);

        if (!fireRenewed) {
            fireBlock.setType(Material.AIR);
        }

        p.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
    }

    private HeatingRecipe findRecipe(Inventory inv) {
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            if (item == null) continue;

            // 压缩铁块 -> 原版核心精热铁
            SlimefunItem sfItem = SlimefunItem.getByItem(item);
            if (sfItem != null && sfItem.getId().equals(OTIronMaterials.COMPRESSED_IRON_BLOCK.getItemId())) {
                return new HeatingRecipe(i, OTIronMaterials.CORE_HOT_IRON);
            }

            // 压缩铁 -> 压缩精热铁
            if (sfItem != null && sfItem.getId().equals(OTIronMaterials.COMPRESSED_IRON.getItemId())) {
                return new HeatingRecipe(i, OTIronMaterials.COMPRESSED_HOT_IRON);
            }

            // 铁锭 -> 精热铁（排除已经是精热铁的物品）
            if (item.getType() == Material.IRON_INGOT) {
                if (sfItem == null || !sfItem.getId().equals(OTIronMaterials.HOT_IRON.getItemId())) {
                    return new HeatingRecipe(i, OTIronMaterials.HOT_IRON);
                }
            }
        }
        return null;
    }

    public static SlimefunItemStack getHighTempHeater() {
        return HIGH_TEMP_HEATER;
    }

    private static class HeatingRecipe {
        final int slot;
        final SlimefunItemStack output;

        HeatingRecipe(int slot, SlimefunItemStack output) {
            this.slot = slot;
            this.output = output;
        }
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> items = new ArrayList<>();
        // 铁锭 -> 精热铁
        items.add(new ItemStack(Material.IRON_INGOT));
        items.add(OTIronMaterials.HOT_IRON.clone());

        // 压缩铁 -> 压缩精热铁
        items.add(OTIronMaterials.COMPRESSED_IRON.clone());
        items.add(OTIronMaterials.COMPRESSED_HOT_IRON.clone());

        // 压缩铁块 -> 原版核心精热铁
        items.add(OTIronMaterials.COMPRESSED_IRON_BLOCK.clone());
        items.add(OTIronMaterials.CORE_HOT_IRON.clone());
        return items;
    }
}