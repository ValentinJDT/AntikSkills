package antikskills.players.events.entity;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayer;
import antikskills.players.AntikPlayerManager;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionType;

public class ArrowEvent implements Listener {

    private AntikPlayerManager antikPlayerManager = AntikSkills.getAntikPlayerManager();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onArrowHit(ProjectileHitEvent e) {
        if(e.isCancelled()) return;

        if(e.getHitEntity() == null) return;
        if(!(e.getEntity().getShooter() instanceof Player)) return;

        AntikPlayer antikPlayer = antikPlayerManager.getAntikPlayer((Player) e.getEntity().getShooter());

        if(antikPlayer == null) return;

        int expObtened = 0;

        if(e.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) e.getEntity();

            if(arrow.getBasePotionData().getType() != PotionType.UNCRAFTABLE) {
                expObtened = e.getHitEntity() instanceof Player ? 200 : 125;
            } else {
                expObtened = e.getHitEntity() instanceof Player ? 50 : 20;
            }

        } else if(e.getEntity() instanceof Snowball || e.getEntity() instanceof Egg) {
            expObtened = e.getHitEntity() instanceof Player ? 15 : 10;
        }

        antikPlayer.addExp(expObtened);
    }


}
