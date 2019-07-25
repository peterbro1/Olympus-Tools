package me.gmx.olympus.items;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.gmx.olympus.OlympusTools;
import me.gmx.olympus.config.Lang;
import me.gmx.olympus.config.Settings;
import me.gmx.olympus.core.Rarity;
import me.gmx.olympus.tools.OlympusItem;
import me.gmx.olympus.util.RandomUtils;

public class DiscoBall extends OlympusItem<PlayerInteractEvent>{

	public DiscoBall(Rarity r, Material type, String name, String id, String[] lore, boolean unplaceable, int durability,
			ArrayList<ItemFlag> flags, Map<Enchantment, Integer> ench, Class<? extends Event> clas,byte data) {
		super(r, type, name, id, lore, unplaceable, durability, flags, ench, clas,data);
	}
	
	



	@Override
	public boolean executeAction(Player p, PlayerInteractEvent e) {
		if(!super.canExecute(p))
			return false;
		
		
		if (!p.getWorld().getBlockAt(p.getLocation().add(0,10,0)).getType().equals(Material.AIR)) {
			p.sendMessage(Lang.PREFIX + "This item needs space!");
			return false;
		}
		
		
		/////////////////
		int step = 4;
		int duration = Settings.DISCO_DURATION.getNumber();
		int direction = 0;
		////////////////
		new BukkitRunnable() {
			int timer = 0;
			int lineParticles = 100, sphereParticles = 50;
		     int max = 15;
		     float sphereRadius = .6f;
			 int maxLines = 8;
				Location location = p.getLocation().add(0, 10, 0);
			public void run() {
				
				
				 
			     Particle lineParticle = Particle.REDSTONE; Particle sphereParticle = Particle.FLAME;
		        //Lines
		        //Lines
		        int mL = RandomUtils.random.nextInt(maxLines - 2) + 2;
		        for (int m = 0; m < mL * 2; m++) {
		            double x = RandomUtils.random.nextInt(max - max * (-1)) + max * (-1);
		            double y = RandomUtils.random.nextInt(max - max * (-1)) + max * (-1);
		            double z = RandomUtils.random.nextInt(max - max * (-1)) + max * (-1);
		           // if (direction == 0) {
		                y = RandomUtils.random.nextInt(max * 2 - max) + max;
		            //} else if (direction == 1) {
		            //    y = RandomUtils.random.nextInt(max * (-1) - max * (-2)) + max * (-2);
		          //  }
		            Location target = location.clone().subtract(x, y, z);
		            if (target == null) {
		                cancel();
		                return;
		            }
		            Vector link = target.toVector().subtract(location.toVector());
		            float length = (float) link.length();
		            link.normalize();

		            float ratio = length / lineParticles;
		            Vector v = link.multiply(ratio);
		            Location loc = location.clone().subtract(v);
		            for (int i = 0; i < lineParticles; i++) {
		                loc.add(v);
		                //display(lineParticle, loc, lineColor);
		                loc.getWorld().spawnParticle(lineParticle, loc,0,0,0,0,Color.GREEN.asRGB());
		                for (Entity e : loc.getNearbyEntitiesByType(LivingEntity.class, 0.29, 0.1, 0.29)) {
		                	if (!e.getName().equals(p.getName()))
		                		((LivingEntity)e).damage(5);
		                }
		            }
		        }

		        //Sphere
		        for (int i = 0; i < sphereParticles; i++) {
		            Vector vector = RandomUtils.getRandomVector().multiply(sphereRadius);
		            location.add(vector);
		            //display(sphereParticle, location, sphereColor);
	                location.getWorld().spawnParticle(sphereParticle, location,0,0,0,0);
		            location.subtract(vector);
		        }
		        
		        timer++;
		        if (timer > (20/step)*duration) {
		        	this.cancel();
		        }
			}
		}.runTaskTimer(OlympusTools.getInstance(), 0, step);
		return true;
	}

}
