package me.gmx.olympus.core;

import org.bukkit.ChatColor;

public enum Rarity {

	MORTAL(1,ChatColor.DARK_GREEN,"Mortal"),
	HERO(2,ChatColor.DARK_AQUA,"Hero"),
	APOSTLE(3,ChatColor.AQUA,"Apostle"),
	LEGEND(4,ChatColor.GOLD,"Legend"),
	DEITY(5,ChatColor.DARK_PURPLE,"Deity"),
	GOD(6,ChatColor.DARK_RED,"God");
	
	
	
	private String s;
	private ChatColor color;
	private int level;
	 Rarity(int i, ChatColor color,String s) {
		this.s = s;
		this.level = i;
		this.color = color;
	}
	 
	 
	 public String getTitle() {
		 return new String(color + s);
	 }
	 
	 public String getName() {
		 return s;
	 }
	 public int getLevel() {
		 return level;
	 }
	 public ChatColor getColor() {
		 return color;
	 }
	
}
