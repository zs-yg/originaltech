package com.moonlight.originaltech.items.machines.farming;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class OTStringFarm extends SlimefunItem {

    private static final int PRODUCTION_INTERVAL = 3;
    private static final String INVENTORY_TITLE = "1.21.1刷线机";
    private static final int[] OUTPUT_SLOTS = {4};

    private static final Map<Location, Integer> tickCounters = new HashMap<>();

    private static final String TEXTURE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDA4ZGY2MGM1MTA3NGVlZjI1NDRmZjM4Y2VhZDllMTY2NzVhZTQyNTE5MTYxMDUxODBlMWY4Y2UxOTdhYjNiYyJ9fX0=";

    public static final SlimefunItemStack STRING_FARM = new SlimefunItemStack(
        "OT_STRING_FARM",
        TEXTURE,
        "&a1.21.1刷线机",
        "",
        "&7每3tick自动生成线",
        "&7无需电力"
    );

    public OTStringFarm(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
        createMenu();
        addItemHandler(new BlockTickerHandler());
    }

    private void createMenu() {
        new BlockMenuPreset(getId(), INVENTORY_TITLE) {
            @Override
            public void init() {
                for (int i = 0; i < 9; i++) {
                    if (i != 4) {
                        addItem(i, createGlassPane());
                    }
                }
            }

            @Override
            public boolean canOpen(Block b, Player p) {
                return true;
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow == ItemTransportFlow.WITHDRAW) {
                    return OUTPUT_SLOTS;
                }
                return new int[]{};
            }
        };
    }

    private class BlockTickerHandler extends BlockTicker {
        @Override
        public void tick(Block block, SlimefunItem item, Config data) {
            Location location = block.getLocation();
            int tickCounter = tickCounters.getOrDefault(location, 0);
            tickCounter++;
            if (tickCounter >= PRODUCTION_INTERVAL) {
                tickCounter = 0;
                produceString(block);
            }
            tickCounters.put(location, tickCounter);
        }

        @Override
        public boolean isSynchronized() {
            return false;
        }
    }

    private void produceString(Block block) {
        BlockMenu menu = BlockStorage.getInventory(block);
        if (menu == null) return;

        ItemStack string = new ItemStack(Material.STRING);
        
        if (!menu.fits(string, OUTPUT_SLOTS)) {
            return;
        }
        
        menu.pushItem(string, OUTPUT_SLOTS);
    }

    private static ItemStack createGlassPane() {
        ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = pane.getItemMeta();
        meta.setDisplayName(" ");
        pane.setItemMeta(meta);
        return pane;
    }
}
