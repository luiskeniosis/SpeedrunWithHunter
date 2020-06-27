package lucien.SpeedrunWithHunter.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import lucien.SpeedrunWithHunter.kits.Warper;

public class PlayerTeleportHandler implements Listener {
    @EventHandler
    public void handle(PlayerTeleportEvent event) {
	if(event.getCause() == TeleportCause.ENDER_PEARL && Warper.warpersAndPearls.containsKey(event.getPlayer().getUniqueId()))
	    event.setCancelled(true);
    }
}
