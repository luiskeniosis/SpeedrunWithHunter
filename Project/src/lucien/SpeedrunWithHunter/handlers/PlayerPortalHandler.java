package lucien.SpeedrunWithHunter.handlers;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

import lucien.SpeedrunWithHunter.game.Main;
import lucien.SpeedrunWithHunter.utilities.CompassTracking;
import lucien.SpeedrunWithHunter.utilities.DimensionalUtility;

public class PlayerPortalHandler implements Listener { 
    @EventHandler
    public void handle(PlayerPortalEvent event) {
	UUID playerUUID = event.getPlayer().getUniqueId();
	if(Main.runners.contains(playerUUID)) {
	    World.Environment realmEntered = event.getTo().getWorld().getEnvironment();
	    if(realmEntered == World.Environment.NETHER) {
		DimensionalUtility.runnersInOverworld.remove(playerUUID);
		DimensionalUtility.runnersInNether.add(playerUUID);
		if(!DimensionalUtility.beenInNether.contains(playerUUID))
		    DimensionalUtility.grantAbsorption(playerUUID);
		CompassTracking.setLastPortalInOverworld(event.getFrom());
		DimensionalUtility.breakNetherPortal(event.getFrom());
	    }
	    else if(realmEntered == World.Environment.THE_END) {
		DimensionalUtility.runnersInOverworld.remove(playerUUID);
		DimensionalUtility.runnersInEnd.add(playerUUID);
		Bukkit.getPlayer(playerUUID).setAbsorptionAmount(20.0);
	    }
	    else if(realmEntered == World.Environment.NORMAL) {
		DimensionalUtility.runnersInNether.remove(playerUUID);
		DimensionalUtility.runnersInOverworld.add(playerUUID);
		CompassTracking.setLastPortalInNether(event.getFrom());
	    }
	}
    }
}
