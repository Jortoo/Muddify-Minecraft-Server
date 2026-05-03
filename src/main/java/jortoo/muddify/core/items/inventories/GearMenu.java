package jortoo.muddify.core.items.inventories;

import jortoo.muddify.Muddify;
import jortoo.muddify.core.items.armor.ArmorManager;
import jortoo.muddify.core.items.armor.ArmorRegistry;
import jortoo.muddify.core.items.holders.GearMenuHolder;
import jortoo.muddify.utils.text.MetaText;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GearMenu {

    public GearMenu () {}

    public void open(String type, Player player, int page) {

        List<Object> itemsToShow = new ArrayList<>();

        Muddify plugin = Muddify.plugin;

        switch (type.toLowerCase()) {
            case "armor" -> { itemsToShow.addAll(plugin.getArmorManager().getArmorMap().values()); }
        }

        Inventory inv = Bukkit.createInventory(new GearMenuHolder(), 54, new MetaText("<black><u>" + type).deser());

        int startIndex = page * 45;

        for (int i = 0; i < 45; i++) {
            int listIndex = startIndex + i;
            if (listIndex >= itemsToShow.size()) break;

            Object registry = itemsToShow.get(listIndex);
            ItemStack finalItem = null;

            if (registry instanceof ArmorRegistry r) finalItem = r.buildItem();
            if (finalItem != null) {
                inv.setItem(i, finalItem);
            }

        }

        player.openInventory(inv);

    }



}
