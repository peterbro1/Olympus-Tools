package me.gmx.olympus.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

import me.gmx.olympus.OlympusTools;
import me.gmx.olympus.config.Settings;
import me.gmx.olympus.core.Rarity;
import me.gmx.olympus.handler.PacketHandler;
import me.gmx.olympus.tools.OlympusItem;

public class Necronomicon extends OlympusItem<PlayerInteractEvent>{
	private Map<Entity,Boolean> entities;

	public Necronomicon(Rarity r, Material type, String name, String id, String[] lore, boolean unplaceable, int durability,
			ArrayList<ItemFlag> flags, Map<Enchantment, Integer> ench, Class<? extends Event> clas,byte data) {
		super(r, type, name, id, lore, unplaceable, durability, flags, ench, clas,data);
	}
	
	@Override
	public boolean executeAction(Player p,PlayerInteractEvent e) {
		if(!super.canExecute(p))
			return false;
		
		entities = new HashMap<Entity,Boolean>();
		OlympusTools.stayers.add(p.getName());
        final Scoreboard board = p.getScoreboard() == null ? Bukkit.getScoreboardManager().getMainScoreboard() : p.getScoreboard();
        
    	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_AMBIENT, 500, 0.02F);
        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,Settings.NECRONOMICON_DURATION.getNumber()*20,1));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,Settings.NECRONOMICON_DURATION.getNumber()*20,2));

        
        new BukkitRunnable() {
  			private int step = 5;
  			private int duration = Settings.NECRONOMICON_DURATION.getNumber();
  			private int timer = 0;
  			public void run() {
  				for (Entity e : p.getLocation().getNearbyEntities(Settings.NECRONOMICON_RANGE.getNumber(),
  						Settings.NECRONOMICON_RANGE.getNumber(), Settings.NECRONOMICON_RANGE.getNumber())) {
  					if (!(e instanceof LivingEntity) || entities.containsKey(e))
  						continue;				
  					entities.put(e, false);
  				}//entities added to list
  				List<Entity> x = new ArrayList<Entity>(entities.keySet());
  				for (Entity e : x) {
  					if (!p.getLocation().getNearbyEntities(Settings.NECRONOMICON_RANGE.getNumber(),
  						Settings.NECRONOMICON_RANGE.getNumber(), Settings.NECRONOMICON_RANGE.getNumber()).contains(e)) {//if on list and not near/ remove, send reverse packet, and remove from list
  						entities.remove(e);
						try {
							entities.remove(e);
							((CraftPlayer)p).getHandle().playerConnection.sendPacket(PacketHandler.createGlowingPacket(((CraftEntity)e).getHandle(), false));
						} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
								| SecurityException e1) {
							e1.printStackTrace();
						}
						
						OlympusTools.getInstance().scoreboardutil.removeEntityToPlayerTeam(e, p, board);
  					}else {	
  					try {
  						OlympusTools.getInstance().scoreboardutil.addEntityToPlayerTeam(e, p, board, ChatColor.DARK_RED);
  						if (!entities.get(e)) {
  							((CraftPlayer)p).getHandle().playerConnection.sendPacket(PacketHandler.createGlowingPacket(((CraftEntity)e).getHandle(), true));
						entities.replace(e, true);
  						}
					} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
							| SecurityException e1) {
						e1.printStackTrace();
						
					}
  					
  					}
  				}
  				timer++;
  				if (timer > (20/step)*duration) {
  					this.cancel();
  					p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,160,6));
  				OlympusTools.getInstance().scoreboardutil.removePlayerTeam(board, p);
  				for (Entity e : entities.keySet()) {
  					try {
						((CraftPlayer)p).getHandle().playerConnection.sendPacket(PacketHandler.createGlowingPacket(((CraftEntity)e).getHandle(), false));
					} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
							| SecurityException e1) {
						e1.printStackTrace();
					}
  				}

  				}
  				timer++;
  			}
  		}.runTaskTimer(OlympusTools.getInstance(), 0, 10L);
  		return true;
	}
	

}
