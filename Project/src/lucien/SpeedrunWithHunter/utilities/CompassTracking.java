package lucien.SpeedrunWithHunter.utilities;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.StructureType;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class CompassTracking {
    private static Location lastUsedNetherPortalInOverworld = null;
    private static Location lastUsedNetherPortalInNether = null;

    public static void track(Player playerTracking) {
	Player target = getNearestPlayer(playerTracking);
	if(playerTracking.getWorld().getEnvironment() == World.Environment.NORMAL) {
	    if(!DimensionalUtility.runnersInEnd.isEmpty()) {
		Location targetLocation = playerTracking.getWorld().locateNearestStructure(playerTracking.getLocation(), StructureType.STRONGHOLD, 64, false);
		playerTracking.setCompassTarget(targetLocation);
		playerTracking.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &eA runner made it to the End. Tracking nearest &fEnd Portal&e."));
		playerTracking.setCooldown(Material.COMPASS, 100);
	    }
	    else if(target != null) {
		Location targetLocation = target.getLocation();
		playerTracking.setCompassTarget(targetLocation);
		playerTracking.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &eYou're Tracking &f" + target.getName()));
		playerTracking.setCooldown(Material.COMPASS, 100);
	    }
	    else {
		Location targetLocation = getLastPortalInOverworld();
		playerTracking.setCompassTarget(targetLocation);
		playerTracking.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &eNo runners in the Overworld. You're tracking the last nether portal used."));
		playerTracking.setCooldown(Material.COMPASS, 100);
	    }
	}
	else if(playerTracking.getWorld().getEnvironment() == World.Environment.NETHER) {
	    if(target != null) {
		Location targetLocation = target.getLocation();
		playerTracking.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &eYou're Tracking &f" + target.getName()));
		playerTracking.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &f("+(int)targetLocation.getX()
		+", "+(int)targetLocation.getY()+", "+(int)targetLocation.getZ()+")"));
		playerTracking.setCooldown(Material.COMPASS, 100);
	    }
	    else {
		Location targetLocation = getLastPortalInNether();
		playerTracking.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &eNo runners in the Nether. The coordinates of the last portal used:"));
		playerTracking.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&5Lucien&l&dAI&r&8] &f("+(int)targetLocation.getX()
		+", "+(int)targetLocation.getY()+", "+(int)targetLocation.getZ()+")"));

	    }
	}
    }

    public static Player getNearestPlayer(Player playerTracking) {
	Player target = null;
	if(playerTracking.getWorld().getEnvironment() == World.Environment.NORMAL) {
	    if(!DimensionalUtility.runnersInOverworld.isEmpty()) {
		double currentSearchDistance = Double.POSITIVE_INFINITY;
		for(UUID uuid : DimensionalUtility.runnersInOverworld) {
		    Player runner = Bukkit.getPlayer(uuid);
		    double distanceBetween = playerTracking.getLocation().distance(runner.getLocation());
		    if(distanceBetween > currentSearchDistance)
			continue;
		    else
			currentSearchDistance = distanceBetween;
		    target = runner;
		}
	    }
	    else
		return target;
	}
	else if(playerTracking.getWorld().getEnvironment() == World.Environment.NETHER) {
	    if(!DimensionalUtility.runnersInNether.isEmpty()) {
		double currentSearchDistance = Double.POSITIVE_INFINITY;
		for(UUID uuid : DimensionalUtility.runnersInNether) {
		    Player runner = Bukkit.getPlayer(uuid);
		    double distanceBetween = playerTracking.getLocation().distance(runner.getLocation());
		    if(distanceBetween > currentSearchDistance)
			continue;
		    else
			currentSearchDistance = distanceBetween;
		    target = runner;
		}
	    }
	    else
		return target;
	}
	return target;
    }

    public static Location getLastPortalInOverworld() {
	return lastUsedNetherPortalInOverworld;
    }

    public static void setLastPortalInOverworld(Location portalLocation) {
	lastUsedNetherPortalInOverworld = portalLocation;
    }

    public static Location getLastPortalInNether() {
	return lastUsedNetherPortalInNether;
    }

    public static void setLastPortalInNether(Location portalLocation) {
	lastUsedNetherPortalInNether = portalLocation;
    }
}
