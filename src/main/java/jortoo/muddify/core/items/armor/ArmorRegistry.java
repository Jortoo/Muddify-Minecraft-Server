package jortoo.muddify.core.items.armor;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimPattern;

import java.util.ArrayList;

public class ArmorRegistry {

    private final String id;
    private final Material material;
    private final String name;
    private final ArrayList<String> lore;
    private final Color color;
    private final ArmorTrim trim;

    public ArmorRegistry(String id, Material material, String name, ArrayList<String> lore, Color color, ArmorTrim trim) {
        this.id = id;
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.color = color;
        this.trim = trim;
    }

    public ItemStack buildItem() {

        ItemStack item = new ItemStack(this.material);
        ItemMeta meta = item.getItemMeta();

        if (meta == null) return null;

        MiniMessage mm = MiniMessage.miniMessage();

        meta.displayName(mm.deserialize("<!italic>" + this.name));

        ArrayList<Component> l = new ArrayList<>();
        for (String s : this.lore) l.add(mm.deserialize("<!italic>" + s));

        meta.lore(l);

        if (this.color != null && meta instanceof LeatherArmorMeta leatherMeta) leatherMeta.setColor(this.color);
        if (this.trim != null && meta instanceof ArmorMeta armorMeta) {
            armorMeta.setTrim(this.trim);
        }

        item.setItemMeta(meta);
        return item;
    }

}
