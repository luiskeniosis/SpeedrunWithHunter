package lucien.HuntersXRunners.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.scheduler.BukkitRunnable;

import lucien.HuntersXRunners.events.GameStartEvent;

public class LobbySystem {
    public static List<UUID> playersReady = new ArrayList<UUID>();
    private static int kitSelectionTimerID;
    private static int kitSelectionTime = 120;
    private static List<UUID> playersWithKits = new ArrayList<UUID>();

    public static void registerInteraction(ItemStack itemClicked, Player playerClicked) {
	if(itemClicked.getType() == Material.RED_WOOL && playerClicked.getCooldown(Material.RED_WOOL) == 0) {
	    playerClicked.getInventory().remove(itemClicked);
	    playerClicked.getInventory().addItem(GameItems.readyWool);
	    playerClicked.setCooldown(Material.GREEN_WOOL, 40);
	    LobbySystem.playersReady.add(playerClicked.getUniqueId());
	    LobbySystem.update();
	}
	if(itemClicked.getType() == Material.GREEN_WOOL && playerClicked.getCooldown(Material.GREEN_WOOL) == 0) {
	    playerClicked.getInventory().remove(itemClicked);
	    playerClicked.getInventory().addItem(GameItems.notReadyWool);
	    playerClicked.setCooldown(Material.RED_WOOL, 100);
	    LobbySystem.playersReady.remove(playerClicked.getUniqueId());
	    LobbySystem.update();
	}
	if(itemClicked.getType() == Material.CHEST)
	    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "gui openfor " + playerClicked.getName() + " runnerkits");
	if(itemClicked.getType() == Material.ENDER_CHEST)
	    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "gui openfor " + playerClicked.getName() + " hunterkits");
    }

    public static void update() {
	Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes ('&', "&8[&5Lucien&l&dAI&r&8] &7Players ready: &e(" + playersReady.size() + "/" + Bukkit.getOnlinePlayers().size() + ")"));
	if(playersReady.size() == Bukkit.getOnlinePlayers().size()) {
	    if(playersReady.size() > 1) {
		for(Player player : Bukkit.getOnlinePlayers())
		    player.getInventory().clear();
		assignTeams();
	    }
	    else
		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes ('&', "&8[&5Lucien&l&dAI&r&8] &cThere must be at least &e2 &cplayers to start the game."));
	}
    }

    private static void assignTeams() {
	for(Player player : Bukkit.getOnlinePlayers()) {
	    PlayerInventory inventory = player.getInventory();
	    inventory.clear();
	}
	Collections.shuffle(playersReady, new Random());
	for(int i = 0; i < playersReady.size(); i++) {
	    Player player = Bukkit.getPlayer(playersReady.get(i));
	    PlayerInventory playerInventory = player.getInventory();
	    if(i < playersReady.size()/2) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &c&l! &eYou're a &4Hunter &ethis game &c&l!"));
		Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " permission set gui.hunterkits");
		Main.hunters.add(player.getUniqueId());
		playerInventory.addItem(GameItems.hunterKitSelector);
	    }
	    else {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &c&l! &eYou're a &fRunner &ethis game &c&l!"));
		Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " permission set gui.runnerkits");
		Main.runners.add(player.getUniqueId());
		playerInventory.addItem(GameItems.runnerKitSelector);
	    }
	}
	startKitSelection();
    }

    private static void startKitSelection() {
	kitSelectionTimerID = new BukkitRunnable() {
	    @Override
	    public void run() {
		if(kitSelectionTime != -1) {
		    if(kitSelectionTime == 120) {
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &7The game will start in &f2 minutes &7or when all players have selected a kit."));
			kitSelectionTime--;
		    }
		    else if(kitSelectionTime == 60) {
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &7There is &f1 minute &7left to choose your kit."));
			kitSelectionTime--;
		    }
		    else if(kitSelectionTime == 10) {
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &7There are &f10 seconds &7left to choose your kit."));
			kitSelectionTime--;
		    }
		    else if(kitSelectionTime == 3) {
			startGameCountdown();
			kitSelectionTime = -1;
		    }
		    else
			kitSelectionTime--;
		    if(kitSelectionTime % 4 == 0)
			kitSelectionCheck();
		}
	    }
	}.runTaskTimer(Main.plugin, 0l, 20l).getTaskId();
    }

    private static void kitSelectionCheck() {
	for(Player player : Bukkit.getOnlinePlayers()) {
	    Set<PermissionAttachmentInfo> permissionSet = player.getEffectivePermissions();
	    for(PermissionAttachmentInfo permissionInfo : permissionSet) {
		if(permissionInfo.getPermission().toLowerCase().contains("hxr.kit.") && !playersWithKits.contains(player.getUniqueId()))
		    playersWithKits.add(player.getUniqueId());
	    }
	}
	if(playersWithKits.size() == Bukkit.getOnlinePlayers().size()) {
	    kitSelectionTime = -1;
	    Bukkit.getScheduler().cancelTask(kitSelectionTimerID);
	    startGameCountdown();
	}
    }

    private static void startGameCountdown() {
	Collection<? extends Player> playerList = Bukkit.getOnlinePlayers();
	new BukkitRunnable() {
	    int timer = 3;
	    @Override
	    public void run() {
		if(timer != -1) {
		    if(timer == 3) {
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &8Game starting in &43 seconds&8!"));
			for(Player players : playerList) {
			    //Display a title screen
			    players.sendTitle(ChatColor.RED + "3", "", 0, 20, 10);
			    //Play a note
			    players.playNote(players.getLocation(), Instrument.PLING, new Note(1));
			}
			timer--;
		    }
		    else if(timer == 2) {
			for(Player players : playerList) {
			    //Display a title screen
			    players.sendTitle(ChatColor.RED + "2", "", 0, 20, 10);
			    //Play a note
			    players.playNote(players.getLocation(), Instrument.PLING, new Note(1));
			}
			timer--;
		    }
		    else if(timer == 1) {
			for(Player players : playerList) {
			    //Display a title screen
			    players.sendTitle(ChatColor.RED + "1", "", 0, 20, 10);
			    //Play a note
			    players.playNote(players.getLocation(), Instrument.PLING, new Note(1));
			}
			timer--;
		    }
		    else if(timer == 0) {
			timer--;
			Bukkit.getPluginManager().callEvent(new GameStartEvent());
		    }
		}
		else
		    this.cancel();
	    }
	}.runTaskTimer(Main.plugin, 0l, 20l);
    }
}
