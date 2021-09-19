package antikskills.players.events.player;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayer;
import antikskills.players.AntikPlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftEvents implements Listener {

    private AntikPlayerManager antikPlayerManager = AntikSkills.getAntikPlayerManager();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCraft(CraftItemEvent e) {
        if(e.isCancelled()) return;
        Player player = (Player) e.getWhoClicked();

        AntikPlayer antikPlayer = antikPlayerManager.getAntikPlayer(player);
        if(antikPlayer == null) return;

        ItemStack itemStack = e.getRecipe().getResult();
        Material material = itemStack.getType();
        String itemTypeName = material.name();

        int expObtained = 0;

        if(material == Material.BEACON) {
            expObtained = 500;
        } else if(itemTypeName.contains("_CHESTPLATE")) {
            expObtained = 75;
        } else if(itemTypeName.contains("_LEGGINGS")) {
            expObtained = 50;
        } else if(itemTypeName.contains("_HELMET")) {
            expObtained = 30;
        } else if(itemTypeName.contains("_BOOTS")) {
            expObtained = 30;
        } else {
            expObtained = 20;
        }

        antikPlayer.addExp(expObtained*itemStack.getAmount());
    }
}
