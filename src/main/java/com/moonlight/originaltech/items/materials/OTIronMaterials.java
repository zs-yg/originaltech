package com.moonlight.originaltech.items.materials;

import com.moonlight.originaltech.OriginalTech;
import com.moonlight.originaltech.setup.OTItemGroups;
import com.moonlight.originaltech.utils.RegisterUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class OTIronMaterials {

    public static final SlimefunItemStack SILICON = new SlimefunItemStack(
        "OT_SILICON",
        Material.QUARTZ_BLOCK,
        "&b原版硅"
    );

    public static final SlimefunItemStack HOT_IRON = createItem("OT_HOT_IRON", Material.IRON_INGOT, "§c精热铁", "§7经过高温加热炉处理后的铁");
    public static final SlimefunItemStack COMPRESSED_IRON = createItem("OT_COMPRESSED_IRON", Material.IRON_BLOCK, "§f压缩铁", "§7由10个精热铁压缩而成");
    public static final SlimefunItemStack COMPRESSED_HOT_IRON = createItem("OT_COMPRESSED_HOT_IRON", Material.RAW_IRON_BLOCK, "§6压缩精热铁", "§7由压缩铁加热而成");
    public static final SlimefunItemStack COMPRESSED_IRON_BLOCK = createItem("OT_COMPRESSED_IRON_BLOCK", Material.IRON_BLOCK, "§f压缩铁块", "§7由10个压缩精热铁压缩而成");
    public static final SlimefunItemStack CORE_HOT_IRON = createItem("OT_CORE_HOT_IRON", Material.NETHERITE_INGOT, "§6原版核心精热铁", "§7由压缩精热铁块加热而成");
    public static final SlimefunItemStack CORE_IRON_BLOCK = createItem("OT_CORE_IRON_BLOCK", Material.NETHERITE_BLOCK, "§e核心铁块", "§7由10个原版核心精热铁压缩而成");

    private static SlimefunItemStack createItem(String id, Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material);
        var meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return new SlimefunItemStack(id, item);
    }

    public static void setup(OriginalTech plugin) {
        // 原版硅有配方
        RegisterUtils.register(
            new SlimefunItem(OTItemGroups.BASIC_MATERIALS_GROUP, SILICON, RecipeType.ENHANCED_CRAFTING_TABLE,
                RegisterUtils.surroundRecipe(null, new ItemStack(Material.SAND))),
            plugin, "原版硅"
        );

        // 铁系材料无配方（通过机器获得）
        for (SlimefunItemStack item : Arrays.asList(HOT_IRON, COMPRESSED_IRON, COMPRESSED_HOT_IRON, COMPRESSED_IRON_BLOCK, CORE_HOT_IRON, CORE_IRON_BLOCK)) {
            RegisterUtils.register(
                new SlimefunItem(OTItemGroups.BASIC_MATERIALS_GROUP, item, RecipeType.NULL, new ItemStack[9]),
                plugin, item.getItemMeta().getDisplayName()
            );
        }
    }
}