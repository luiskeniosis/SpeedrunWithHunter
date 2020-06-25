package lucien.HuntersXRunners.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.block.data.type.Campfire;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class FoundrySystem {
    public static ConcurrentHashMap<Location, Integer> listOfFoundrys = new ConcurrentHashMap<Location, Integer>();
    public static ConcurrentHashMap<Location, ArmorStand> listOfLavaStands = new ConcurrentHashMap<Location, ArmorStand>();
    public static List<UUID> createdForgeBefore = new ArrayList<UUID>();
    public static List<UUID> playersForging = new ArrayList<UUID>();

    public static void cauldronPlaced(Block blockPlaced, Player blockPlacer) {
	Location blockPlacedLocation = blockPlaced.getLocation();
	Block blockUnder = blockPlacer.getWorld().getBlockAt(blockPlacedLocation.getBlockX(), blockPlacedLocation.getBlockY() - 1, blockPlacedLocation.getBlockZ());
	if(blockUnder.getType() == Material.CAMPFIRE && ((Campfire) blockUnder.getBlockData()).isLit() &&
		!FoundrySystem.listOfFoundrys.containsKey(blockPlacedLocation)) {
	    listOfFoundrys.put(blockPlacedLocation, 0);
	    listOfLavaStands.put(blockPlacedLocation, generateLavaStand(blockPlacedLocation));
	    blockPlacer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &fYou've created a &eFoundry&f!"));
	}
    }

    public static void campfirePlaced(Block blockPlaced, Player blockPlacer) {
	Location blockPlacedLocation = blockPlaced.getLocation();
	Block blockAbove = blockPlacer.getWorld().getBlockAt(blockPlacedLocation.getBlockX(), blockPlacedLocation.getBlockY() + 1, blockPlacedLocation.getBlockZ());
	Location blockAboveLocation = blockAbove.getLocation();
	if(blockAbove.getType() == Material.CAULDRON && ((Levelled)blockAbove.getBlockData()).getLevel() == 0 &&
		!FoundrySystem.listOfFoundrys.containsKey(blockPlacedLocation)) {
	    listOfFoundrys.put(blockAboveLocation, 0);
	    listOfLavaStands.put(blockAboveLocation, generateLavaStand(blockAboveLocation));
	    blockPlacer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &fYou've created a &eFoundry&f!"));
	}
    }

    private static ArmorStand generateLavaStand(Location forgeLocation) {
	World world = forgeLocation.getWorld();
	ArmorStand lavaStand = (ArmorStand) world.spawnEntity(new Location(world, forgeLocation.getBlockX()+0.5, forgeLocation.getBlockY()-1.7, forgeLocation.getBlockZ()+0.5), EntityType.ARMOR_STAND);
	lavaStand.setMarker(true);
	lavaStand.setVisible(false);
	return lavaStand;
    }
    
    public static void cauldronBroken(Block blockBroken, Player blockBreaker) {
	Location blockBrokenLocation = blockBroken.getLocation();
	if(listOfFoundrys.containsKey(blockBrokenLocation))
	    removeFoundry(blockBreaker, blockBrokenLocation);
    }

    public static void campfireBroken(Block blockBroken, Player blockBreaker) {
	Location blockBrokenLocation = blockBroken.getLocation();
	Block blockAbove = blockBreaker.getWorld().getBlockAt(blockBrokenLocation.getBlockX(), blockBrokenLocation.getBlockY() + 1, blockBrokenLocation.getBlockZ());
	Location blockAboveLocation = blockAbove.getLocation();
	if(listOfFoundrys.containsKey(blockAboveLocation))
	    removeFoundry(blockBreaker, blockAboveLocation);
    }
    
    public static void removeFoundry(Player player, Location forgeLocation) {
	listOfFoundrys.remove(forgeLocation);
	listOfLavaStands.get(forgeLocation).remove();
	player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &fYou've destroyed a &eFoundry&f!"));
    }

    public static void smeltItem(Player player, Location forgeLocation) {
	playersForging.add(player.getUniqueId());
	ItemStack smeltedItem = player.getInventory().getItemInMainHand();
	smeltedItem.setAmount(smeltedItem.getAmount() - 1);
	World world = forgeLocation.getWorld();
	new BukkitRunnable() {
	    int time = 3;
	    Random random = new Random();
	    @Override
	    public void run() {
		if(time != 0) {
		    player.playSound(forgeLocation, Sound.ITEM_BUCKET_FILL_LAVA, 0.6f, random.nextFloat());
		    time--;
		}
		else {
		    listOfFoundrys.put(forgeLocation, listOfFoundrys.get(forgeLocation) + 1);
		    if(listOfFoundrys.get(forgeLocation) == 1) {
			listOfLavaStands.get(forgeLocation).getEquipment().setHelmet(GameItems.lavaHead);
			player.playSound(forgeLocation, Sound.BLOCK_LAVA_EXTINGUISH, 0.3f, 1f);
		    }
		    else if(listOfFoundrys.get(forgeLocation) == 2) {
			listOfLavaStands.get(forgeLocation).teleport(new Location(world, forgeLocation.getBlockX()+0.5, forgeLocation.getBlockY()-1.4, forgeLocation.getBlockZ()+0.5));
			player.playSound(forgeLocation, Sound.BLOCK_LAVA_EXTINGUISH, 0.3f, 1f);
		    }
		    else if(listOfFoundrys.get(forgeLocation) == 3) {
			listOfLavaStands.get(forgeLocation).teleport(new Location(world, forgeLocation.getBlockX()+0.5, forgeLocation.getBlockY()-1.1, forgeLocation.getBlockZ()+0.5));
			player.playSound(forgeLocation, Sound.BLOCK_LAVA_EXTINGUISH, 0.3f, 1f);
		    }
		    playersForging.remove(player.getUniqueId());
		    this.cancel();
		}
	    }
	}.runTaskTimer(Main.plugin, 0l, 20l);
    }

    public static void emptyLava(Player player, Location forgeLocation) {
	listOfFoundrys.put(forgeLocation, 0);
	World world = forgeLocation.getWorld();
	ArmorStand lavaStand = listOfLavaStands.get(forgeLocation);
	lavaStand.teleport(new Location(world, forgeLocation.getBlockX()+0.5, forgeLocation.getBlockY()-1.7, forgeLocation.getBlockZ()+0.5));
	lavaStand.getEquipment().setHelmet(null);
	ItemStack bucket = player.getInventory().getItemInMainHand();
	bucket.setAmount(bucket.getAmount() - 1);
	player.getInventory().addItem(new ItemStack(Material.LAVA_BUCKET));
	player.playSound(forgeLocation, Sound.ITEM_BUCKET_FILL_LAVA, 1f, 1f);
    }
    
    public static Location isFoundryCampfire(Block campfire) {
	Location blockBrokenLocation = campfire.getLocation();
	Block blockAbove = campfire.getWorld().getBlockAt(blockBrokenLocation.getBlockX(), blockBrokenLocation.getBlockY() + 1, blockBrokenLocation.getBlockZ());
	Location blockAboveLocation = blockAbove.getLocation();
	if(listOfFoundrys.containsKey(blockAboveLocation))
	    return blockAboveLocation;
	return null;
    }
}
