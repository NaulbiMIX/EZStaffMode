package com.ezstaffmode.util;

import java.util.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class ItemUtil {

    public ItemStack createItemWithLoreAndShort(Material m, int amount, int setShort, String itemName, List<String> setLore) {
        ItemStack itemStack = new ItemStack(m, 1, (short)setShort);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(itemName);
        itemMeta.setLore(setLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }


}