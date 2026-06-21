package com.moonlight.originaltech.utils;

import com.moonlight.originaltech.OriginalTech;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * 注册工具类，简化物品注册过程
 */
public final class RegisterUtils {

    private RegisterUtils() {
        // 工具类，不允许实例化
    }

    /**
     * 注册物品并输出日志
     * @param item Slimefun物品
     * @param plugin 插件实例
     * @param name 物品名称（用于日志）
     */
    public static void register(@Nonnull SlimefunItem item, @Nonnull OriginalTech plugin, @Nonnull String name) {
        item.register(plugin);
        plugin.getLogger().info("已加载 - " + name);
    }

    /**
     * 创建配方数组
     * @param items 配方物品
     * @return 9格配方数组
     */
    public static ItemStack[] recipe(ItemStack... items) {
        if (items.length != 9) {
            throw new IllegalArgumentException("配方必须有9个物品");
        }
        return items;
    }

    /**
     * 创建围一圈的配方（中间为中心物品，周围8个相同物品）
     * @param center 中心物品
     * @param around 围绕物品
     * @return 9格配方数组
     */
    public static ItemStack[] surroundRecipe(@Nonnull ItemStack center, @Nonnull ItemStack around) {
        return new ItemStack[]{
            around, around, around,
            around, center, around,
            around, around, around
        };
    }
}