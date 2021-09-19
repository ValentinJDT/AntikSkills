package antikskills;

import antikskills.commands.BackupCommand;
import antikskills.commands.LevelCommand;
import antikskills.commands.LevelTabCompleter;
import antikskills.commands.LevelsCommand;
import antikskills.players.AntikPlayerManager;
import antikskills.players.events.EventCenter;
import antikskills.players.sql.AntikPlayerInterface;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntikSkills extends JavaPlugin {

    private PluginManager pluginManager;
    private EventCenter eventCenter;
    private AntikPlayerInterface antikPlayerInterface;
    private static AntikPlayerManager antikPlayerManager;
    private static AntikSkills instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        antikPlayerInterface = new AntikPlayerInterface(this);
        antikPlayerManager = new AntikPlayerManager(this);
        pluginManager = getServer().getPluginManager();
        eventCenter = new EventCenter(this);

        getCommand("level").setExecutor(new LevelCommand());
        getCommand("level").setTabCompleter(new LevelTabCompleter(this));

        getCommand("levels").setExecutor(new LevelsCommand(this));

        getCommand("backup").setExecutor(new BackupCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        antikPlayerManager.save();
    }

    public static AntikPlayerManager getAntikPlayerManager() {
        return antikPlayerManager;
    }

    public static AntikSkills getInstance() {
        return instance;
    }

    public PluginManager getPluginManager() {
        return this.pluginManager;
    }

    public AntikPlayerInterface getAntikPlayerInterface() {
        return antikPlayerInterface;
    }

}
