package jortoo.muddify.utils;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {

    public Items() {}

    public static void hideAllAttributes(ItemMeta meta) {

        for (ItemFlag flag : ItemFlag.values()) {
            meta.addItemFlags(flag);
        }

    }

}
