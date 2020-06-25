package lucien.HuntersXRunners.handlers;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import lucien.HuntersXRunners.game.GameItems;
import lucien.HuntersXRunners.game.Main;

public class PlayerDeathHandler implements Listener {
    @EventHandler
    public void handle(PlayerDeathEvent event) {
	if(Main.hunters.contains(event.getEntity().getUniqueId())) {
	    List<ItemStack> droppedItems = event.getDrops();
	    Player player = event.getEntity();
	    PlayerInventory playerInventory = player.getInventory();
	    event.setKeepInventory(true);
	    droppedItems.clear();
	    ItemStack itemInMainHand = playerInventory.getItemInMainHand();
	    for(int i = 0; i < 36; i++) {
		ItemStack itemInMainInventory = playerInventory.getItem(i);
		if(itemInMainInventory != null && !GameItems.uniqueItems.contains(itemInMainInventory)) {
		    droppedItems.add(itemInMainInventory);
		    playerInventory.clear(i);
		}
	    }
	    if(itemInMainHand != null) {
		droppedItems.remove(itemInMainHand);
		playerInventory.addItem(itemInMainHand);
	    }
	}
    }
}