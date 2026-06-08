package com.moonlight.originaltech.items;

import com.moonlight.originaltech.setup.OTItemGroups;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class OTAuthorItem extends SlimefunItem {

    public static final SlimefunItemStack AUTHOR_HEAD = createPlayerHead();

    private static SlimefunItemStack createPlayerHead() {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setDisplayName("§d作者 moonlight_awa");
        meta.setOwner("moonlight_awa");
        item.setItemMeta(meta);
        return new SlimefunItemStack("OT_AUTHOR", item);
    }

    public OTAuthorItem() {
        super(OTItemGroups.AUTHOR_GROUP, AUTHOR_HEAD, RecipeType.NULL, new ItemStack[9]);
    }
}