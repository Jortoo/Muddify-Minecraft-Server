package jortoo.muddify.core.mining.events;

import jortoo.muddify.core.mining.KeyRegistry;
import jortoo.muddify.Muddify;
import jortoo.muddify.core.mining.RegenBlocks;
import jortoo.muddify.core.mining.RegenDrops;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class MiningListener implements Listener {

    private final Map<Material, RegenBlocks> blocksMap = new HashMap<>();
    private final Map<Material, DropBluePrint> dropsMap = new HashMap<>();
    private final KeyRegistry registry;

    public MiningListener(KeyRegistry registry) {

        this.registry = registry;

        blocksMap.put(Material.STONE, new RegenBlocks(Material.STONE, 100L));
        blocksMap.put(Material.COAL_ORE, new RegenBlocks(Material.COAL_ORE, 100L));
        blocksMap.put(Material.IRON_ORE, new RegenBlocks(Material.IRON_ORE, 100L));

        dropsMap.put(Material.STONE, new DropBluePrint(Material.STONE, 1, 1));
        dropsMap.put(Material.COAL_ORE, new DropBluePrint(Material.COAL_ORE, 2, 2));
        dropsMap.put(Material.IRON_ORE, new DropBluePrint(Material.IRON_ORE, 3, 3));

    }


    @EventHandler
    public void onMine(BlockBreakEvent event) {

        if (event.isCancelled()) {
            return;
        }

        Material material = event.getBlock().getType();

        if (blocksMap.containsKey(material)) {

            event.setCancelled(true);

            RegenBlocks config = blocksMap.get(material);
            config.handleRegen(event.getBlock(), Muddify.plugin);

        }

        if (dropsMap.containsKey(material)) {

            DropBluePrint blueprint = dropsMap.get(material);

            RegenDrops drops = new RegenDrops(new ItemStack(blueprint.item()),
                    blueprint.xp(),
                    blueprint.amount(),
                    event.getBlock().getLocation().add(0.5, 1.5, 0.5),
                    this.registry
            );

            drops.handleDrops();

        }


    }

    record DropBluePrint(Material item, double xp, int amount) {}

}
