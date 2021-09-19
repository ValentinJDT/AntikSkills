package antikskills.players.events.test;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PiglinBarterEvent;

public class PiglinEvent implements Listener {

    private AntikPlayerManager antikPlayerManager = AntikSkills.getAntikPlayerManager();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPiglinBarter(PiglinBarterEvent e) {
        Player player = (Player) e.getEntity().getNearbyEntities(5,5,5).stream().filter(entity -> entity instanceof Player).findFirst().orElse(null);
        if(player == null) return;

        antikPlayerManager.getAntikPlayer(player).addExp(150);
    }
}
