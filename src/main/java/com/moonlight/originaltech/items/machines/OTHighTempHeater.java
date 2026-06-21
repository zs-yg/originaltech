package com.moonlight.originaltech.items.machines;

import com.moonlight.originaltech.items.materials.OTMaterials;
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
        recipes.add(new ItemStack(Material.IRON_INGOT));
        recipes.add(OTMaterials.HOT_IRON.clone());
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

        boolean hasIronIngot = false;
        int ironIngotSlot = -1;

        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            if (item != null && item.getType() == Material.IRON_INGOT) {
                SlimefunItem sfItem = SlimefunItem.getByItem(item);
                if (sfItem == null || !sfItem.getId().equals(OTMaterials.HOT_IRON.getItemId())) {
                    hasIronIngot = true;
                    ironIngotSlot = i;
                    break;
                }
            }
        }

        if (!hasIronIngot) {
            Slimefun.getLocalization().sendMessage(p, "machines.unknown-material", true);
            return;
        }

        ItemStack ironIngot = inv.getItem(ironIngotSlot);
        ironIngot.setAmount(ironIngot.getAmount() - 1);
        if (ironIngot.getAmount() <= 0) {
            inv.setItem(ironIngotSlot, null);
        }

        ItemStack hotIron = OTMaterials.HOT_IRON.clone();

        if (inv.firstEmpty() != -1) {
            inv.addItem(hotIron);
        } else {
            b.getWorld().dropItemNaturally(b.getLocation(), hotIron);
            Slimefun.getLocalization().sendMessage(p, "machines.full-inventory", true);
            return;
        }

        // Try to use Ignition Chamber first
        boolean fireRenewed = IgnitionChamber.useFlintAndSteel(p, dispenserBlock);

        if (!fireRenewed) {
            // No Ignition Chamber or no Flint and Steel, consume the fire
            fireBlock.setType(Material.AIR);
        }

        p.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> items = new ArrayList<>();
        items.add(new ItemStack(Material.IRON_INGOT));
        items.add(OTMaterials.HOT_IRON.clone());
        return items;
    }
}