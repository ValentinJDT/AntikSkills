package antikskills.players.events.player;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayerManager;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    private AntikPlayerManager antikPlayerManager = AntikSkills.getAntikPlayerManager();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(!antikPlayerManager.isPlayerExist(e.getPlayer()))
            antikPlayerManager.createPlayer(e.getPlayer());

        AttributeInstance attr = e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        attr.setBaseValue(attr.getDefaultValue());

        //e.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
    }
}
