package antikskills.commands;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;
import java.util.stream.Collectors;

public class LevelTabCompleter implements TabCompleter {

    private AntikSkills antikSkills;

    public LevelTabCompleter(AntikSkills antikSkills) {
        this.antikSkills = antikSkills;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1) {
            return antikSkills.getAntikPlayerInterface().getAntikPlayerListDB().stream().map(AntikPlayer::getName).collect(Collectors.toList());
        }

        return null;
    }
}
