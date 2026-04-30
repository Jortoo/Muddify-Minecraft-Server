package jortoo.muddify.core.playerdata;

import jortoo.muddify.Muddify;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerStorage {

    public void savePlayerData(PlayerData pData) {

        File dir = new File(Muddify.plugin.getDataFolder(), "playerdata");
        if (!dir.exists()) dir.mkdirs();

        File file = new File(Muddify.plugin.getDataFolder(), "playerdata/" + pData.getUuid() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        pData.getAllData().forEach((key, value) -> {
            config.set(key, value);
        });

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public PlayerData loadPlayerData(UUID uuid) {
        File file = new File(Muddify.plugin.getDataFolder(), "playerdata/" + uuid + ".yml");
        PlayerData pData = new PlayerData(uuid);

        if (!file.exists()) return pData;

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        for (String key : config.getKeys(true)) {
            Object value = config.get(key);

            if (!(value instanceof ConfigurationSection)) {
                pData.setData(key, value);
            }
        }

        return pData;
    }


}
