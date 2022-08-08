package antikskills.players.events.player;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayer;
import antikskills.players.AntikPlayerManager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlocksEvents implements Listener {

    private AntikPlayerManager antikPlayerManager = AntikSkills.getAntikPlayerManager();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent e) {
        if(e.isCancelled()) return;
        if(e.getBlock().getType() == Material.FIRE) return;

        AntikPlayer antikPlayer = antikPlayerManager.getAntikPlayer(e.getPlayer());
        if(antikPlayer == null) return;

        int expObtained = 0;

        Block block = e.getBlock();
        BlockData blockData = block.getBlockData();

        if(e.getBlock().getType().name().contains("_ORE") && !e.getPlayer().getInventory().getItemInMainHand().getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
            expObtained = 150;
        } else if(blockData instanceof Ageable) {
            Ageable crop = (Ageable) blockData;

            if(crop.getAge() == crop.getMaximumAge()) {
                expObtained = 150;
            } else {
                expObtained = 20;
            }
        } else {
            expObtained = 20;
        }

        antikPlayer.addExp(expObtained);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlace(BlockPlaceEvent e) {
        if(e.isCancelled()) return;
        if(e.getBlock().getType() == Material.FIRE) return;

        AntikPlayer antikPlayer = antikPlayerManager.getAntikPlayer(e.getPlayer());
        if(antikPlayer == null) return;

        antikPlayer.addExp(20);
    }

}
