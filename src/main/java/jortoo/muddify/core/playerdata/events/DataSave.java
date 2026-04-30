package jortoo.muddify.core.playerdata.events;

import jortoo.muddify.Muddify;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class DataSave implements Listener {

    @EventHandler
    public void onLeaveData(PlayerQuitEvent event) {

        UUID uuid = event.getPlayer().getUniqueId();

        Muddify.plugin.getPlayerManager().unload(uuid);

    }

}
