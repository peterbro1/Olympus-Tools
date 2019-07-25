package me.gmx.olympus.util;

import java.util.HashMap;

public class CooldownUtil {
	public static HashMap<String, Long> cd;
	
	
	public static void addCooldown(String player,String item_id, int time) {
		if (!cd.containsKey(player + "-" + item_id)) {
			cd.put(player + "-" + item_id, (System.currentTimeMillis() + time*1000));
		}else { cd.replace(player + "-" + item_id, (System.currentTimeMillis() + time*1000));}
	}
	
	
	public static long getCooldown(String player, String item_id) {
		if (!cd.containsKey(player + "-" + item_id)) {
			return 0;
		}else {
			long remaining = cd.get(player + "-" + item_id) - System.currentTimeMillis();
			if (remaining <= 0 ) {
				return 0;
			}
			return remaining;
		}
	}
	
	public static boolean onCooldown(String player, String item_id) {
		if (!cd.containsKey(player + "-" + item_id)) {
			return false;
		}else {
			
			if (cd.get(player + "-" + item_id) < System.currentTimeMillis()) {
				cd.remove(player + "-" + item_id);
				return false;
			}else {
				return true;
			}
		}
	}
	

}
