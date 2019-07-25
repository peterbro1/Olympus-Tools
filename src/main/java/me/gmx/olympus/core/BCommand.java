package me.gmx.olympus.core;

import java.util.ArrayList;

import me.gmx.olympus.OlympusTools;

public class BCommand {
	
	public OlympusTools main;
	public ArrayList<BSubCommand> subcommands;
	
	public BCommand(OlympusTools ins) {
		this.main = ins;
		subcommands = new ArrayList<BSubCommand>();
	}

}
