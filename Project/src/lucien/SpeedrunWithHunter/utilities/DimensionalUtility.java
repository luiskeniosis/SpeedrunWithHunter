package lucien.SpeedrunWithHunter.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class DimensionalUtility {
    public static List<UUID> runnersInOverworld = new ArrayList<UUID>();
    public static List<UUID> runnersInNether = new ArrayList<UUID>();
    public static List<UUID> runnersInEnd = new ArrayList<UUID>();
    public static List<UUID> beenInNether = new ArrayList<UUID>();
    
    public static void grantAbsorption(UUID playerUUID) {
	beenInNether.add(playerUUID);
	Player player = Bukkit.getPlayer(playerUUID);
	player.setAbsorptionAmount(player.getAbsorptionAmount() + 10.0);
    }
    
    public static void breakNetherPortal(Location portalLocation) {
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