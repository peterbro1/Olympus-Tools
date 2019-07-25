package me.gmx.olympus.setup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.gmx.olympus.OlympusTools;
import me.gmx.olympus.entities.EntityHandler;
import me.gmx.olympus.inventory.IconMenu;
import me.gmx.olympus.inventory.IconMenu.OptionClickEvent;
import me.gmx.olympus.lists.OlympusItems;
import me.gmx.olympus.tools.OlympusItem;
import me.gmx.olympus.util.CooldownUtil;
import me.gmx.olympus.util.InvenUtil;

public class MiscSetup {

	public static void init() {
		CooldownUtil.cd = new HashMap<String,Long>();
		EntityHandler.init();
		OlympusTools.menu = new IconMenu("§dOlympus Tools", 45, new IconMenu.OptionClickEventHandler() {

			@Override
			public void onOptionClick(OptionClickEvent event) {
				if (event.getName().equals("§cExit")) {
					event.setWillClose(true);
				}else if (event.getName().equals("§cNext Page")) {
					event.setWillClose(true);
					
					new BukkitRunnable() {
						public void run() {
							OlympusTools.menu2.open(event.getPlayer());
						}
						
					}.runTaskLater(OlympusTools.getInstance(),2L);
				}else if (OlympusItems.getByName(event.getName()) != null) {
					event.setWillClose(true);
					InvenUtil.giveItem(event.getPlayer(), OlympusItems.getByName(event.getName()));
				}
				
				
				
				
				
				
				
			}}, OlympusTools.getInstance()).setOption(0, new ItemStack(Material.EYE_OF_ENDER), "§cExit", "")
				.setOption(8, new ItemStack(Material.EYE_OF_ENDER), "§cNext Page", "");
		
		
		
		OlympusTools.menu2 = new IconMenu("§dOlympus Tools page 2", 45, new IconMenu.OptionClickEventHandler() {

			@Override
			public void onOptionClick(OptionClickEvent event) {
				if (event.getName().equals("§cBack")) {
					event.setWillClose(true);
					
					
					new BukkitRunnable() {
						public void run() {
							OlympusTools.menu.open(event.getPlayer());
						}
						
					}.runTaskLater(OlympusTools.getInstance(),2L);
					
				}
				
				
				
				
				
				
				
			}}, OlympusTools.getInstance()).setOption(0, new ItemStack(Material.EYE_OF_ENDER), "§cBack", "");
		
		initMenu();
	}
	
	public static void initMenu() {
		try {
			Method m = IconMenu.class.getMethod("setOption",java.lang.Integer.class, me.gmx.olympus.tools.OlympusItem.class, java.lang.String.class, java.util.List.class);
			
			int integer = 1;
			for (OlympusItem o : OlympusItems.list) {
				try {
					if (integer == 8) {
						integer = 9;
					}else if (integer > 54) {
						initMenu2();
						return;
					}
					//Bukkit.broadcastMessage(o.getDisplayName() + "   " + OlympusTools.menu.getEmptySlots());

					//if (OlympusTools.menu.getEmptySlots() > 0)
					m.invoke(OlympusTools.menu, integer,o,o.getDisplayName(),o.getItemMeta().getLore());
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				integer++;
			}
			
			
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public static void initMenu2() {
		try {
			Method m = IconMenu.class.getMethod("setOption",java.lang.Integer.class, me.gmx.olympus.tools.OlympusItem.class, java.lang.String.class, java.util.List.class);
			int integer = 1;
			for (OlympusItem o : OlympusItems.list) {
				try {
					/*if (integer == 8) {
						integer = 9;
					}else if (integer > 54) {
						//initMenu2();
						return;
					}*/
					if (OlympusTools.menu2.getEmptySlots() > 0)
					m.invoke(OlympusTools.menu2, integer,o,o.getDisplayName(),o.getItemMeta().getLore());
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				integer++;
			}
			
			
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	
}
