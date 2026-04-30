package jortoo.muddify.core.mining;

import jortoo.muddify.Muddify;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class RegenBlocks {

    private final Material type;
    private final Long delay;

    public RegenBlocks(Material type, Long delay) {
        this.type = type;
        this.delay = delay;
    }

    public void handleRegen(Block block, Muddify plugin) {

        block.setType(Material.BEDROCK);

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            block.setType(this.type);
        }, this.delay);
    }

}
