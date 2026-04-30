package jortoo.muddify.core.staff.clearlag.commands;

import jortoo.muddify.Muddify;
import jortoo.muddify.core.staff.clearlag.ClearLagManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClearLagCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player player)) {
            return false;
        }

        ClearLagManager cl = new ClearLagManager(Muddify.plugin);
        cl.runClear();

        return true;
    }
}
