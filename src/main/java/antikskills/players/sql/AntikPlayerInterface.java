package antikskills.players.sql;

import antikskills.AntikSkills;
import antikskills.players.AntikPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AntikPlayerInterface extends SQLite {

    public AntikPlayerInterface(AntikSkills main) {
        super(main, "AntikPlayers");

        execute("CREATE TABLE IF NOT EXISTS AntikPlayers ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name varchar(64) not null,"
                + "level integer not null,"
                + "exp integer null,"
                + "reward_points integer null"
                + ");");
    }

    public List<AntikPlayer> getAntikPlayerListDB() {
        List<Map<String, String>> list = fetchAll("SELECT * FROM AntikPlayers");

        List<AntikPlayer> antikPlayerList = new ArrayList<>();

        for(Map<String, String> map : list) {

            AntikPlayer antikPlayer = new AntikPlayer();
            antikPlayer.setName(map.get("name"));
            antikPlayer.setLevel(Integer.parseInt(map.get("level")));
            antikPlayer.setExp(Integer.parseInt(map.get("exp")));
            antikPlayer.setRewardPoints(Integer.parseInt(map.get("reward_points")));

            antikPlayerList.add(antikPlayer);
        }

        return antikPlayerList;
    }

    public void saveAntikPlayerList(List<AntikPlayer> antikPlayerList) {

        String insertString = "INSERT INTO AntikPlayers (`id`, `name`, `level`, `exp`, `reward_points`) VALUES ";

        List<String> values = new ArrayList<>();
        List<String> updates = new ArrayList<>();

        List<Map<String, String>> list = fetchAll("SELECT * FROM AntikPlayers");

        for(AntikPlayer antikPlayer : antikPlayerList) {
            if(list.stream().anyMatch(map -> map.get("name").equals(antikPlayer.getName()))) {
                updates.add(String.format("UPDATE AntikPlayers SET level = %d, exp = %d, reward_points = %d WHERE name = '%s'", antikPlayer.getLevel(), antikPlayer.getExp(), antikPlayer.getRewardPoints(), antikPlayer.getName()));
            } else {
                values.add(String.format("(NULL, '%s', %d, %d, %d)", antikPlayer.getName(), antikPlayer.getLevel(), antikPlayer.getExp(), antikPlayer.getRewardPoints()));
            }
        }

        insertString += String.join(", ", values);

        if(values.size() > 0) {
            execute(insertString);
        }

        if(updates.size() > 0) {
            updates.forEach(SQLite::execute);
        }
    }

    public boolean isAntikPlayerExist(String name) {
        return fetchAll("SELECT * FROM AntikPlayers WHERE name = '" + name + "';").size() > 0;
    }
}
