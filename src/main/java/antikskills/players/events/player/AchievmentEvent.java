package antikskills.players.events.player;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayer;
import antikskills.players.AntikPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;


public class AchievmentEvent implements Listener {

    private AntikPlayerManager antikPlayerManager = AntikSkills.getAntikPlayerManager();

    @EventHandler
    public void onObtain(PlayerAdvancementDoneEvent e) {
        AntikPlayer antikPlayer = antikPlayerManager.getAntikPlayer(e.getPlayer());
        if(antikPlayer == null) return;

        antikPlayer.addExp(250);
    }


}
