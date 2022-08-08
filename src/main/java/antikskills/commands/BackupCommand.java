package antikskills.commands;

import antikskills.AntikSkills;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BackupCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!sender.hasPermission("*") && !sender.isOp())
            return false;

        if(args.length == 1 && args[0].equals("save")) {
            AntikSkills.getAntikPlayerManager().save();
            sender.sendMessage("§aJoueurs sauvegardés avec succès");

        } else if(args.length == 1 && args[0].equals("load")) {
            AntikSkills.getAntikPlayerManager().load();
            sender.sendMessage("§aJoueurs rechargés avec succès");
        } else {
            sender.sendMessage("§cCommande inconnue");
        }

        return false;
    }
}
