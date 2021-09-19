package antikskills.players.events.test;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.util.EulerAngle;

public class DropEvent implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Item item = e.getItemDrop();
        Location loc = item.getLocation().clone();
        loc.subtract(0.5,2.5,0);

        ArmorStand armorStand = loc.getWorld().spawn(loc, ArmorStand.class);
        armorStand.setGravity(false);
        armorStand.setArms(true);
        armorStand.setRightArmPose(new EulerAngle(-Math.PI/2, 0,Math.PI/2));
        armorStand.getEquipment().setItemInMainHand(item.getItemStack());
        armorStand.setVisible(false);

        e.getItemDrop().remove();
    }
}
