package jortoo.muddify.core.items.events;

import jortoo.muddify.Muddify;
import jortoo.muddify.core.items.holders.GearMainHolder;
import jortoo.muddify.core.items.inventories.GearMenu;
import jortoo.muddify.core.mining.holders.BackpackHolder;
import jortoo.muddify.utils.PDC;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GearMainMenuClicks implements Listener {

    @EventHandler
    public void onGearMenuClick(InventoryClickEvent event) {

        if (event.getClickedInventory() == null) return;
        if (!(event.getClickedInventory().getHolder() instanceof GearMainHolder)) return;

        ItemStack slot = event.getCurrentItem();
        PDC pdc = new PDC(slot.getItemMeta().getPersistentDataContainer(), Muddify.plugin);

        if (!pdc.has("gear_type")) return;

        event.setCancelled(true);

        String type = pdc.getString("gear_type", "");
        Player player = (Player) event.getWhoClicked();

        if (type.equals("armor")) new GearMenu().open(type, player, 0);


    }



}
