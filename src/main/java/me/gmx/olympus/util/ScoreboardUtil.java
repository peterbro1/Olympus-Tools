package me.gmx.olympus.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import me.gmx.olympus.OlympusTools;

public class ScoreboardUtil {
	public String prefix;
	public ScoreboardUtil() {
		prefix = OlympusTools.getInstance().getDescription().getName();
		init();
	}
	
	private void init() {
		

		
		
	}
	
	
	public Team createTeam(Scoreboard board,String s,ChatColor color) {
		Team t = board.getTeam(s) == null ? board.registerNewTeam(s) : board.getTeam(s);
		t.setColor(color);
		t.setPrefix(color + "");
		return t;
		
	}
	
	public void addEntityToPlayerTeam(Entity entity, Player p,Scoreboard board,ChatColor color) {
		Team t;
		if (board.getTeam(p.getName()) == null) {
		  t = createTeam(board,p.getName(),color);
		}else {
		  t = board.getTeam(p.getName());
		}
		t.addEntry(entity.getUniqueId().toString());
		
	}
	
	public void removeEntityToPlayerTeam(Entity entity, Player p,Scoreboard board) {
		Team t;
		if (board.getTeam(p.getName()) == null) {
		  return;
		}
		t = board.getTeam(p.getName());
		
		try {
			t.removeEntry(entity.getUniqueId().toString());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void removePlayerTeam(Scoreboard board, Player p) {
		if (board.getTeam(p.getName()) == null) {
			return;
		}else {
			board.getTeam(p.getName()).unregister();
		}
	}
	
	
	

}
