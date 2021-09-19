package antikskills.commands;

import antikskills.AntikSkills;
import antikskills.interfaces.ShopInterface;
import antikskills.players.AntikPlayer;
import antikskills.players.AntikPlayerManager;
import antikskills.utils.IntUtils;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LevelCommand implements CommandExecutor {

    private AntikPlayerManager antikPlayerManager = AntikSkills.getAntikPlayerManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        AntikPlayer antikPlayer;

        if(args.length == 0) {
            antikPlayer = antikPlayerManager.getAntikPlayer(sender.getName());
        } else {

            if(args[0].equals("shop")) {
                antikPlayer = antikPlayerManager.getAntikPlayer(player);

                if(antikPlayer.getRewardPoints() > 0) {
                    ShopInterface shop = new ShopInterface(player);
                    shop.open();
                } else {
                    player.sendMessage("§cVous n'avez pas assez de point de récompense");
                }

                return true;

            } else if(antikPlayerManager.isPlayerExist(args[0])) {
                antikPlayer = antikPlayerManager.getAntikPlayer(args[0]);

            } else {
                player.sendMessage("§cCe joueur n'existe pas");
                return true;
            }
        }

        String level = IntUtils.RomanNumerals(antikPlayer.getLevel());
        String percentage = String.valueOf((double) antikPlayer.getExp() / (double) antikPlayer.expForLevel()*100D).split("\\.")[0];

        TextComponent text = new TextComponent("§7(§b" + percentage + "%§7)");

        text.setHoverEvent(
                new HoverEvent(
                        HoverEvent.Action.SHOW_TEXT,
                        new Text("§b" + antikPlayer.getExp() + "§7/§b" + antikPlayer.expForLevel()))
        );

        player.spigot().sendMessage(new TextComponent("§7Niveau actuel §b" + level + " "), text);

        return true;
    }
}
