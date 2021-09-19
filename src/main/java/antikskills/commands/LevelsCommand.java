package antikskills.commands;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayer;
import antikskills.utils.IntUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LevelsCommand implements CommandExecutor {

    private AntikSkills antikSkills;

    public LevelsCommand(AntikSkills antikSkills) {
        this.antikSkills = antikSkills;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        List<AntikPlayer> antikPlayerList = new ArrayList<>(AntikSkills.getAntikPlayerManager().getAntikPlayerList());

        //antikPlayerList.sort(Comparator.comparingInt(AntikPlayer::getLevel));

        antikPlayerList.sort((p1, p2) -> p2.getLevel() - p1.getLevel());

        List<String> players = new ArrayList<>();

        int i = 1;
        for(AntikPlayer antikPlayer : antikPlayerList.stream().limit(10).collect(Collectors.toList())) {
            players.add("§b" + i + " §7- §b" + IntUtils.RomanNumerals(antikPlayer.getLevel()) + " " + antikPlayer.getName());
            i++;
        }

        sender.sendMessage("§7Leaderboard des niveaux : \n" + String.join("§7, \n", players));

        return false;
    }

}
