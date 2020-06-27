package lucien.SpeedrunWithHunter.handlers;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import lucien.SpeedrunWithHunter.game.GameItems;
import lucien.SpeedrunWithHunter.game.Main;

public class CraftingEventsHandler implements Listener {
    //Handles PrepareItemCraftEvent
    @EventHandler
    public void handle(PrepareItemCraftEvent event) {
	if(event.getRecipe() != null) {
	    CraftingInventory craftInventory = event.getInventory();
	    for(ItemStack uniqueItem : GameItems.uniqueItems) {
		for(ItemStack craftingInvItems : craftInventory.getContents()) {
		    if(craftingInvItems.hasItemMeta() && craftingInvItems.getItemMeta().hasDisplayName() &&
			    uniqueItem.getItemMeta().getDisplayName().equals(craftingInvItems.getItemMeta().getDisplayName())) {
			craftInventory.setResult(null);
			return;
		    }
		}
	    }
	    for(UUID uuid: Main.runners) {
		if(event.getViewers().contains(Bukkit.getPlayer(uuid))) {
		    String recipeResultName = event.getRecipe().getResult().getType().toString().toLowerCase();
		    if((recipeResultName.contains("pickaxe") || recipeResultName.contains("shovel") || recipeResultName.contains("axe")) &&
			    (recipeResultName.contains("wooden") || recipeResultName.contains("stone"))) {
			ItemStack item = new ItemStack(event.getRecipe().getResult().getType());
			ItemMeta itemMeta = item.getItemMeta();
			itemMeta.addEnchant(Enchantment.DIG_SPEED, 1, false);
			item.setItemMeta(itemMeta);
			craftInventory.setResult(item);
		    }
		}
	    }
	}
    }
}
