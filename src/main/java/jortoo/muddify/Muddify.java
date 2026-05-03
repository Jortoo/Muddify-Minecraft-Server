package jortoo.muddify;

import jortoo.muddify.core.KeyRegistry;
import jortoo.muddify.core.mining.DropsManager;
import jortoo.muddify.core.mining.commands.BackpackCommand;
import jortoo.muddify.core.mining.events.BackpackClick;
import jortoo.muddify.core.mining.events.DropsPickup;
import jortoo.muddify.core.mining.events.MiningListener;
import jortoo.muddify.core.playerdata.PlayerDataExpansion;
import jortoo.muddify.core.playerdata.PlayerManager;
import jortoo.muddify.core.playerdata.events.DataLoad;
import jortoo.muddify.core.playerdata.events.DataSave;
import jortoo.muddify.core.staff.clearlag.commands.ClearLagCommand;
import jortoo.muddify.utils.menu.MenuListeners;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Muddify extends JavaPlugin {

    public static Muddify plugin;
    private PlayerManager playerManager;
    private DropsManager dropsManager;

    @Override
    public void onEnable() {

        plugin = this;

        KeyRegistry keyRegistery = new KeyRegistry(this);
        this.playerManager = new PlayerManager();

        this.dropsManager = new DropsManager();
        this.dropsManager.loadDrops();

        getServer().getPluginManager().registerEvents(new MiningListener(keyRegistery), this);
        getServer().getPluginManager().registerEvents(new DropsPickup(keyRegistery), this);
        getServer().getPluginManager().registerEvents(new DataLoad(), this);
        getServer().getPluginManager().registerEvents(new DataSave(), this);
        getServer().getPluginManager().registerEvents(new BackpackClick(), this);
        getServer().getPluginManager().registerEvents(new MenuListeners(), this);

        getCommand("clearlag").setExecutor(new ClearLagCommand());
        getCommand("backpack").setExecutor(new BackpackCommand(playerManager));

        for (Player player : Bukkit.getOnlinePlayers()) {
            playerManager.load(player.getUniqueId());
        }

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlayerDataExpansion(this).register();
        }



    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public DropsManager getDropsManager() {
        return dropsManager;
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            playerManager.unload(player.getUniqueId());
        }
    }
}
