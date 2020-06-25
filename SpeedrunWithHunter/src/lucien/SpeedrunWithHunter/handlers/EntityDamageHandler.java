package lucien.SpeedrunWithHunter.handlers;

import org.bukkit.World.Environment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EntityDamageHandler implements Listener {
    @EventHandler
    public void handle(EntityDamageEvent event) {
	Entity entity = event.getEntity();
	if(entity.getWorld().getEnvironment() == Environment.NETHER && entity instanceof Player && 
		(event.getCause() == EntityDamageEvent.DamageCause.FIRE || event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK)) {
	    PotionEffect resistance = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 25, 0, true, false, false);
	    ((Player) entity).addPotionEffect(resistance);
	}
    }
}
