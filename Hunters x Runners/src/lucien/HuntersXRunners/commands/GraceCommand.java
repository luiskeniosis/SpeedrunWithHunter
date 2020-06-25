package lucien.HuntersXRunners.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import lucien.HuntersXRunners.game.Main;

public class GraceCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
	if(sender instanceof Player && sender.getName().equals("luiskeniosis")) {
	    if(arguments.length == 1) {
		if(arguments[0].equals("true") || arguments[0].equals("on")) {
		    Main.lobbyPeriod = true;
		    Main.postGame = false;
		    sender.sendMessage("Grace Mode has been turned On.");
		}
		else if(arguments[0].equals("false") || arguments[0].equals("off")) {
		    Main.lobbyPeriod = false;
		    sender.sendMessage("Grace Mode has been turned Off.");
		}
	    }
	    else
		sender.sendMessage("Grace mode is currently: " + Main.lobbyPeriod);
	}
	return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] arguments) {
	List<String> list = new ArrayList<>();
	list.add("true");
	list.add("false");
	if(command.getName().equalsIgnoreCase("grace")) {
	    return list;
	}
	return null;
    }

}
