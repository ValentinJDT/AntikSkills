package antikskills.players.events.test;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class ConsumeItem implements Listener {

    private AntikPlayerManager antikPlayerManager = AntikSkills.getAntikPlayerManager();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEat(PlayerItemConsumeEvent e) {
        if(e.isCancelled()) return;
        antikPlayerManager.getAntikPlayer(e.getPlayer()).addExp(25);
    }
}
