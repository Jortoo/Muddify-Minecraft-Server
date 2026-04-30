package jortoo.muddify.core.playerdata;

import jortoo.muddify.Muddify;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class PlayerData {

    private final UUID uuid;
    private final Map<String, Object> data = new HashMap<>();

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setData(String key, Object value) {
        data.put(key, value);
    }

    public String getString(String key, String def) {
        return (String) data.getOrDefault(key, def);
    }

    public int getInt(String key, int def) {
        return (int) data.getOrDefault(key, def);
    }

    public Map<String, Object> getAllData() {
        return data;
    }


}
