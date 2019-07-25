package me.gmx.olympus.items;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;

import me.gmx.olympus.config.Settings;
import me.gmx.olympus.core.Rarity;
import me.gmx.olympus.tools.OlympusItem;
import me.gmx.olympus.util.InvenUtil;

public class ThorAxe extends OlympusItem<PlayerInteractEvent>{

	public ThorAxe(Rarity r, Material type, String name, String id, String[] lore, boolean unplaceable, int durability,
			ArrayList flags, Map ench,Class<? extends Event> clas,byte data) {
		super(r, type, name, id, lore, unplaceable, durability, flags, ench,clas,data);
	}
	


	@Override
	public boolean executeAction(Player player, PlayerInteractEvent e) {
		if(!super.canExecute(player))
			return false;
		Block b = player.getTargetBlock(null, Settings.MJOLNIR_RANGE.getNumber());
		b.getWorld().strikeLightning(b.getLocation().add(0,1,0));
		InvenUtil.damageItemInHand(player,12);
		return true;
	}
	

}
