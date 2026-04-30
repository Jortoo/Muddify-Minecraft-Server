package jortoo.muddify.core.playerdata.events;

import jortoo.muddify.Muddify;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class DataLoad implements Listener {

    @EventHandler
    public void onJoinData(PlayerJoinEvent event) {

        UUID uuid = event.getPlayer().getUniqueId();

        Muddify.plugin.getPlayerManager().load(uuid);

    }

}
