package me.gmx.olympus.command.olympus;

import org.bukkit.Effect;
import org.bukkit.entity.Player;

import me.gmx.olympus.OlympusTools;
import me.gmx.olympus.core.BSubCommand;

public class CmdOlympusMenu extends BSubCommand{
	
	public CmdOlympusMenu() {
		this.aliases.add("menu");
		this.aliases.add("toolmenu");
		this.correctUsage = "/olympustools menu";
		this.permission = "olympustools.menu";
		
		this.senderMustBePlayer = true;
	}
	
	
	
	
	@Override
	public void execute() {
		OlympusTools.menu.open((Player)sender);

	}

}
