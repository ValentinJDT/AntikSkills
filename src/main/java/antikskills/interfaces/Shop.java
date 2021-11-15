package antikskills.interfaces;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayer;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Shop {

    public enum Type {
        MAIN("§8Récompense de niveau (§3%%points%%§8)", 9*3, Material.WHITE_STAINED_GLASS_PANE, (inventory) -> {
            inventory.setItem(10, Item.HEART.getItem());
            inventory.setItem(12, Item.ATTACK.getItem());
            inventory.setItem(14, Item.PB_WEBSITE.getItem());
            inventory.setItem(16, Item.RANDOM_ITEM.getItem());
            return inventory;
        });

        private final String title;
        private final int size;
        private final Material backGround;
        private final Function<Inventory, Inventory> function;

        Type(String title, int size, Material backGround, Function<Inventory, Inventory> function) {
            this.title = title;
            this.size = size;
            this.backGround = backGround;
            this.function = function;
        }

        public void open(Player player) {
            Inventory inventory;

            if(this == MAIN) {
                AntikPlayer antikPlayer = AntikSkills.getAntikPlayerManager().getAntikPlayer(player);
                String pts = antikPlayer.getRewardPoints() <= 1 ? antikPlayer.getRewardPoints() + " point" : antikPlayer.getRewardPoints() + " points";
                inventory = Bukkit.createInventory(null, this.size, this.title.replace("%%points%%", pts));
            } else {
                inventory = Bukkit.createInventory(null, this.size, this.title);
            }

            for(int i = 0; i<this.size; i++)
                inventory.setItem(i, getBackground());

            player.openInventory(function.apply(inventory));
        }

        private ItemStack getBackground() {
            ItemStack backGround = new ItemStack(this.backGround);
            ItemMeta itemMeta = backGround.getItemMeta();
            itemMeta.setDisplayName("§7");
            backGround.setItemMeta(itemMeta);
            return backGround;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum Item {
        // Main menu
        HEART(Material.REDSTONE, 1, "§7>> §4Coeur en plus", new String[] { "§7Obtiens un coeur en plus de manière définitive !" }, (player) -> {
            AttributeInstance attributable = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            attributable.setBaseValue(attributable.getValue() + 2); // Default 20
            player.playSound(player.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 0.5f,1.0f);
            player.sendMessage("§7Vous avez obtenu §4Coeur en plus §7!");
            return true;
        }),
        ATTACK(Material.DIAMOND_SWORD, 1, "§7>> §3Diminuer les délais d'attaque", new String[] { "§7Réduis de §e20% §7ton délais d'attaque !" }, (player) -> {
            if(true) {
                player.sendMessage("§cFonctionnalité non disponible");
                return false;
            }

            AttributeInstance attributable = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
            attributable.setBaseValue(attributable.getValue()*1.20); // Default 1.4
            player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 0.5f,1.0f);
            player.sendMessage("§7Vous avez obtenu §3Diminuer les délais d'attaque §7!");
            return true;
        }),
        PB_WEBSITE(Material.GOLD_NUGGET, 1, "§7>> §eArgent", new String[] { "§7Obtiens des §eeCoins §7 : ", "§7 - §e5000eC", "§7 - §e3500eC", "§7 - §e1800eC" }, (player) -> {
            Random random = new Random();

            double[] money = new double[] {
                    5000,
                    3500,
                    1800
            };

            AntikSkills.getInstance().getEconomy().depositPlayer(player, money[random.nextInt(money.length - 1)]);
            player.sendMessage("§7Vous avez obtenu §e" + money + "eC §7!");
            return true;
        }),
        RANDOM_ITEM(Material.PAPER, 1, "§7>> §5Récompense mystère", new String[] { "§7Obtiens des récompenses mystères !", "§7Vous pouvez recevoir des §eclefs§7, des §eitems§7, des §eboosts§7, des §eECoins§7, §e..." }, (player) -> {
            if(true) {
                player.sendMessage("§cFonctionnalité non disponible");
                return false;
            }

            return true;
        });

        private final Material material;
        private final int amount;
        private final String name;
        private final List<String> description;
        private final Function<Player, Boolean> action;

        Item(Material material, int amount, String name, String[] description, Function<Player, Boolean> action) {
            this.material = material;
            this.amount = amount;
            this.name = name;
            this.description = Arrays.stream(description).collect(Collectors.toList());
            this.action = action;
        }

        public ItemStack getItem() {
            ItemStack itemStack = new ItemStack(this.material, this.amount);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(this.name);
            itemMeta.setLore(this.description);
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        }

        public String getName() {
            return this.name;
        }

        public boolean run(Player player) {
            return this.action.apply(player);
        }
    }

}
