package lucien.HuntersXRunners.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import lucien.HuntersXRunners.game.Main;

public class PlayerMoveHandler implements Listener {
    private static PotionEffect runnerSpeed = new PotionEffect(PotionEffectType.SPEED, 100, 0, true, false, false);
    
    @EventHandler
    public void handle(PlayerMoveEvent event) {
	Player player = event.getPlayer();
	if(!GameStartHandler.gameStartFallingPlayers.isEmpty() && Main.lobbyPeriod == false){
	    if(GameStartHandler.gameStartFallingPlayers.contains(player.getUniqueId()) && (player.isOnGround() || player.isSwimming())) {
		player.removePotionEffect(PotionEffectType.SLOW_FALLING);
		GameStartHandler.gameStartFallingPlayers.remove(player.getUniqueId());
	    }
	}
	if(Main.runners.contains(player.getUniqueId())) {
	    if(event.getTo().getX() != event.getFrom().getX() ||
		    event.getTo().getY() != event.getFrom().getY() ||
		    event.getTo().getZ() != event.getFrom().getZ()) {
		if(!player.hasPotionEffect(PotionEffectType.SPEED))
		    player.addPotionEffect(runnerSpeed);
	    }
	}
    }
}
