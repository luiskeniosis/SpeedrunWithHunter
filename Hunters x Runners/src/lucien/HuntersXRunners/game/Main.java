package lucien.HuntersXRunners.game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import lucien.HuntersXRunners.commands.GraceCommand;
import lucien.HuntersXRunners.commands.TestCommand;
import lucien.HuntersXRunners.handlers.CraftingEventsHandler;
import lucien.HuntersXRunners.handlers.EntityDamageHandler;
import lucien.HuntersXRunners.handlers.FoundrySystemHandler;
import lucien.HuntersXRunners.handlers.GameStartHandler;
import lucien.HuntersXRunners.handlers.PlaceAndBreakHandler;
import lucien.HuntersXRunners.handlers.PlayerDeathHandler;
import lucien.HuntersXRunners.handlers.PlayerInteractHandler;
import lucien.HuntersXRunners.handlers.PlayerJoinHandler;
import lucien.HuntersXRunners.handlers.PlayerMoveHandler;
import lucien.HuntersXRunners.handlers.PlayerPortalHandler;
import lucien.HuntersXRunners.handlers.PreGameCancelledHandler;

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
