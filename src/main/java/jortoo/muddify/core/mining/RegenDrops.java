package jortoo.muddify.core.mining;

import jortoo.muddify.Muddify;
import jortoo.muddify.core.KeyRegistry;
import jortoo.muddify.core.playerdata.PlayerData;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

public class RegenDrops {

    private final ItemStack drop;
    private final double xp;
    private final int dropAmount;
    private final Location location;
    private final KeyRegistry registery;



    public RegenDrops(ItemStack drop, double xp, int dropAmount, Location location, KeyRegistry registery) {
        this.drop = drop;
        this.xp = xp;
        this.dropAmount = dropAmount;
        this.location = location;
        this.registery = registery;
    }

    public void addToBackPack(PlayerData data, ItemStack drop, int dropAmount) {

        String key = "backpack." + this.drop.getType().name().toLowerCase();

        data.setData(key, data.getInt(key, 0) + dropAmount);

    }

    public void handleDrops() {

        Item droppedItem = this.location.getWorld().dropItem(this.location, this.drop);

        droppedItem.setVelocity(new Vector(0,0,0));

        MiniMessage mm = MiniMessage.miniMessage();

        String name = this.drop.getType().name().replace("_", " ").toLowerCase();

        droppedItem.customName(mm.deserialize("<yellow>" + this.dropAmount + "x <white>" + name));
        droppedItem.setCustomNameVisible(true);
        droppedItem.getPersistentDataContainer().set(
                registery.get("no_pickup"),
                PersistentDataType.BOOLEAN,
                true
        );

        Bukkit.getScheduler().runTaskLater(Muddify.plugin, () -> {
            droppedItem.setHealth(0);
        }, 20);

    }

}
