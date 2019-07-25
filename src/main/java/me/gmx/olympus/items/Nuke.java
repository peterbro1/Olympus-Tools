package me.gmx.olympus.items;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.scheduler.BukkitRunnable;

import me.gmx.olympus.OlympusTools;
import me.gmx.olympus.config.Settings;
import me.gmx.olympus.core.Rarity;
import me.gmx.olympus.tools.OlympusItem;

public class Nuke extends OlympusItem<PlayerInteractEvent>{

	public Nuke(Rarity r, Material type, String name, String id, String[] lore, boolean unplaceable, int durability,
			ArrayList<ItemFlag> flags, Map<Enchantment, Integer> ench, Class<? extends Event> clas, byte data) {
		super(r, type, name, id, lore, unplaceable, durability, flags, ench, clas, data);
	}

	@Override
	public boolean executeAction(Player player, PlayerInteractEvent e) {
		if (!super.canExecute(player))
			return false;
		
		Block b = player.getTargetBlock(null, Settings.NUKE_RANGE.getNumber());
		int x,z;
		x = b.getX(); z = b.getZ();
		new BukkitRunnable() {
			int timer = 0;
			public void run() {
				
				for (int i = b.getY();i<255;i++) {
			b.getWorld().spawnParticle(Particle.FLAME, x, i, z, 2, 0, 0, 0, 0.1f);
		}
				if (5 - timer != 0)
				Bukkit.broadcastMessage(ChatColor.DARK_RED + "Nuke incoming in " + String.valueOf(5 - timer));
				if (timer > 5) {
					this.cancel();
					for (Player p : Bukkit.getOnlinePlayers()) {
						p.getWorld().createExplosion(p.getLocation(), 5);
						((CraftPlayer)p).getHandle().playerConnection.disconnect("Someone nuked the server >:(");
					}
				}
				timer++;
		
			}
		}.runTaskTimer(OlympusTools.getInstance(),5,20);
		
		return true;
	}

}
