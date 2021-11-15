package antikskills.interfaces;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayer;
import antikskills.players.AntikPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Optional;

public class ShopInterfaceEvent implements Listener {

    private AntikPlayerManager antikPlayerManager = AntikSkills.getAntikPlayerManager();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        InventoryView inventoryView = player.getOpenInventory();

        if(Arrays.stream(Shop.Type.values()).noneMatch(type -> inventoryView.getTitle().contains(type.getTitle().replace(" (§3%%points%%§8)", ""))))
            return;

        if(e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta())
            return;

        Optional<Shop.Item> menuItem = Arrays.stream(Shop.Item.values()).filter(item -> item.getName().equals(e.getCurrentItem().getItemMeta().getDisplayName()) && item.getItem().getType() == e.getCurrentItem().getType()).findFirst();

        e.setCancelled(true);
        if(!menuItem.isPresent()) return;

        if(menuItem.get().run(player)) {
            antikPlayerManager.getAntikPlayer(player).removeRewardPoint();
            player.getOpenInventory().close();
        } else {
            player.sendMessage("§cUne erreur s'est produite lors de l'exécution de cette action");
        }
    }

}
