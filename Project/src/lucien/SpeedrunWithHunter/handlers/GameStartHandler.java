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
	Random random = new Random();
	for(Player player : event.getPlayers()) {
	    player.getInventory().removeItem(GameItems.runnerKitSelector, GameItems.hunterKitSelector);
	    player.sendTitle(ChatColor.GREEN + "GO!", "", 0, 40, 10);
	    int x; int z;
	    if(player.hasPermission("speedrun.profession.exile")) {
		x = random.nextInt(201);
		z = random.nextInt(201);
		if(x > 100) {
		    x = (x/2) * -1;
		    x = x - 200;
		}
		else
		    x = x + 200;
		if(z > 100) {
		    z = (z/2) * -1;
		    z = z - 200;
		}
		else
		    z = z + 200;
		player.teleport(new Location(player.getWorld(), x, player.getWorld().getHighestBlockYAt(x, z) + 50, z));
	    }
	    else {
		x = random.nextInt(151);
		z = random.nextInt(151);
		if(x > 75)
		    x = (x/2) * -1;
		if(z > 75)
		    z = (z/2) * -1;
		player.teleport(new Location(world, x, player.getWorld().getHighestBlockYAt(x, z) + 50, z));
	    }
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
