package jortoo.muddify.core.mining.events;

import jortoo.muddify.core.KeyRegistry;
import jortoo.muddify.Muddify;
import jortoo.muddify.core.mining.DropsManager;
import jortoo.muddify.core.mining.RegenBlocks;
import jortoo.muddify.core.mining.RegenDrops;
import jortoo.muddify.core.playerdata.PlayerData;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class MiningListener implements Listener {

    private final KeyRegistry registry;

    public MiningListener(KeyRegistry registry) {
        this.registry = registry;
    }

    @EventHandler
    public void onMine(BlockBreakEvent event) {

        if (event.isCancelled()) return;

        Material material = event.getBlock().getType();
        Player player = event.getPlayer();

        DropsManager.DropBluePrint blueprint = Muddify.plugin.getDropsManager().getDrops().get(material);

        if (blueprint == null) return;

        PlayerData data = Muddify.plugin.getPlayerManager().get(player.getUniqueId());
        if (data == null) return;

        event.setCancelled(true);

        new RegenBlocks(material, blueprint.regenTime()).handleRegen(event.getBlock(), Muddify.plugin);

        data.setData("stats.mined", data.getInt("stats.mined", 0) + 1);

        ItemStack dropItem = new ItemStack(blueprint.item());

        RegenDrops dropsHandler = new RegenDrops(
                dropItem,
                blueprint.xp(),
                blueprint.amount(),
                event.getBlock().getLocation().add(0.5, 1.1, 0.5),
                this.registry
        );

        dropsHandler.handleDrops();
        dropsHandler.addToBackPack(data, dropItem, blueprint.amount());

    }

}
