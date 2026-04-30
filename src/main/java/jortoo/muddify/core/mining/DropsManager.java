package jortoo.muddify.core.mining;

import jortoo.muddify.Muddify;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DropsManager {

    private final Map<Material, DropBluePrint> drops = new LinkedHashMap<>();;

    public void loadDrops() {

        drops.clear();
        File file = new File(Muddify.plugin.getDataFolder(), "Drops.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        ConfigurationSection section = config.getConfigurationSection("drops");
        if (section == null) return;

        for (String key : section.getKeys(false)) {

            Material mat = Material.getMaterial(key);
            if (mat == null) continue;

            double xp = section.getDouble(key + ".xp");
            int dropAmount = section.getInt(key + ".amount");
            long regenTime = section.getLong(key + ".regen-time");

            drops.put(mat, new DropBluePrint(mat, xp, dropAmount, regenTime));

        }

    }

    public Map<Material, DropBluePrint> getDrops() {
        return drops;
    }

    public record DropBluePrint(Material item, double xp, int amount, long regenTime) {}

}
