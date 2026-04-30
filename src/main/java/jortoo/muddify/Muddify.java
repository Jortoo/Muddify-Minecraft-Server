package jortoo.muddify;

import jortoo.muddify.core.mining.KeyRegistry;
import jortoo.muddify.core.mining.events.MiningListener;
import jortoo.muddify.core.staff.clearlag.commands.ClearLagCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Muddify extends JavaPlugin {

    public static Muddify plugin;

    @Override
    public void onEnable() {

        plugin = this;

        KeyRegistry keyRegistery = new KeyRegistry(this);

        getServer().getPluginManager().registerEvents(new MiningListener(keyRegistery), this);
        getCommand("clearlag").setExecutor(new ClearLagCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
