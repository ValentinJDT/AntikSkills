package antikskills.interfaces;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopInterface {

    private Inventory inventory;
    private AntikSkills antikSkills = AntikSkills.getInstance();
    private Player player;
    private AntikPlayer antikPlayer;
    private int size;

    public ShopInterface(Player player) {
        this.player = player;
        this.size = 9*3;
        this.antikPlayer = AntikSkills.getAntikPlayerManager().getAntikPlayer(player);

        init();
    }

    private void init() {

        String pts = this.antikPlayer.getRewardPoints() <= 1 ? "point" : "points";

        this.inventory = antikSkills.getServer().createInventory(null, this.size, "§8Récompense de niveau (§3" + this.antikPlayer.getRewardPoints() + " " + pts + "§8)");

        for(int i = 0; i<this.size; i++)
            this.inventory.setItem(i, createItem(Material.WHITE_STAINED_GLASS_PANE, "§7"));

        this.inventory.setItem(10, createItem(Material.REDSTONE, "§7>> §4Coeur en plus"));
        this.inventory.setItem(12, createItem(Material.DIAMOND_SWORD, "§7>> §3Diminuer les délais d'attaque"));
        this.inventory.setItem(14, createItem(Material.GOLD_NUGGET, "§7>> §ePoints boutique"));
        this.inventory.setItem(16, createItem(Material.PAPER, "§7>> §5Récompense mystère"));
    }

    private ItemStack createItem(Material material, String title) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(title);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public void open() {
        player.openInventory(this.inventory);
    }



}
