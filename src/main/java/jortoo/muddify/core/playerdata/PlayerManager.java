package jortoo.muddify.core.playerdata;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

    private final Map<UUID, PlayerData> players = new HashMap<>();
    private final PlayerStorage storage = new PlayerStorage();

    public void load(UUID uuid) {
        players.put(uuid, storage.loadPlayerData(uuid));
    }

    public void unload(UUID uuid) {
        PlayerData data = players.get(uuid);
        if (data != null) {
            storage.savePlayerData(data);
            players.remove(uuid);
        }
    }

    public PlayerData get(UUID uuid) {
        return players.get(uuid);
    }

}
