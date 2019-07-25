package me.gmx.olympus.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

public class ItemPickupListener implements Listener {

    public ItemPickupListener() {
    }

    
    @EventHandler
    public void onMinecartPickup(InventoryPickupItemEvent e) {
        if (e.getItem().hasMetadata("no_pickup")) e.setCancelled(true);
        
    }
    
    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if (e.getItem().hasMetadata("no_pickup")) e.setCancelled(true);
        
    }


}