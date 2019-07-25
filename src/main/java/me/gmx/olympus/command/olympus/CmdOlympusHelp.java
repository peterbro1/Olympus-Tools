package me.gmx.olympus.command.olympus;

import org.bukkit.Material;

import me.gmx.olympus.config.Lang;
import me.gmx.olympus.core.BSubCommand;

public class CmdOlympusHelp extends BSubCommand{

	
	public CmdOlympusHelp() {
		this.aliases.add("help");
		this.correctUsage = "/Olympus help";
		this.permission = "olympustools.help";
	}
	
	@Override
	public void execute() {
		msg(Lang.MSG_OLYMPUS_HELP.toMsg());	
	}

}
