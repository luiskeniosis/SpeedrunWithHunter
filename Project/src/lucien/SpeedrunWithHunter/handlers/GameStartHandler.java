package lucien.SpeedrunWithHunter.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import lucien.SpeedrunWithHunter.events.GameStartEvent;
import lucien.SpeedrunWithHunter.game.GameItems;
import lucien.SpeedrunWithHunter.game.Main;
import lucien.SpeedrunWithHunter.utilities.DimensionalUtility;

public class GameStartHandler implements Listener {
    public static List<UUID> gameStartFallingPlayers = new ArrayList<UUID>();
    private static PotionEffect gameStartSpeed = new PotionEffect(PotionEffectType.SPEED, 100, 1);
    private static PotionEffect gameStartHaste = new PotionEffect(PotionEffectType.FAST_DIGGING, 300, 0);
    private static PotionEffect gameStartDolphinGrace = new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 100, 1);

    @EventHandler
    public void handle(GameStartEvent event) {
	World world = Bukkit.getWorld("hxr");
	int maxX = 151;
	int maxZ = 151;
	Random random = new Random();
	for(Player player : event.getPlayers()) {
	    player.getInventory().removeItem(GameItems.runnerKitSelector, GameItems.hunterKitSelector);
	    player.sendTitle(ChatColor.GREEN + "GO!", "", 0, 40, 10);
	    //Generate a number between 0 and (maxX-1)
	    int x = random.nextInt(maxX);
	    //Generate a number between 0 and (maxZ-1)
	    int z = random.nextInt(maxZ);
	    //If the number generated was in the upper half
	    if(x > 75)
		//Divide it by 2 and reverse it
		x = (x/2) * -1;
	    //If the number generated was in the upper half
	    if(z > 75)
		//Divide it by 2 and reverse it
		z = (z/2) * -1;
	    if(player.hasPermission("hxr.kit.exile"))
		player.teleport(new Location(world, x+200, player.getWorld().getHighestBlockYAt(x+200, z+200) + 50, z+200));
	    else
		player.teleport(new Location(world, x, player.getWorld().getHighestBlockYAt(x, z) + 50, z));
	    gameStartFallingPlayers.add(player.getUniqueId());
	    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 400, 0, false, false));
	    //Play a note
	    player.playNote(player.getLocation(), Instrument.PLING, new Note(13));
	    //Play the begin game sound
	    player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 5, 0.8F);
	    //Set the player's game mode to survival
	    player.setGameMode(GameMode.SURVIVAL);
	}
	Main.lobbyPeriod = false;
	world.setTime(0);
	world.setGameRule(GameRule.MOB_GRIEFING, true);
	for(UUID uuid : Main.runners) {
	    DimensionalUtility.runnersInOverworld.add(uuid);
	    Player player = Bukkit.getPlayer(uuid);
	    player.addPotionEffect(gameStartSpeed);
	    player.addPotionEffect(gameStartHaste);
	    player.addPotionEffect(gameStartDolphinGrace);
	    player.setAbsorptionAmount(20.0);
	}
	for(UUID uuid : Main.hunters) {
	    Bukkit.getPlayer(uuid).getInventory().setItem(8, GameItems.playerTracker);
	}
    }
}
