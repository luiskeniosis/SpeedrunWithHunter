package lucien.HuntersXRunners.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
	//((Player) sender).getInventory().addItem(GameItems.playerTracker);
	//Bukkit.getPluginManager().callEvent(new GameStartEvent());
	Player player = (Player) sender;
	Bukkit.broadcastMessage(player.getUniqueId() + "");
	return false;
    }
}
