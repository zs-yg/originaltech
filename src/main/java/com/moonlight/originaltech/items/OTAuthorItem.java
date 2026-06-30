package com.moonlight.originaltech.items;

import com.moonlight.originaltech.setup.OTItemGroups;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

public class OTAuthorItem extends SlimefunItem {

    private static final String AUTHOR_TEXTURE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjZjMDgyNjViYjg5ODg3NmFjNmM2OWQ3OWVhYWFmMjgzYzUyNGM3MzY4YjIzZGY2NzllN2ZlZjJiNTkzZGJkZCJ9fX0=";

    public static final SlimefunItemStack AUTHOR_HEAD = new SlimefunItemStack(
        "OT_AUTHOR",
        AUTHOR_TEXTURE,
        "§d作者 moonlight_awa"
    );

    public OTAuthorItem() {
        super(OTItemGroups.AUTHOR_GROUP, AUTHOR_HEAD, RecipeType.NULL, new ItemStack[9]);
    }
}