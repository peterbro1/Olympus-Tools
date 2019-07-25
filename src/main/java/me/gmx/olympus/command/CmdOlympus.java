package me.gmx.olympus.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.gmx.olympus.OlympusTools;
import me.gmx.olympus.command.olympus.CmdOlympusHelp;
import me.gmx.olympus.command.olympus.CmdOlympusMenu;
import me.gmx.olympus.config.Lang;
import me.gmx.olympus.core.BCommand;
import me.gmx.olympus.core.BSubCommand;

public class CmdOlympus extends BCommand implements CommandExecutor {

	public CmdOlympus(OlympusTools instance) {
		super(instance);
		this.subcommands.add(new CmdOlympusMenu());
		this.subcommands.add(new CmdOlympusHelp());
		//this.subcommands.add(new sub)
		
		
		
	}
	
	
	

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if (arg3.length < 1) {
			arg0.sendMessage(Lang.MSG_USAGE_OLYMPUS.toMsg());
			return true;
		}
		
		
		
		for (BSubCommand cmd : this.subcommands) {
			if (cmd.aliases.contains(arg3[0])) {
				cmd.execute(arg0,arg3);
				return true;
			}
		}
		arg0.sendMessage(Lang.MSG_USAGE_OLYMPUS.toMsg());
		
		return true;
	}

}
