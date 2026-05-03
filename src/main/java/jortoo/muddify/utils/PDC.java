package jortoo.muddify.utils;

import jortoo.muddify.Muddify;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PDC {

    private final PersistentDataContainer pdc;
    private final Muddify plugin;

    public PDC (PersistentDataContainer pdc, Muddify plugin) {

        this.pdc = pdc;
        this.plugin = plugin;

    }

    public NamespacedKey getKey(String keyName) { return new NamespacedKey(plugin, keyName.toLowerCase()); }

    public boolean has(String keyName) { return pdc.has(getKey(keyName)); }

    public void remove(String keyName) { pdc.remove(getKey(keyName)); }

    public void setString(String keyName, String val) { pdc.set(getKey(keyName), PersistentDataType.STRING, val); }
    public void setInt(String keyName, int val) { pdc.set(getKey(keyName), PersistentDataType.INTEGER, val); }
    public void setDouble(String keyName, double val) { pdc.set(getKey(keyName), PersistentDataType.DOUBLE, val); }
    public void setLong(String keyName, Long val) { pdc.set(getKey(keyName), PersistentDataType.LONG, val); }
    public void setBoolean(String keyName, Boolean val) { pdc.set(getKey(keyName), PersistentDataType.BOOLEAN, val);}

    public String getString(String keyName, String def) {
        if (!pdc.has(getKey(keyName))) return def;
        return pdc.get(getKey(keyName), PersistentDataType.STRING);
    }
    public int getInt(String keyName, int def) {
        if (!pdc.has(getKey(keyName))) return def;
        return pdc.get(getKey(keyName), PersistentDataType.INTEGER);
    }
    public Double getDouble(String keyName, Double def) {
        if (!pdc.has(getKey(keyName))) return def;
        return pdc.get(getKey(keyName), PersistentDataType.DOUBLE);
    }
    public Long getLong(String keyName, Long def) {
        if (!pdc.has(getKey(keyName))) return def;
        return pdc.get(getKey(keyName), PersistentDataType.LONG);
    }
    public Boolean getBoolean(String keyName, Boolean def) {
        if (!pdc.has(getKey(keyName))) return def;
        return pdc.get(getKey(keyName), PersistentDataType.BOOLEAN);
    }
}
