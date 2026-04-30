package jortoo.muddify.core.mining.events;

import jortoo.muddify.core.mining.holders.BackpackHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BackpackClick implements Listener {

    @EventHandler
    public void onBackpackClick(InventoryClickEvent event) {

        if (event.getClickedInventory() == null) return;

        if (!(event.getClickedInventory().getHolder() instanceof BackpackHolder)) return;

        event.setCancelled(true);

    }

}
