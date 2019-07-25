package me.gmx.olympus.util;

import java.util.List;

import org.bukkit.inventory.ItemStack;

public class ItemUtil {
	public enum ScanType
	  {
	    FULL,
	    SKIP_DURABILITY;
	  }	
	  public static boolean isItemSimiliar(ItemStack item, ItemStack item2, boolean lore, ScanType data) {
		    if (item == null) return (item2 == null); 
		    if (item2 == null) return false;
		    
		    if (item.getType() == item2.getType() && item.getAmount() >= item2.getAmount()) {
		          if (item.getData().getData() != item2.getData().getData()
		        		  &&  ((item2.getDurability() != item.getData().getData() && data.equals(ScanType.FULL)) || (item2.getData().getData() != item.getDurability() && data.equals(ScanType.FULL)) )) 
		        	  return false;
		        
		        
		        else if (data.equals(ScanType.FULL) && item.getDurability() != item2.getDurability()) {
		          return false;
		        } 
		      
		      
		      if (item.hasItemMeta() && item2.hasItemMeta()) {
			    	  if (!item.getItemFlags().equals(item2.getItemFlags())) 
			    		  return false;
		        if (item.getItemMeta().hasDisplayName() && item2.getItemMeta().hasDisplayName()) {
		          if (item.getItemMeta().getDisplayName().equals(item2.getItemMeta().getDisplayName())) {
		            if (lore) {
		              if (item.getItemMeta().hasLore() && item2.getItemMeta().hasLore()) {
		                return equalsLore(item.getItemMeta().getLore(), item2.getItemMeta().getLore());
		              }
		              return (!item.getItemMeta().hasLore() && !item2.getItemMeta().hasLore());
		            } 
		            return true;
		          } 
		          return false;
		        } 
		        if (!item.getItemMeta().hasDisplayName() && !item2.getItemMeta().hasDisplayName()) {
		          if (lore) {
		            if (item.getItemMeta().hasLore() && item2.getItemMeta().hasLore()) {
		              return equalsLore(item.getItemMeta().getLore(), item2.getItemMeta().getLore());
		            }
		            return (!item.getItemMeta().hasLore() && !item2.getItemMeta().hasLore());
		          } 
		          return true;
		        } 
		        return false;
		      } 
		      return (!item.hasItemMeta() && !item2.hasItemMeta());
		    } 
		    return false;
		  }
	  private static boolean equalsLore(List<String> lore, List<String> lore2) {
		    String string1 = "", string2 = "";
		    for (String string : lore) {
		      if (!string.startsWith("&e&e&7")) string1 = string1 + "-NEW LINE-" + string; 
		    } 
		    for (String string : lore2) {
		      if (!string.startsWith("&e&e&7")) string2 = string2 + "-NEW LINE-" + string; 
		    } 
		    return string1.equals(string2);
		  }
	  
	
}
