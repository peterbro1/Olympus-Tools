package me.gmx.olympus.items;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.gmx.olympus.OlympusTools;
import me.gmx.olympus.core.Rarity;
import me.gmx.olympus.tools.OlympusItem;
import me.gmx.olympus.util.ItemMetadata;

public class IllusionSword extends OlympusItem<PlayerInteractEvent>{

	private boolean switching;
	private BukkitTask task;
	public IllusionSword(Rarity r, Material type, String name, String id, String[] lore, boolean unplaceable,
			int durability, ArrayList flags, Map ench, Class clas, byte data) {
		super(r, type, name, id, lore, unplaceable, durability, flags, ench, clas, data);
		switching = false;
	}

	@Override
	public boolean executeAction(Player player, PlayerInteractEvent e) {
		if (!switching) { //if not switching already
			this.task = initTask(player); //create task
			this.switching = true;
		}else {
			//if it was switching
			this.task.cancel();
			this.switching = false;
		}
		return false;
	}
	
	public BukkitTask initTask(Player p) {
		ItemStack u,d,t,c;
		u = asMirror(Material.GOLD_SWORD);
		d = asMirror(Material.DIAMOND_SWORD);
		t = asMirror(Material.IRON_SWORD);
		c = asMirror(Material.WOOD_SWORD);
		BukkitTask run =  new BukkitRunnable() {
			int i = 0;
			public void run() {
				
				try {
				if (!switching || !ItemMetadata.getNBTDataString(CraftItemStack.asNMSCopy(p.getInventory().getItemInMainHand()), "ID").equals(id)) {
					this.cancel();
					switching = false;
					return;
				}
				Bukkit.broadcastMessage(String.valueOf(i));
				if (i == 0) {
					i++;
					p.setItemInHand(u);
				}else if (i == 1) {
					i++;
					p.setItemInHand(d);
				}else if (i == 2) {
					i++;
					p.setItemInHand(t);
				}else if (i == 3) {
					i = 0;
					p.setItemInHand(c);
				}
				}catch(NullPointerException e) {
					this.cancel();
					switching = false;
				}
			}
		}.runTaskTimer(OlympusTools.getInstance(), 20, 10);
		return run;
		
		
	}
	
	@Override
	public void destroy() {
		this.task.cancel();
	}
	

	
	
	

}
