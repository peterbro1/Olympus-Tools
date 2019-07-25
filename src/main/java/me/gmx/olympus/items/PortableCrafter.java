package me.gmx.olympus.items;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;

import me.gmx.olympus.core.Rarity;
import me.gmx.olympus.tools.OlympusItem;

public class PortableCrafter extends OlympusItem<PlayerInteractEvent>{

	public PortableCrafter(Rarity r, Material type, String name, String id, String[] lore, boolean unplaceable, int durability,
			ArrayList<ItemFlag> flags, Map<Enchantment, Integer> ench, Class<? extends Event> clas,byte data) {
		super(r, type, name, id, lore, unplaceable, durability, flags, ench, clas,data);
	}

	@Override
	public boolean executeAction(Player p, PlayerInteractEvent e) {
		if(!super.canExecute(p))
			return false;
		
		p.playSound(p.getLocation(), Sound.BLOCK_WOOD_STEP, 0, 2);
		p.openWorkbench(null, true);
		return true;
		
	}

}
