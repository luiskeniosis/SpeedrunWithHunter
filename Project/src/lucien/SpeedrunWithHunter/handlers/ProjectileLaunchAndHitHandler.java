package lucien.SpeedrunWithHunter.handlers;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.projectiles.ProjectileSource;

import lucien.SpeedrunWithHunter.kits.Warper;

public class ProjectileLaunchAndHitHandler implements Listener {
    @EventHandler
    public void handle(ProjectileLaunchEvent event) {
	Projectile entity = event.getEntity();
	if(event.getEntityType() == EntityType.ENDER_PEARL) {
	    ProjectileSource shooter = entity.getShooter();
	    if(shooter instanceof Player && ((Player)shooter).hasPermission("speedrun.profession.warper"))
		Warper.addWarp(entity, (Player)shooter);
	}
    }

    @EventHandler
    public void handle(ProjectileHitEvent event) {
	Projectile entity = event.getEntity();
	if(entity.getType() == EntityType.ENDER_PEARL && Warper.warpersAndPearls.contains(entity.getUniqueId())) {
	    ProjectileSource shooter = entity.getShooter();
	    if(shooter instanceof Player && Warper.warpersAndPearls.containsKey(((Player) shooter).getUniqueId())) {
		if(!entity.getPassengers().isEmpty()) {
		    Warper.warpersAndPearls.remove(((Player) shooter).getUniqueId());
		    Warper.antiFallDamage.remove(((Player) shooter).getUniqueId());
		}
		else
		    Warper.warpersAndPearls.put(((Player) shooter).getUniqueId(), Warper.nullUUID);
	    }
	}
    }
}
