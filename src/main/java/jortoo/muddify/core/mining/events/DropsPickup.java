package jortoo.muddify.core.mining.events;

import jortoo.muddify.Muddify;
import jortoo.muddify.core.mining.KeyRegistry;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.persistence.PersistentDataType;

public class DropsPickup implements Listener {

    private final KeyRegistry registry;

    public DropsPickup(KeyRegistry registry) {
        this.registry = registry;
    }

    @EventHandler
    public void onDropPickup(EntityPickupItemEvent event) {

        Entity entity = event.getItem();

        if (entity.getPersistentDataContainer().has(registry.get("no_pickup"), PersistentDataType.BOOLEAN)) {
            event.setCancelled(true);
        }

    }

}
