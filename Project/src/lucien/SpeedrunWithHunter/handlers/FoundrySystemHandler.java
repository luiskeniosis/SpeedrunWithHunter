package lucien.SpeedrunWithHunter.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Campfire;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftThrownPotion;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import lucien.SpeedrunWithHunter.game.FoundrySystem;

public class FoundrySystemHandler implements Listener {
    //Handles CauldronLevelChangeEvent and EntityChangeBlockEvent
    @EventHandler
    public void handle(CauldronLevelChangeEvent event) {
	if(event.getEntity() instanceof Player) {
	    Player player = (Player) event.getEntity();
	    if(event.getNewLevel() != 0) {
		Location cauldronLocation = event.getBlock().getLocation();
		if(FoundrySystem.listOfFoundrys.containsKey(cauldronLocation))
		    FoundrySystem.removeFoundry((Player)event.getEntity(), cauldronLocation);
	    }
	    else
		FoundrySystem.cauldronPlaced(event.getBlock(), player);
	}
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void handle(EntityChangeBlockEvent event) {
	if(event.getBlock().getType() == Material.CAMPFIRE) {
	    Block eventBlock = event.getBlock();
	    Campfire campfire = (Campfire) eventBlock.getBlockData();
	    Location forgeLocation = FoundrySystem.isFoundryCampfire(eventBlock);
	    if(forgeLocation != null && campfire.isLit()) {
		CraftThrownPotion entity = (CraftThrownPotion) event.getEntity();
		String playerNameTemp = entity.getShooter().toString().substring(17);
		String actualPlayerName = playerNameTemp.substring(0, playerNameTemp.length()-1);
		FoundrySystem.removeFoundry(Bukkit.getServer().getOfflinePlayer(actualPlayerName).getPlayer(), forgeLocation);
	    }
	}
    }
}