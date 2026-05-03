package jortoo.muddify.core.items.inventories;

import jortoo.muddify.Muddify;
import jortoo.muddify.core.items.holders.GearMainHolder;
import jortoo.muddify.utils.Items;
import jortoo.muddify.utils.PDC;
import jortoo.muddify.utils.menu.MenuSlots;
import jortoo.muddify.utils.text.MetaText;
import jortoo.muddify.utils.text.TextHelper;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GearMainMenu {

    public GearMainMenu () {}

    public void open(Player player) {

        Inventory inv = Bukkit.createInventory(new GearMainHolder(), 36, new MetaText("<black><u>Items").deser());
        MenuSlots menuSlots = new MenuSlots();

        inv.setItem(11, gearButton(new ItemStack(Material.IRON_HELMET), "armor"));
        inv.setItem(13, gearButton(new ItemStack(Material.IRON_PICKAXE), "tools"));
        inv.setItem(15, gearButton(new ItemStack(Material.IRON_SWORD), "weapons"));

        for (int i = 27; i < 36; i++) {inv.setItem(i, menuSlots.borderGlass());}

        inv.setItem(31, menuSlots.closeButton());

        player.openInventory(inv);

    }

    private ItemStack gearButton(ItemStack item, String name) {

        ItemMeta meta = item.getItemMeta();

        meta.displayName(new MetaText("<#006AFF><bold>" + name.toUpperCase() + " Menu").deser());
        ArrayList<Component> lore = new ArrayList<>();

        lore.add(new MetaText("<dark_gray>Button").deser());
        lore.add(new MetaText("").deser());
        lore.add(new MetaText(" <#006AFF><bold>⏹ <b:false><white>Get all custom " + name + " here").deser());
        lore.add(new MetaText("").deser());
        lore.add(new MetaText(TextHelper.miniText("<#006AFF>Click to open")).deser());

        Items.hideAllAttributes(meta);

        PDC pdc = new PDC(meta.getPersistentDataContainer(), Muddify.plugin);
        pdc.setString("gear_type", name);

        meta.lore(lore);
        item.setItemMeta(meta);

        return item;

    }

}
