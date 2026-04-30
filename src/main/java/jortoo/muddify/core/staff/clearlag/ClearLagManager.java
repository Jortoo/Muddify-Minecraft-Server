package jortoo.muddify.core.staff.clearlag;

import jortoo.muddify.Muddify;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.List;

public class ClearLagManager {

    private final Muddify plugin;

    public ClearLagManager(Muddify plugin) {
        this.plugin = plugin;
    }

    public void runClear() {

        int removeCount = 0;

        for (World world : Bukkit.getWorlds()) {

            List<Entity> entities = world.getEntities();

            for (Entity entity : entities) {

                if (entity.getType() != EntityType.ITEM) {
                    continue;
                }

                removeCount++;
                entity.remove();

            }

        }

        if (removeCount > 0) {
            Bukkit.broadcast(MiniMessage.miniMessage().deserialize("<red>" + removeCount + " Entities have been removed!"));
        }

    }

}
