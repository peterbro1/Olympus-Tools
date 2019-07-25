package me.gmx.olympus.config;

import org.bukkit.ChatColor;

import me.gmx.olympus.core.BConfig;

public enum Lang {

		PREFIX("&4[&5Olympus Tools&4]&r "),
		MSG_ERROR("Error occured, please contact server owner."),
	  	MSG_NOACCESS("You don't have access to this command."),
	  	MSG_PLAYERONLY("This command can be used only in the game."),
	  	MSG_TOOLOWRANK("You have to be at least %rank% to do this."),
	  	MSG_USAGE_FIGHT("Invalid command usage. Please check /gang help"),
	  	MSG_USAGE_OLYMPUS("Invalid command usage. Please check /olympus help"),
	  	MSG_USAGE_OLYMPUSADMIN("Invalid command usage. Correct usage: /oadmin <reload>"),
	  	MSG_USAGE_SUBCOMMAND("Invalid command usage. Correct usage: %usage%"),
	  	MSG_MODULE_DISABLED("This module has been disabled by the server owner."),
	  	MSG_OLYMPUS_HELP("&7--[ &bOlympus Tools help &7]--\n &7- &b/olympus menu &7- Opens menu.\n"),
	  	MSG_CONFIGRELOADED("Config reloaded."),
	  	MSG_ITEM_ONCOOLDOWN("You can't use %item% for %time% seconds"),
	  	MSG_INVALIDPLAYER("Invalid player name specified."),
	  	LANG_CONSOLE("The console cannot perform this action."),
	  	MSG_ATTEMPTCRAFT("You cannot craft this!");
	
	
	private String defaultValue;
	private static BConfig config;
	
	Lang(String str){
		defaultValue = str;
	}
	
	
	public String getPath() { return name().replace("_", "."); }
	
	public String getDefaultValue() { return this.defaultValue; }
	
	public String toString() { return fixColors(config.getConfig().getString(getPath())); }
	
	public static void setConfig(BConfig paramBConfig) {
		      config = paramBConfig;
		      load();
		    }
	
	public String toMsg() {
		     boolean bool = true;
		     if (bool) {
		       return fixColors(config.getConfig().getString(PREFIX.getPath()) + config.getConfig()
		           .getString(getPath()));
		     }
		     return fixColors(config.getConfig().getString(getPath()));
		   }
	
	private static void load() {
		     for (Lang lang : values()) {
		       if (config.getConfig().getString(lang.getPath()) == null) {
		         config.getConfig().set(lang.getPath(), lang.getDefaultValue());
		       }
		     } 
		     config.save();
		   }
	
	
	private String fixColors(String paramString) {
		     if (paramString == null)
		    	 return ""; 
		     return ChatColor.translateAlternateColorCodes('&', paramString);
		   }
}
