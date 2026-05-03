package jortoo.muddify.core.items.armor;

import jortoo.muddify.Muddify;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ArmorManager {

    private final Muddify plugin;
    private File file;
    private FileConfiguration config;
    private final Map<String, ArmorRegistry> armorMap = new HashMap<>();

    public ArmorManager(Muddify plugin) {

        this.plugin = plugin;
        setupFile();

    }

    private void setupFile() {

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        file = new File(plugin.getDataFolder(), "Armor.yml");

        if (file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);

    }

    public void load() {

        armorMap.clear();
        ConfigurationSection section = config.getConfigurationSection("armors");

        if (section == null) return;

        for (String id : section.getKeys(false)) {

            String path = "armors." + id;

            Material mat = Material.matchMaterial(config.getString(path + ".material", "LEATHER_CHESTPLATE"));
            String name = config.getString(path + ".name", id);

            ArrayList<String> lore = new ArrayList<>(config.getStringList(path + ".lore"));

            Color color = null;
            String hex = config.getString(path + ".color");
            if (hex != null) {
                color = hexToColor(hex);
            }

            ArmorTrim trim = null;
            if (config.contains(path + ".trim")) {
                String patternStr = config.getString(path + ".trim.pattern");
                String materialStr = config.getString(path + ".trim.material");

                TrimPattern pattern = Registry.TRIM_PATTERN.get(NamespacedKey.minecraft(patternStr.toLowerCase()));
                TrimMaterial trimMat = Registry.TRIM_MATERIAL.get(NamespacedKey.minecraft(materialStr.toLowerCase()));

                if (pattern != null && trimMat != null) {
                    trim = new ArmorTrim(trimMat, pattern);
                }
            }

            ArmorRegistry armor = new ArmorRegistry(id, mat, name, lore, color, trim);
            armorMap.put(id.toLowerCase(), armor);

        }

    }

    private Color hexToColor(String hex) {
        try {
            java.awt.Color javaColor = java.awt.Color.decode(hex);
            return Color.fromRGB(javaColor.getRed(), javaColor.getGreen(), javaColor.getBlue());
        } catch (Exception e) {
            return Color.WHITE;
        }
    }

    public ArmorRegistry getArmor(String id) {
        return armorMap.get(id.toLowerCase());
    }
    public Map<String, ArmorRegistry> getArmorMap() { return armorMap; }
}
