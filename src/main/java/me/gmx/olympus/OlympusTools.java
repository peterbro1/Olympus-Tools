package me.gmx.olympus;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.gmx.olympus.command.CmdOlympus;
import me.gmx.olympus.command.CmdOlympusAdmin;
import me.gmx.olympus.config.Lang;
import me.gmx.olympus.config.Settings;
import me.gmx.olympus.core.BConfig;
import me.gmx.olympus.entities.CustomWolf;
import me.gmx.olympus.handler.InteractHandler;
import me.gmx.olympus.handler.PacketHandler;
import me.gmx.olympus.inventory.IconMenu;
import me.gmx.olympus.items.K9;
import me.gmx.olympus.listener.EntityListener;
import me.gmx.olympus.listener.ItemPickupListener;
import me.gmx.olympus.lists.OlympusItems;
import me.gmx.olympus.setup.MiscSetup;
import me.gmx.olympus.util.ScoreboardUtil;

public class OlympusTools extends JavaPlugin{
	
	private static OlympusTools instance;
	public static IconMenu menu;
	public static IconMenu menu2;
	
	Logger logger;
	
	public BConfig mainConfig;
	public BConfig langConfig;
	public OlympusItems items;
	public static ArrayList<String> stayers;
	public ScoreboardUtil scoreboardutil;

	public void onEnable() {
		instance = this;
		this.logger = getLogger();
		PacketHandler.initPacketLib();
	    logger.log(Level.INFO, String.format("[%s] Successfully enabled version %s!", new Object[] { getDescription().getName(), getDescription().getVersion() }));
	    registerCommands();
	    registerListeners();
	    this.mainConfig = new BConfig(this,"config.yml");
		
		this.langConfig = new BConfig(this,"Lang.yml");
		Lang.setConfig(this.langConfig);
		Settings.setConfig(this.mainConfig);
		initItems();
		stayers = new ArrayList<String>();
		scoreboardutil = new ScoreboardUtil();

	}
	
	
	
	
	
	
	
	
	 private void addClassPath(final URL url) throws IOException {
	        final URLClassLoader sysloader = (URLClassLoader) ClassLoader
	                .getSystemClassLoader();
	        final Class<URLClassLoader> sysclass = URLClassLoader.class;
	        try {
	            final Method method = sysclass.getDeclaredMethod("addURL",
	                    new Class[] { URL.class });
	            method.setAccessible(true);
	            method.invoke(sysloader, new Object[] { url });
	        } catch (final Throwable t) {
	            t.printStackTrace();
	            throw new IOException("Error adding " + url
	                    + " to system classloader");
	        }
	    }
	
	
	
	
	public void initItems() {
		items = new OlympusItems(instance);
		MiscSetup.init();
		
	}
	
	public void onDisable() {
		Bukkit.getScheduler().cancelTasks(this);
		for (CustomWolf o : ((K9)OlympusItems.K9_UNIT).list) {
			o.delete();
		}
		
	}
	
	public static OlympusTools getInstance() {
		return instance;
	}
	
	
	 public void reload() {
		    this.mainConfig = new BConfig(this, "config.yml");
		    
		    
		    this.langConfig = new BConfig(this,"Lang.yml");
		    Settings.setConfig(this.mainConfig);
		    Lang.setConfig(this.langConfig);
		  }
	 
	 public void registerListeners() {
		 getServer().getPluginManager().registerEvents(new ItemListener(), getInstance());
		 getServer().getPluginManager().registerEvents(new ItemPickupListener(), getInstance());
		 getServer().getPluginManager().registerEvents(new EntityListener(), getInstance());
		 getServer().getPluginManager().registerEvents(new InteractHandler(getInstance()), getInstance());



	 }
	 
	 public void registerCommands() {
		getCommand("olympus").setExecutor(new CmdOlympus(getInstance()));
		getCommand("olympusadmin").setExecutor(new CmdOlympusAdmin(getInstance()));

	 }
	
	
}
