package antikskills.players.events.entity;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayer;
import antikskills.players.AntikPlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class TradeEvent implements Listener {

    private AntikPlayerManager antikPlayerManager = AntikSkills.getAntikPlayerManager();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTrade(InventoryClickEvent e) {
        if(e.isCancelled()) return;

        if(e.getClickedInventory() == null) return;

        if(e.getClickedInventory().getType() != InventoryType.MERCHANT || e.getSlotType() != InventoryType.SlotType.RESULT || e.getCurrentItem().getType() == Material.AIR)
            return;

        AntikPlayer antikPlayer = antikPlayerManager.getAntikPlayer((Player) e.getWhoClicked());
        if(antikPlayer == null) return;

        antikPlayer.addExp(100);
    }

}
