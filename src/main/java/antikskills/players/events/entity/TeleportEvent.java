package antikskills.players.events.entity;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayer;
import antikskills.players.AntikPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TeleportEvent implements Listener {

    private AntikPlayerManager antikPlayerManager = AntikSkills.getAntikPlayerManager();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTeleport(PlayerTeleportEvent e) {
        if(e.isCancelled()) return;

        AntikPlayer antikPlayer = antikPlayerManager.getAntikPlayer(e.getPlayer());
        if(antikPlayer == null) return;

        int expObtained = 0;

        switch (e.getCause()) {
            case END_PORTAL:
                expObtained = 150;
                break;
            case CHORUS_FRUIT:
                expObtained = 50;
                break;
            case END_GATEWAY:
                expObtained = 100;
                break;
            case ENDER_PEARL:
                expObtained = 25;
                break;
            case NETHER_PORTAL:
                expObtained = 20;
                break;
        }

        antikPlayer.addExp(expObtained);
    }

}
