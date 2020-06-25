package lucien.SpeedrunWithHunter.game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import lucien.SpeedrunWithHunter.commands.GraceCommand;
import lucien.SpeedrunWithHunter.commands.TestCommand;
import lucien.SpeedrunWithHunter.handlers.CraftingEventsHandler;
import lucien.SpeedrunWithHunter.handlers.EntityDamageHandler;
import lucien.SpeedrunWithHunter.handlers.FoundrySystemHandler;
import lucien.SpeedrunWithHunter.handlers.GameStartHandler;
import lucien.SpeedrunWithHunter.handlers.PlaceAndBreakHandler;
import lucien.SpeedrunWithHunter.handlers.PlayerDeathHandler;
import lucien.SpeedrunWithHunter.handlers.PlayerInteractHandler;
import lucien.SpeedrunWithHunter.handlers.PlayerJoinHandler;
import lucien.SpeedrunWithHunter.handlers.PlayerMoveHandler;
import lucien.SpeedrunWithHunter.handlers.PlayerPortalHandler;
import lucien.SpeedrunWithHunter.handlers.PreGameCancelledHandler;

public class Main extends JavaPlugin {
    public static Main plugin;

    //Sets the preGame and graceMode status to true upon server load
    public static boolean lobbyPeriod = true;
    public static boolean postGame = false;
    
    //Stores runners and hunters after they've been assigned teams
    public static List<UUID> runners = new ArrayList<UUID>();
    public static List<UUID> hunters = new ArrayList<UUID>();

    @Override
    public void onEnable() {
	//Stores "this" for use in other classes
	plugin = this;

	//Sets vanilla gameRules
	setGamerules();

	//Sets up game functions
	registerCommands();
	registerHandlers();
	GameItems.generateLobbyItems();
	GameItems.generateGameItems();
    }
    
    private void setGamerules() {
	World world = getServer().getWorld("hxr");
	world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
	world.setGameRule(GameRule.SPAWN_RADIUS, 1);
	world.setGameRule(GameRule.MOB_GRIEFING, false);
	world.setSpawnLocation(0, 100, 0);
    }

    private void registerCommands() {
	getCommand("grace").setExecutor(new GraceCommand());
	getCommand("test").setExecutor(new TestCommand());
    }
    
    private void registerHandlers() {
	PluginManager pluginManager = getServer().getPluginManager();
	pluginManager.registerEvents(new PlayerInteractHandler(), this);
	pluginManager.registerEvents(new PlayerJoinHandler(), this);
	pluginManager.registerEvents(new PreGameCancelledHandler(), this);
	pluginManager.registerEvents(new PlayerMoveHandler(), this);
	pluginManager.registerEvents(new PlayerDeathHandler(), this);
	pluginManager.registerEvents(new GameStartHandler(), this);
	pluginManager.registerEvents(new CraftingEventsHandler(), this);
	pluginManager.registerEvents(new PlayerPortalHandler(), this);
	pluginManager.registerEvents(new PlaceAndBreakHandler(), this);
	pluginManager.registerEvents(new FoundrySystemHandler(), this);
	pluginManager.registerEvents(new EntityDamageHandler(), this);
    }
}
