package lucien.SpeedrunWithHunter.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import lucien.SpeedrunWithHunter.game.Main;

public class PreGameCancelledHandler implements Listener {
    //Handles FoodLevelChangeEvent, EntityPickupItemEvent, and PlayerDropItemEvent.
    @EventHandler
    public void noHungerChange(FoodLevelChangeEvent event) {
	if(Main.lobbyPeriod == true)
	    event.setCancelled(true);
    }

    @EventHandler
    public void noItemPickup(EntityPickupItemEvent event) {
	if(Main.lobbyPeriod == true)
	    event.setCancelled(true);
    }

    @EventHandler
    public void noItemDrop(PlayerDropItemEvent event) {
	if(Main.lobbyPeriod == true)
	    event.setCancelled(true);
    }
    
    /*@EventHandler
    public void noEntityDamage(EntityDamageEvent event) {
	if(Main.lobbyPeriod ==  true || Main.postGame == true)
	    event.setCancelled(true);
    } */
}
