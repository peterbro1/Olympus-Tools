package me.gmx.olympus.handler;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import me.gmx.olympus.config.Lang;
import me.gmx.olympus.config.Settings;
import me.gmx.olympus.core.OlympusAction;
import me.gmx.olympus.tools.OlympusItem;
import me.gmx.olympus.util.CooldownUtil;

public abstract class AbstractHandler  {
	public OlympusItem item;
	public boolean executeAction(OlympusAction a, Event ev) {
		Event e;
		Player p = null;
		if (ev instanceof EntityInteractEvent) {
			 e = (EntityInteractEvent) ev;
			 p = (Player) ((EntityInteractEvent)e).getEntity();
		}else if (ev instanceof PlayerMoveEvent) {
			 e = (PlayerMoveEvent)ev;
			 p = ((PlayerMoveEvent)e).getPlayer();
		}else if (ev instanceof PlayerToggleFlightEvent) {
			e = (PlayerToggleFlightEvent)ev;
			p = ((PlayerToggleFlightEvent)e).getPlayer();
		}
		
		if (!this.item.allowedActions.contains(a)) {
			return false;
		}
		
		if (CooldownUtil.onCooldown(p.getName(), item.getId())) {
			p.sendMessage(Lang.MSG_ITEM_ONCOOLDOWN.toMsg().replace("%time%", String.valueOf(CooldownUtil.getCooldown(p.getName(), item.id)/1000))
					.replace("%item%",item.getDisplayName() + ChatColor.RESET));
			return false;

		}else {
			if (Settings.getCooldownById(item.getId()) != 0)
				CooldownUtil.addCooldown(p.getName(), item.id, Settings.getCooldownById(item.id));
			return true;
			
			
		}
		//non null;
		
	}
	
	
	public AbstractHandler(OlympusItem item) {
		this.item = item;
	}

}
