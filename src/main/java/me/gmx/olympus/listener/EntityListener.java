package me.gmx.olympus.listener;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;

import me.gmx.olympus.OlympusTools;
import me.gmx.olympus.config.Lang;
import me.gmx.olympus.util.ItemMetadata;

public class EntityListener implements Listener{
	
	@EventHandler
	public void targetEntity(EntityTargetLivingEntityEvent e) {
		if (!e.getEntity().hasMetadata("owner")) {
			return;
		}
		try {
		if (e.getEntity().getMetadata("owner").get(0).asString().equals(e.getTarget().getName())) {
			e.setCancelled(true);
		}
		}catch(NullPointerException ex) {
			return;
		}
	}
	
	@EventHandler
	public void join(PlayerJoinEvent e) {
		e.getPlayer().setAllowFlight(true);
	}
	
	//@EventHandler
	public void entityMove(PlayerMoveEvent e) {
		if (OlympusTools.stayers.contains(e.getPlayer().getName())) {
			e.setCancelled(true);
		}
	}
	
	//prevent olympus items from being repaired
	@EventHandler(priority = EventPriority.LOW)
	public void dd(InventoryClickEvent e) {
		if (e.getInventory() instanceof AnvilInventory) {
			Inventory inv = e.getInventory();
			InventoryView v = e.getView();
			int rawSlot = e.getRawSlot();
			if (rawSlot == v.convertSlot(rawSlot)) {
				if (rawSlot == 2) {//result
					if (e.getCurrentItem() != null) {
						if (ItemMetadata.hasNBTDataString(e.getCurrentItem(), "ID")) {
							ItemStack[] it = inv.getContents();
							if (it[0] == null || it[1] == null) {return;}
							ItemMeta meta = e.getCurrentItem().getItemMeta();
							if(meta instanceof Repairable){
								Repairable repairable = (Repairable)meta;
								int repairCost = repairable.getRepairCost();
								 
								// can the player afford to repair the item
								if(((Player)e.getWhoClicked()).getLevel() >= repairCost){
									e.getWhoClicked().sendMessage(Lang.PREFIX + " Cheeky bastard.");
									e.setCancelled(true);
								}
								}
						}
					}
					
				}
			}
		}

	}
	@EventHandler
	public void c(CraftItemEvent e) {
		ItemStack[] a = e.getInventory().getContents();
		for (ItemStack aa : a) {
			if (ItemMetadata.hasNBTDataString(aa, "ID")) {
				e.setCancelled(true);
				e.getWhoClicked().sendMessage(Lang.MSG_ATTEMPTCRAFT.toMsg());
			}
		}
	}
	private HashMap<String, Long> clickPlayers = new HashMap<String, Long>();



}
