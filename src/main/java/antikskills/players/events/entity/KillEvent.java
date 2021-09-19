package antikskills.players.events.entity;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayer;
import antikskills.players.AntikPlayerManager;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class KillEvent implements Listener {

    private AntikPlayerManager antikPlayerManager = AntikSkills.getAntikPlayerManager();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onKillEntity(EntityDeathEvent e) {

        if(e.getEntity().getKiller() == null) return;
        if(!(e.getEntity() instanceof Animals) && !(e.getEntity() instanceof Monster)) return;

        AntikPlayer antikPlayer = antikPlayerManager.getAntikPlayer(e.getEntity().getKiller());
        if(antikPlayer == null) return;

        int expObtained = 0;

        Entity entity = e.getEntity();

        if(entity instanceof EnderDragon) {
            expObtained = 2500;

        } else if(entity instanceof Wither) {
            expObtained = 1000;

        } else if(entity instanceof Monster) {
            expObtained = 55;
        } else {
            expObtained = 25;
        }

        antikPlayer.addExp(expObtained);
    }
}
