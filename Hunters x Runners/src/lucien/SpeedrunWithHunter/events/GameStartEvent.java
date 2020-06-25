package lucien.SpeedrunWithHunter.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameStartEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private List<Player> playerList = new ArrayList<Player>();
    
    public GameStartEvent() {
	for(Player player : Bukkit.getOnlinePlayers()) {
	    playerList.add(player);
	}
    }
    
    public HandlerList getHandlers() {
	return handlers;
    }

    public static HandlerList getHandlerList() {
	return handlers;
    }

    public List<Player> getPlayers() {
	return playerList;
    }
}