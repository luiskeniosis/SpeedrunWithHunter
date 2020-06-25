package lucien.SpeedrunWithHunter.handlers;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

import lucien.SpeedrunWithHunter.game.Main;
import lucien.SpeedrunWithHunter.utilities.CompassTracking;
import lucien.SpeedrunWithHunter.utilities.DimensionTracker;

public class PlayerPortalHandler implements Listener { 
    @EventHandler
    public void handle(PlayerPortalEvent event) {
	UUID playerUUID = event.getPlayer().getUniqueId();
	if(Main.runners.contains(playerUUID)) {
	    World.Environment realmEntered = event.getTo().getWorld().getEnvironment();
	    if(realmEntered == World.Environment.NETHER) {
		DimensionTracker.runnersInOverworld.remove(playerUUID);
		DimensionTracker.runnersInNether.add(playerUUID);
		CompassTracking.setLastPortalInOverworld(event.getFrom());
		breakNetherPortal(event.getFrom());
	    }
	    else if(realmEntered == World.Environment.THE_END) {
		DimensionTracker.runnersInOverworld.remove(playerUUID);
		DimensionTracker.runnersInEnd.add(playerUUID);
	    }
	    else if(realmEntered == World.Environment.NORMAL) {
		DimensionTracker.runnersInNether.remove(playerUUID);
		DimensionTracker.runnersInOverworld.add(playerUUID);
		CompassTracking.setLastPortalInNether(event.getFrom());
	    }
	}
    }
    
    private void breakNetherPortal(Location portalLocation) {
	int portalX = portalLocation.getBlockX();
	int portalY = portalLocation.getBlockY();
	int portalZ = portalLocation.getBlockZ();
	int offsetX = 0; int offsetY = 0; int offsetZ = 0;
	int obsidianBroken = 0;
	for(int a = 0; a < 5; a++) {
	    for(int b = 0; b < 4; b++) {
		for(int c = 0; c < 5; c++) {
		    if(obsidianBroken >= 3) return;
		    offsetX = a; offsetY = b; offsetZ = c;
		    if(a > 2) offsetX = (a-2) * -1;
		    if(c > 2) offsetZ = (c-2) * -1;
		    Block testingFor = portalLocation.getWorld().getBlockAt(portalX + offsetX, portalY + offsetY, portalZ + offsetZ);
		    if(testingFor.getType() == Material.OBSIDIAN) {
			testingFor.setType(Material.AIR);
			obsidianBroken++;
		    }
		}
	    }
	}
    }
}
