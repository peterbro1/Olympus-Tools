package me.gmx.olympus.config;

import me.gmx.olympus.core.BConfig;

public enum Settings {

		MJOLNIR_COOLDOWN("11"),
		MJOLNIR_RANGE("20"),
		CRAFTER_COOLDOWN("5"),
		K9_COOLDOWN("60"),
		K9_DURATION("10"),
		K9_AMOUNT("5"),
		DISCO_COOLDOWN("35"),
		DISCO_DURATION("5"),
		NECRONOMICON_COOLDOWN("80"),
		NECRONOMICON_DURATION("15"),
		NECRONOMICON_RANGE("32"),
		CLOUDBOOTS_COOLDOWN("5"),
		CLOUDBOOTS_MIDAIR("false"),
		PORCUPINE_COOLDOWN("60"),
		STORMBREAKER_COOLDOWN("55"),
		STORMBREAKER_RANGE("30"),
		STORMBREAKER_DAMAGE("6"),
		STORMBREAKER_RADIUS("5"),
		FLAMETHROWER_COOLDOWN("75"),
		DNA_COOLDOWN("85"),
		NUKE_COOLDOWN("99"),
		NUKE_RANGE("20");
	
	
	private String defaultValue;
	private static BConfig config;
	
	Settings(String str){
		defaultValue = str;
	}
	
	
	public String getPath() { return name().replace("_", "."); }
	
	public String getDefaultValue() { return this.defaultValue; }
	
	
	public static void setConfig(BConfig paramBConfig) {
		      config = paramBConfig;
		      load();
		    }
	
	public int getNumber() {
		return Integer.parseInt(config.getConfig().getString(getPath()));
	}
	
	public boolean getBoolean() throws Exception{
		
		try {
			return Boolean.valueOf(config.getConfig().getString(getPath()));
		}catch(NullPointerException e) {
			throw new Exception("Value could not be converted to a boolean");
		}
		
	}
	
	private static void load() {
		     for (Settings lang : values()) {
		       if (config.getConfig().getString(lang.getPath()) == null) {
		         config.getConfig().set(lang.getPath(), lang.getDefaultValue());
		       }
		     } 
		     config.save();
		   }
	
	public static int getCooldownById(String s) throws NullPointerException{
		for (Settings set : values()) {
			if (set.name().equalsIgnoreCase(s + "_COOLDOWN")) {
				return set.getNumber();
			}
		}
		throw new NullPointerException(s + " could not be found");
		
	}
	
	

}
