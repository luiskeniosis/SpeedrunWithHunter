package lucien.HuntersXRunners.utilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Scoreboards {
    public static ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
    
    public static void showInitialScoreboard() {
	Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
	Objective initialScoreboard = scoreboard.registerNewObjective("initial", "dummy", ChatColor.translateAlternateColorCodes('&', "&4&lH&rx&f&lR"));
	initialScoreboard.setDisplaySlot(DisplaySlot.SIDEBAR);
	
	//Score score = initialScoreboard.getScore();
    }
}
