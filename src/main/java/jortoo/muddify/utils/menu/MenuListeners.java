package jortoo.muddify.utils.menu;

import jortoo.muddify.Muddify;
import jortoo.muddify.utils.PDC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MenuListeners implements Listener {

    @EventHandler
    public void onCloseClick(InventoryClickEvent event) {

        ItemStack slotItem = event.getCurrentItem();

        if (slotItem == null || slotItem.getItemMeta() == null) return;

        PDC pdc = new PDC(slotItem.getItemMeta().getPersistentDataContainer(), Muddify.plugin);

        if (pdc.getBoolean("close_button", false)) {

            event.setCancelled(true);

            if (event.getWhoClicked() instanceof Player player) player.closeInventory();

        }

        else if (pdc.getBoolean("border_glass", false)) event.setCancelled(true);

    }

}
