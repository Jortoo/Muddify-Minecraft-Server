package jortoo.muddify.core.mining.commands;

import jortoo.muddify.core.mining.inventories.BackPackInventory;
import jortoo.muddify.core.playerdata.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BackpackCommand implements CommandExecutor {

    private final PlayerManager playerManager;

    public BackpackCommand(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player player)) return false;

        new BackPackInventory(playerManager).open(player);

        return true;
    }
}
