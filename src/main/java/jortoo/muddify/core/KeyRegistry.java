package jortoo.muddify.core;

import jortoo.muddify.Muddify;
import org.bukkit.NamespacedKey;
import java.util.HashMap;
import java.util.Map;

public class KeyRegistry {

    private final Map<String, NamespacedKey> keys = new HashMap<>();
    private final Muddify plugin;

    public KeyRegistry(Muddify plugin) {
        this.plugin = plugin;
    }

    public NamespacedKey get(String key) {
        return keys.computeIfAbsent(key, k -> new NamespacedKey(plugin, k));
    }
}