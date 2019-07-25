package me.gmx.olympus.handler;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;

import me.gmx.olympus.OlympusTools;
import me.gmx.olympus.core.OlympusAction;
import me.gmx.olympus.lists.OlympusItems;
import me.gmx.olympus.tools.OlympusItem;
import me.gmx.olympus.util.ItemMetadata;

public class InteractHandler implements Listener{

	private OlympusTools ins;
	public Action action;
	public Player player;
	public ItemStack item;
	public OlympusItem<?> olympusitem;
	public String item_id;
	public String target_id;
	
	public InteractHandler(OlympusTools ins) {
		this.ins = ins;
		//ins.getServer().getPluginManager().registerEvents(this, ins);
	}
	
//test	
	@EventHandler
	public void interactEvent(PlayerInteractEvent e) {
		OlympusAction a = null;
	
		Action ac = e.getAction();
		if (ac == Action.RIGHT_CLICK_AIR)
			a = OlympusAction.RIGHT_CLICK_AIR;
		else if (ac == Action.RIGHT_CLICK_BLOCK)
			a = OlympusAction.RIGHT_CLICK_BLOCK;
		else if (ac == Action.LEFT_CLICK_AIR)
			a = OlympusAction.LEFT_CLICK_AIR;
		else if (ac == Action.LEFT_CLICK_BLOCK)
			a = OlympusAction.LEFT_CLICK_BLOCK;
		
		
		
		if (a == null) {return;}
		this.player = e.getPlayer();
		if (e.getItem() != null) {
			this.item = e.getItem();
		}else {
			return;
		}
		if (!ItemMetadata.hasNBTDataString(e.getItem(), "ID")) {
			return;
		}
		this.item_id = ItemMetadata.getNBTDataString(CraftItemStack.asNMSCopy(item), "ID");
		try {

			//if >>>vvv   then add cooldown. in case item cant active, wont be put cd
			if (OlympusItems.getById(this.item_id).allowedActions == null) {
				return;
			}
				if (OlympusItems.getById(this.item_id).allowedActions.contains(a)){
					OlympusItems.getById(this.item_id).executeAction(this.player,e);
				}
		}catch (NullPointerException ex) {
			ex.printStackTrace();
		}

	}
	
	@EventHandler
	public void entityMoveEvent(PlayerMoveEvent e) {
		ItemStack[] armor;
		armor = e.getPlayer().getInventory().getArmorContents();
		if (armor.length > 0) {
			for (ItemStack i : armor) {
				if (ItemMetadata.hasNBTDataString(i, "ID")) {
					
				}
			}
			
			
		}
	}
    
    @EventHandler
    public void setFlyOnJump(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
       
        if(event.isFlying() && event.getPlayer().getGameMode() != GameMode.CREATIVE) {
        	ItemStack[] armor;
        	
    		armor = event.getPlayer().getInventory().getArmorContents();
    		if (armor.length > 0) {
    			List<OlympusItem<?>> l = OlympusItems.getByAction(OlympusAction.DOUBLE_JUMP);
    			if (l.isEmpty()) {
    				return;
    			}
    			for (ItemStack i : armor) {
    				if (ItemMetadata.hasNBTDataString(i,"ID")) {
    					for (OlympusItem oi : (List<OlympusItem<?>>)l) {
    						if (oi.getId().equals(ItemMetadata.getNBTDataString(CraftItemStack.asNMSCopy(i),"ID"))) {
    							oi.executeAction(player, event);
    								event.setCancelled(true);
    								player.setFlying(false);
    							
    							    							
    							
    						}
    					}
    				}else {
    				}
    				
    			}
    			}
            //player.setFlying(false);
           
           
           // event.setCancelled(true);
        }
    }
    
    @SuppressWarnings("unchecked")
	@EventHandler
    public void consume(PlayerItemConsumeEvent e) {
    	if (e.isCancelled())
			return;
		
		
		if (ItemMetadata.hasNBTDataString(e.getItem(), "ID")) {
			
			try {
				OlympusItem o = OlympusItems.getById(ItemMetadata.getNBTDataString(CraftItemStack.asNMSCopy(e.getItem()), "ID"));
				if (!o.allowedActions.contains(OlympusAction.ITEM_CONSUME)) {
					e.setCancelled(true);
					return;
				}else {
					
					if (!o.executeAction(e.getPlayer(), e)) {
						e.setCancelled(true);
					}
				}
				
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
				
			
		}
		
		
		
		
		
		
		
    }
	
	
	
	
	
	
	
	

}
