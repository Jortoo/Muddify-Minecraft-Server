package jortoo.muddify.core.playerdata;

import jortoo.muddify.Muddify;
import jortoo.muddify.utils.text.TextHelper;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class PlayerDataExpansion extends PlaceholderExpansion {

    private final Muddify plugin;

    public PlayerDataExpansion(Muddify plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "jortoo";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Jortoo";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if (player == null) return "";

        PlayerData data = plugin.getPlayerManager().get(player.getUniqueId());
        if (data == null) return "Loading...";

        String key = params.replace("_", ".");
        Object value = data.getAllData().get(key);

        if (value != null) {

            if (value instanceof Number) {

                return TextHelper.formatNumber(((Number) value).doubleValue());
            }

            return String.valueOf(value);
        }

        return "0";
    }
}
