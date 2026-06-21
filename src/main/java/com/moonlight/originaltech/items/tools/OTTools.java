package com.moonlight.originaltech.items.tools;

import com.moonlight.originaltech.OriginalTech;
import com.moonlight.originaltech.items.materials.OTIronMaterials;
import com.moonlight.originaltech.setup.OTItemGroups;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class OTTools {

    public static final SlimefunItemStack CORE_PICKAXE;

    static {
        ItemStack baseItem = new ItemStack(Material.NETHERITE_PICKAXE);
        baseItem.addUnsafeEnchantment(Enchantment.EFFICIENCY, 100);
        baseItem.addUnsafeEnchantment(Enchantment.UNBREAKING, 255);
        baseItem.addUnsafeEnchantment(Enchantment.FORTUNE, 100);
        baseItem.addUnsafeEnchantment(Enchantment.MENDING, 1);

        CORE_PICKAXE = new SlimefunItemStack(
            "OT_CORE_PICKAXE",
            baseItem,
            "&k&b&l核心稿&r &6&l[终极]",
            "",
            "&c&l✦ 终极神器 ✦",
            "&e效率 C &7- &d瞬间挖掘",
            "&e时运 C &7- &d矿藏倍加",
            "&e耐久 X &7- &d无限耐久",
            "&e经验修补 &7- &d自我修复",
            "&5据说这把稿子强大到无可比拟(堪比无尽)",
            "&5&o凝聚了核心铁块的终极力量",
            "&8&o只有最强大的玩家才能驾驭"
        );
    }

    @SuppressWarnings("null")
    public static void setup(OriginalTech plugin) {
        SimpleSlimefunItem<ToolUseHandler> corePickaxe = new SimpleSlimefunItem<>(
            OTItemGroups.TOOLS_GROUP,
            CORE_PICKAXE,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                OTIronMaterials.CORE_IRON_BLOCK, OTIronMaterials.CORE_IRON_BLOCK, OTIronMaterials.CORE_IRON_BLOCK,
                null, new ItemStack(Material.STICK), null,
                null, new ItemStack(Material.STICK), null
            }
        ) {
            @Override
            public ToolUseHandler getItemHandler() {
                return (e, tool, fortune, drops) -> {
                    // 核心稿没有特殊功能，只是一个高效的镐子
                };
            }
        };
        corePickaxe.register(plugin);
        plugin.getLogger().info("工具已加载 - 核心稿");
    }
}