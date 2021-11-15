package antikskills.players;

import antikskills.AntikSkills;
import antikskills.players.sql.AntikPlayerInterface;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AntikPlayerManager {

    private AntikSkills antikSkills;
    private AntikPlayerInterface antikPlayerInterface;
    private List<AntikPlayer> antikPlayerList = new ArrayList<>();

    public AntikPlayerManager(AntikSkills antikSkills) {
        this.antikSkills = antikSkills;
        this.antikPlayerInterface = antikSkills.getAntikPlayerInterface();

        load();
        autoSave();
    }

    public void createPlayer(Player player) {
        antikPlayerList.add(new AntikPlayer(player.getName()));
    }

    public List<AntikPlayer> getAntikPlayerList() {
        return antikPlayerList;
    }

    public AntikPlayer getAntikPlayer(String name) {
        return antikPlayerList.stream().filter(antikPlayer -> antikPlayer.getName().equals(name)).findFirst().orElse(null);
    }

    public AntikPlayer getAntikPlayer(Player player) {
        return antikPlayerList.stream().filter(antikPlayer -> antikPlayer.getName().equals(player.getName())).findFirst().orElse(null);
    }

    public boolean isPlayerExist(String name) {
        return antikPlayerList.stream().anyMatch(antikPlayer -> antikPlayer.getName().equals(name));
    }

    public boolean isPlayerExist(Player player) {
        return antikPlayerList.stream().anyMatch(antikPlayer -> antikPlayer.getName().equals(player.getName()));
    }

    public void load() {
        List<AntikPlayer> list = antikPlayerInterface.getAntikPlayerListDB();
        antikPlayerList = list.size() > 0 ? list : new ArrayList<>();
    }

    public void save() {
        antikPlayerInterface.saveAntikPlayerList(antikPlayerList);
        Bukkit.getConsoleSender().sendMessage("§aPlayers levels saved !");
    }

    private void autoSave() {
        Bukkit.getScheduler().runTaskTimer(antikSkills, this::save, 20L*60*30, 20L*60*30);
    }

}
