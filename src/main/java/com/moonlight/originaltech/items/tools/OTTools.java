package com.moonlight.originaltech.items.tools;

import com.moonlight.originaltech.OriginalTech;
import com.moonlight.originaltech.items.materials.OTIronMaterials;
import com.moonlight.originaltech.setup.OTItemGroups;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;

public class OTTools {

    public static final SlimefunItemStack CORE_PICKAXE;

    static {
        ItemStack baseItem = new ItemStack(Material.NETHERITE_PICKAXE);
        @Nonnull ItemMeta meta = baseItem.getItemMeta();
        meta.setUnbreakable(true);
        baseItem.setItemMeta(meta);
        baseItem.addUnsafeEnchantment(Enchantment.EFFICIENCY, 100);
        baseItem.addUnsafeEnchantment(Enchantment.FORTUNE, 100);
        baseItem.addUnsafeEnchantment(Enchantment.MENDING, 1);

        CORE_PICKAXE = new SlimefunItemStack(
            "OT_CORE_PICKAXE",
            baseItem,
            "&k&b&l核心稿",
            "",
            "&c这稿子有点厉害",
            "&e效率 C - 瞬间挖掘",
            "&e时运 C - 矿藏倍增",
            "&e无法破坏",
            "&e经验修补",
            "&5用核心铁块做的，应该很强吧"
        );
    }

    public static void setup(OriginalTech plugin) {
        SlimefunItem corePickaxe = new SlimefunItem(
            OTItemGroups.TOOLS_GROUP,
            CORE_PICKAXE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                OTIronMaterials.CORE_IRON_BLOCK, OTIronMaterials.CORE_IRON_BLOCK, OTIronMaterials.CORE_IRON_BLOCK,
                null, new ItemStack(Material.STICK), null,
                null, new ItemStack(Material.STICK), null
            }
        );
        corePickaxe.addItemHandler((ToolUseHandler) (e, tool, fortune, drops) -> {
            // 核心稿没有特殊功能，只是一个高效的镐子
        });
        corePickaxe.register(plugin);
        plugin.getLogger().info("工具已加载 - 核心稿");
    }
}