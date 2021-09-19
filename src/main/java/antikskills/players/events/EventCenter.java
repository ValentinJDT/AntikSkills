package antikskills.players.events;

import antikskills.AntikSkills;
import antikskills.interfaces.ShopInterfaceEvent;
import antikskills.players.events.entity.*;
import antikskills.players.events.player.*;
import antikskills.players.events.test.ConsumeItem;
import antikskills.players.events.test.DropEvent;
import antikskills.players.events.test.PiglinEvent;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.List;

public class EventCenter {

    private AntikSkills antikSkills;
    private PluginManager pluginManager;

    public EventCenter(AntikSkills antikSkills) {
        this.antikSkills = antikSkills;
        this.pluginManager = antikSkills.getPluginManager();
        registerEvents();
    }

    private void registerEvents() {
        loadPlayerEvents();
        loadEntityEvents();
        loadOtherEvents();
        loadTestEvents();
    }

    private void loadOtherEvents() {
        List<Listener> listeners = new ArrayList<>();
        listeners.add(new ShopInterfaceEvent());

        registers(listeners);
    }

    private void loadEntityEvents() {
        List<Listener> listeners = new ArrayList<>();
        listeners.add(new ArrowEvent());
        listeners.add(new TeleportEvent());
        listeners.add(new KillEvent());
        listeners.add(new TradeEvent());
        listeners.add(new ShearEvent());

        registers(listeners);
    }

    private void loadPlayerEvents() {
        List<Listener> listeners = new ArrayList<>();
        listeners.add(new JoinEvent());
        listeners.add(new BlocksEvents());
        listeners.add(new CraftEvents());
        listeners.add(new FishEvent());
        listeners.add(new AchievmentEvent());

        registers(listeners);
    }

    private void loadTestEvents() {
        List<Listener> listeners = new ArrayList<>();
        listeners.add(new ConsumeItem());
        listeners.add(new PiglinEvent());

        registers(listeners);
    }

    private void registers(List<Listener> listeners) {
        for(Listener listener : listeners)
            this.pluginManager.registerEvents(listener, antikSkills);
    }
}
