package lucien.SpeedrunWithHunter.handlers;

import org.bukkit.World.Environment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import lucien.SpeedrunWithHunter.game.Main;
import lucien.SpeedrunWithHunter.kits.Warper;

public class EntityDamageHandler implements Listener {
    @EventHandler
    public void handle(EntityDamageEvent event) {
	Entity entity = event.getEntity();
	DamageCause cause = event.getCause();
	
	//Cancels all entity damage in lobby, and post game
	if(Main.lobbyPeriod ==  true || Main.postGame == true)
	    event.setCancelled(true);
	//All code below applies if entity damaged was a player
	if(entity instanceof Player) {
	    if(cause == EntityDamageEvent.DamageCause.FIRE || cause == EntityDamageEvent.DamageCause.FIRE_TICK) {
		if(entity.getWorld().getEnvironment() == Environment.NETHER) {
		    PotionEffect resistance = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 25, 0, true, false, false);
		    ((Player) entity).addPotionEffect(resistance);
		}
	    }
	    else if(cause == EntityDamageEvent.DamageCause.FALL) {
		if(Warper.antiFallDamage.contains(entity.getUniqueId())) {
		    Warper.antiFallDamage.remove(entity.getUniqueId());
		    event.setCancelled(true);
		} 
	    }
	}
    }
}
