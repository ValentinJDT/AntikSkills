package antikskills.players.events.player;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    private AntikPlayerManager antikPlayerManager = AntikSkills.getAntikPlayerManager();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(!antikPlayerManager.isPlayerExist(e.getPlayer()))
            antikPlayerManager.createPlayer(e.getPlayer());

        //e.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
    }
}
