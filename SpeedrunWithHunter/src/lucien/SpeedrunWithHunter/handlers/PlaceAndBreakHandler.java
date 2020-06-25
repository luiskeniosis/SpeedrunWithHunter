package lucien.SpeedrunWithHunter.handlers;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import lucien.SpeedrunWithHunter.game.FoundrySystem;

public class PlaceAndBreakHandler implements Listener {
    @EventHandler
    public void handle(BlockPlaceEvent event) {
	Block blockPlaced = event.getBlock();
	if(blockPlaced.getType() == Material.CAULDRON)
	    FoundrySystem.cauldronPlaced(blockPlaced, event.getPlayer());
	else if(blockPlaced.getType() == Material.CAMPFIRE)
	    FoundrySystem.campfirePlaced(blockPlaced, event.getPlayer());
    }

    @EventHandler
    public void handle(BlockBreakEvent event) {
	Block blockBroken = event.getBlock();
	if(blockBroken.getType() == Material.CAULDRON)
	    FoundrySystem.cauldronBroken(blockBroken, event.getPlayer());
	else if(blockBroken.getType() == Material.CAMPFIRE)
	    FoundrySystem.campfireBroken(blockBroken, event.getPlayer());
    }
}
