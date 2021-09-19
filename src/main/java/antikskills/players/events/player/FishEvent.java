package antikskills.players.events.player;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayer;
import antikskills.players.AntikPlayerManager;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class FishEvent implements Listener {

    private AntikPlayerManager antikPlayerManager = AntikSkills.getAntikPlayerManager();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onFish(PlayerFishEvent e) {
        if(e.isCancelled()) return;
        if(e.getState() != PlayerFishEvent.State.CAUGHT_FISH) return;

        AntikPlayer antikPlayer = antikPlayerManager.getAntikPlayer(e.getPlayer());
        if(antikPlayer == null) return;

        antikPlayer.addExp(200);
    }
}
