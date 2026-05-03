package jortoo.muddify.utils.menu;

import jortoo.muddify.Muddify;
import jortoo.muddify.utils.text.MetaText;
import jortoo.muddify.utils.text.TextHelper;
import jortoo.muddify.utils.PDC;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MenuSlots {

    private int slot;

    public MenuSlots() {

    }

    public ItemStack closeButton() {

        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();

        meta.itemName(new MetaText("<#FF2700><bold>✘ CLOSE").deser());

        ArrayList<Component> lore = new ArrayList<>();
        lore.add(new MetaText("").deser());
        lore.add(new MetaText(TextHelper.miniText("<#FF2700>click to close")).deser());
        meta.lore(lore);

        PDC pdc = new PDC(meta.getPersistentDataContainer(), Muddify.plugin);
        pdc.setBoolean("close_button", true);

        item.setItemMeta(meta);

        return item;
    }

    public ItemStack borderGlass() {

        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();

        PDC pdc = new PDC(meta.getPersistentDataContainer(), Muddify.plugin);
        pdc.setBoolean("border_glass", true);

        meta.setHideTooltip(true);

        item.setItemMeta(meta);

        return item;

    }

}
