package antikskills.players.events.player;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayer;
import antikskills.players.AntikPlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;


public class AchievmentEvent implements Listener {

    private final AntikPlayerManager antikPlayerManager = AntikSkills.getAntikPlayerManager();

    @EventHandler
    public void onObtain(PlayerAdvancementDoneEvent e) {
        if(e.getAdvancement().getCriteria().stream().anyMatch(adv -> adv.contains("the_recipe") || adv.contains("has_")))
            return;

        AntikPlayer antikPlayer = antikPlayerManager.getAntikPlayer(e.getPlayer());
        if(antikPlayer == null) return;

        antikPlayer.addExp(380);
    }


}
