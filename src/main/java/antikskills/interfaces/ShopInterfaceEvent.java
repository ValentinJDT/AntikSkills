package antikskills.interfaces;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class ShopInterfaceEvent implements Listener {

    private AntikPlayerManager antikPlayerManager = AntikSkills.getAntikPlayerManager();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        InventoryView inventoryView = e.getView();

        if(!inventoryView.getTitle().contains("§8Récompense de niveau")) return;

        ItemStack item = e.getCurrentItem();

        if(item == null || !item.hasItemMeta()) return;
        e.setCancelled(true);

        String itemName = item.getItemMeta().getDisplayName();

        if(itemName.equals("§7")) return;
        inventoryView.close();

        if(!player.isOp())
            player.sendMessage("§cFeature non disponible sur le serveur Survie Temporaire");

        antikPlayerManager.getAntikPlayer(player).removeRewardPoint();
    }

}
