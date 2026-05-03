package jortoo.muddify.core.mining.inventories;

import jortoo.muddify.Muddify;
import jortoo.muddify.core.mining.DropsManager;
import jortoo.muddify.core.mining.holders.BackpackHolder;
import jortoo.muddify.core.playerdata.PlayerData;
import jortoo.muddify.core.playerdata.PlayerManager;
import jortoo.muddify.utils.menu.MenuSlots;
import jortoo.muddify.utils.text.MetaText;
import jortoo.muddify.utils.text.TextHelper;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BackPackInventory {

    private final PlayerManager pManager;

    public BackPackInventory(PlayerManager pManager) {
        this.pManager = pManager;
    }

    public void open(Player player) {

        PlayerData data = pManager.get(player.getUniqueId());
        if (data == null) return;

        Inventory inv = Bukkit.createInventory(new BackpackHolder(), 54, MiniMessage.miniMessage().deserialize("<black><u>Backpack"));

        int slot = 10;
        int iteration = 0;

        for (DropsManager.DropBluePrint bp : Muddify.plugin.getDropsManager().getDrops().values()) {

            if (iteration >= 7) {
                slot += 2;
                iteration = 0;
            }

            Material mat = bp.item();
            inv.setItem(slot, formatSlot(mat, data.getInt("backpack." + mat.name().toLowerCase(), 0)));

            slot++;
            iteration++;

        }

        slot = 45;

        MenuSlots invSlot = new MenuSlots();

        for (int i = 0; i < 9; i++ ) {

            inv.setItem(slot, invSlot.borderGlass());
            slot++;

        }

        inv.setItem(49, invSlot.closeButton());

        player.openInventory(inv);

    }

    private ItemStack formatSlot(Material mat, int amount) {

        ItemStack slot = new ItemStack(mat, 1);
        ItemMeta meta = slot.getItemMeta();

        String itemName = mat.name().replaceAll("_", " ");
        String zebraText = TextHelper.zebraText("#0090FF", "#27BEF5", itemName);
        Component finalname = new MetaText("<bold>" + zebraText).deser();

        meta.displayName(finalname);
        List<Component> lore = new ArrayList<>();
        lore.add(new MetaText("<dark_gray>Item").deser());
        lore.add(new MetaText("").deser());
        lore.add(new MetaText("<#0090FF> ⏹ <white>Amount: <#27BEF5>" + TextHelper.formatNumber(amount)).deser());
        lore.add(new MetaText("").deser());
        lore.add(new MetaText(TextHelper.miniText("<#0090FF>Click to do nothing")).deser());
        meta.lore(lore);

        slot.setItemMeta(meta);

        return slot;

    }

}
