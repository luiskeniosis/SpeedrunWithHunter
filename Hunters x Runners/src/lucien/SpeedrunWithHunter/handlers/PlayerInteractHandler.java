package lucien.SpeedrunWithHunter.handlers;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import lucien.SpeedrunWithHunter.game.FoundrySystem;
import lucien.SpeedrunWithHunter.game.GameItems;
import lucien.SpeedrunWithHunter.game.LobbySystem;
import lucien.SpeedrunWithHunter.game.Main;
import lucien.SpeedrunWithHunter.utilities.CompassTracking;

public class PlayerInteractHandler implements Listener {
    @EventHandler
    public void handle(PlayerInteractEvent event) {
	Player player = event.getPlayer();
	if(event.hasItem()) {
	    //All code under here involves player clicking with an item
	    ItemStack item = event.getItem();
	    if(Main.lobbyPeriod == true) {
		//All code in this section is during lobby period
		event.setCancelled(true);
		if(event.getHand() == EquipmentSlot.HAND)
		    LobbySystem.registerInteraction(item, event.getPlayer());
	    }
	    else {
		//All code under here is NOT during lobby period
		if(event.hasBlock()) {
		    //All code in this section is if a player clicked a block with an item
		    Block blockClicked = event.getClickedBlock();
		    if(event.getHand() == EquipmentSlot.HAND) {
			if(blockClicked.getType() == Material.CAULDRON && event.getAction() == Action.RIGHT_CLICK_BLOCK &&
				FoundrySystem.listOfFoundrys.containsKey(blockClicked.getLocation()) && !FoundrySystem.playersForging.contains(player.getUniqueId())) {
			    if(FoundrySystem.listOfFoundrys.get(blockClicked.getLocation()) < 3 && GameItems.forgableItems.contains(item.getType()))
				FoundrySystem.smeltItem(player, blockClicked.getLocation());
			    else if(FoundrySystem.listOfFoundrys.get(blockClicked.getLocation()) == 3 && item.getType() == Material.BUCKET)
				FoundrySystem.emptyLava(player, blockClicked.getLocation());
			}
			else if(blockClicked.getType() == Material.CAMPFIRE && event.getAction() == Action.RIGHT_CLICK_BLOCK &&
				item.getType() == Material.WATER_BUCKET)
			    FoundrySystem.campfireBroken(blockClicked, player);
		    }
		}
		if(item.getType() == Material.COMPASS && Main.hunters.contains(player.getUniqueId()) && player.getCooldown(Material.COMPASS) == 0 &&
			event.getHand() == EquipmentSlot.HAND)
		    CompassTracking.track(event.getPlayer());
	    }
	}
    }
}