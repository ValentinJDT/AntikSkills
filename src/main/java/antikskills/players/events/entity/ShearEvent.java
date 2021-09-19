package antikskills.players.events.entity;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayer;
import antikskills.players.AntikPlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShearEntityEvent;

public class ShearEvent implements Listener {

    private AntikPlayerManager antikPlayerManager = AntikSkills.getAntikPlayerManager();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onShear(PlayerShearEntityEvent e) {
        if(e.isCancelled()) return;

        AntikPlayer antikPlayer = antikPlayerManager.getAntikPlayer(e.getPlayer());
        if(antikPlayer == null) return;

        antikPlayer.addExp(150);
    }
}
