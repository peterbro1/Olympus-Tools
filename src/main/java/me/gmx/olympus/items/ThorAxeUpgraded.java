package me.gmx.olympus.items;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.gmx.olympus.OlympusTools;
import me.gmx.olympus.config.Settings;
import me.gmx.olympus.core.Rarity;
import me.gmx.olympus.tools.OlympusItem;
import me.gmx.olympus.util.LocationUtil;

public class ThorAxeUpgraded extends OlympusItem<PlayerInteractEvent>{

	public ThorAxeUpgraded(Rarity r, Material type, String name, String id, String[] lore, boolean unplaceable, int durability,
			ArrayList<ItemFlag> flags, Map<Enchantment, Integer> ench, Class<? extends Event> clas,byte data) {
		super(r, type, name, id, lore, unplaceable, durability, flags, ench, clas,data);
	}

	@Override
	public boolean executeAction(Player player, PlayerInteractEvent e) {
		if (!super.canExecute(player))
			return false;
		
		Block b = player.getTargetBlock(null, Settings.STORMBREAKER_RANGE.getNumber());
		//final ArrayDeque<Location> l = new ArrayDeque<Location>();
		//final Location location = b.getLocation().subtract(0,1,0);
		//final Random r = new Random();
		/*new BukkitRunnable() {
			int iterations = 1;
			ArrayList<Location> loc = new ArrayList<Location>();
			public void run() {
				loc.add(b.getLocation().add(r.nextGaussian()*(iterations/20),0,r.nextGaussian()*(iterations/20)));
				loc.add(b.getLocation().add(iterations,0,0));
				
				loc.add(b.getLocation().add(iterations,0,iterations));
				loc.add(b.getLocation().add(iterations,0,-iterations));
				
				loc.add(b.getLocation().add(-iterations,0,0));
				loc.add(b.getLocation().add(-iterations,0,iterations));
				loc.add(b.getLocation().add(-iterations,0,-iterations));
				
				loc.add(b.getLocation().add(0,0,-iterations));
				loc.add(b.getLocation().add(0,0,iterations));
				for (Location ll : loc) {
					ll.getWorld().strikeLightningEffect(ll);
				for (Entity e : ll.getNearbyEntities(1,1,1)) {
					if ((e instanceof LivingEntity)) {
						if (e instanceof Player) {
							if (((Player)e).getUniqueId().equals(player.getUniqueId())) {
								continue;
							}
						}
						((LivingEntity)e).damage(Settings.STORMBREAKER_DAMAGE.getNumber());
						((LivingEntity)e).addPotionEffect(new PotionEffect(PotionEffectType.SLOW,10,1));
					}
				}
				}

				iterations++;
				if (iterations > 8){
					this.cancel();
				}
	
			}
		}.runTaskTimer(OlympusTools.getInstance(),10,5);*/
		
	//	for (Block bl : LocationUtil.getCircleAround(b.getLocation(), 5, 30, 2)) {
	//		bl.getWorld().strikeLightningEffect(bl.getLocation());
	//	}
		for (Block bl : LocationUtil.getBlocks(b.getLocation(), 4, 0, 4)) {
			bl.getWorld().strikeLightningEffect(bl.getLocation());
		}
		for (Entity en : b.getLocation().getNearbyEntities(Settings.STORMBREAKER_RADIUS.getNumber(),
				Settings.STORMBREAKER_RADIUS.getNumber(), Settings.STORMBREAKER_RADIUS.getNumber())) {
			if ((en instanceof LivingEntity)) {
				if (en instanceof Player) {
					if (((Player)en).getUniqueId().equals(player.getUniqueId())) {
						continue;
					}
				}
				((LivingEntity)en).damage(Settings.STORMBREAKER_DAMAGE.getNumber());
				((LivingEntity)en).addPotionEffect(new PotionEffect(PotionEffectType.SLOW,10,1));
			}
		}
		
		
		return true;
	}

}
