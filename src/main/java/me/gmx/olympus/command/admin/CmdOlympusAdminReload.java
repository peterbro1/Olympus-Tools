package me.gmx.olympus.command.admin;

import me.gmx.olympus.config.Lang;
import me.gmx.olympus.core.BSubCommand;

public class CmdOlympusAdminReload extends BSubCommand{

	public CmdOlympusAdminReload() {
		this.aliases.add("reload");
		this.permission = "olympustools.admin.reload";
		this.correctUsage = "/olympusadmin reload";
		this.senderMustBePlayer = false;
	}
	
	
	@Override
	public void execute() {
		this.main.reload();
		msg(Lang.MSG_CONFIGRELOADED.toMsg());
		
	}

}
