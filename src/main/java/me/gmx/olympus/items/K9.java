package me.gmx.olympus.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.gmx.olympus.OlympusTools;
import me.gmx.olympus.config.Settings;
import me.gmx.olympus.core.Rarity;
import me.gmx.olympus.entities.CustomWolf;
import me.gmx.olympus.tools.OlympusItem;

public class K9 extends OlympusItem<PlayerInteractEvent>{
public List<CustomWolf> list;
	public K9(Rarity r, Material type, String name, String id, String[] lore, boolean unplaceable, int durability,
			ArrayList flags, Map ench,Class<? extends Event> clas,byte data) {
		super(r, type, name, id, lore, unplaceable, durability, flags, ench,clas,data);
		list = new ArrayList<CustomWolf>();
	}

	public K9(Rarity r, Material type, String name, String id, String[] lore,Class<? extends Event> clas,byte data) {
		this(r, type,name,id,lore,false,0,null,null,clas,data);
	}
	
	public boolean executeAction(Player p, PlayerInteractEvent e) {
		if(!super.canExecute(p))
			return false;
		
		Random r = new Random();
		for (int i = 0;i < Settings.K9_AMOUNT.getNumber();i++) {
		CustomWolf cw = new CustomWolf(((CraftWorld)p.getWorld()).getHandle(),p);
		cw.spawnEntity(p.getLocation().add(r.nextInt(4)-2, 0,r.nextInt(3)-1));
		list.add(cw);
		new BukkitRunnable() {
			public void run() {
				cw.delete();
				list.remove(cw);
			}
		}.runTaskLater(OlympusTools.getInstance(),Settings.K9_DURATION.getNumber()*20);
		}
		return true;
	}
	
}
