package me.gmx.olympus.items;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftArrow;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;
import org.bukkit.event.player.PlayerInteractEvent;

import me.gmx.olympus.OlympusTools;
import me.gmx.olympus.config.Lang;
import me.gmx.olympus.core.Rarity;
import me.gmx.olympus.handler.PacketHandler;
import me.gmx.olympus.tools.OlympusItem;

public class Porcupine extends OlympusItem<PlayerInteractEvent> implements Listener{
	public ArrayList<UUID> currentPlayers;
	public Porcupine(Rarity r, Material type, String name, String id, String[] lore, boolean unplaceable, int durability,
			ArrayList flags, Map ench, Class clas,byte data) {
		super(r, type, name, id, lore, unplaceable, durability, flags, ench, clas,data);
		currentPlayers = new ArrayList<UUID>();
		
	}
	public void initListener() {
		Bukkit.getPluginManager().registerEvents(this, OlympusTools.getInstance());
		OlympusTools.getInstance().getLogger().log(Level.SEVERE,"REGISTERING LISTENER");
	}

	@Override
	public boolean executeAction(Player player, PlayerInteractEvent e) {
		if (!super.canExecute(player))
			return false;
			//(((CraftPlayer)(p))).getHandle().playerConnection.sendPacket(PacketHandler.createArrowsOnEntityPacket(((CraftLivingEntity)player).getHandle(), 0));
			player.setArrowsStuck(400);
			player.sendMessage(Lang.PREFIX + "You are now surrounded by thorns!");
			player.getWorld().playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_CHAIN, 1, 1);
			currentPlayers.add(player.getUniqueId());
			Bukkit.getScheduler().scheduleSyncDelayedTask(OlympusTools.getInstance(), () -> {
				try {
					(((CraftPlayer)(player))).getHandle().playerConnection.sendPacket(PacketHandler.createArrowsOnEntityPacket(((CraftLivingEntity)player).getHandle(), 0));
					currentPlayers.remove(player.getUniqueId());
					player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
					player.sendMessage(Lang.PREFIX + "You are no longer surrounded by thorns!");
				}catch(Exception ez) {
					
				}
				},120);
			

		
		return false;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOW)
	public void damage(EntityDamageByEntityEvent e) {
		if (e.isCancelled())
			return;
		//Bukkit.broadcastMessage("damage taken:" + String.valueOf(e.getDamage() + e.getDamage(DamageModifier.ARMOR)));
		//Bukkit.broadcastMessage("damage reflected: " + String.valueOf((e.getDamage() + e.getDamage(DamageModifier.ARMOR))*1.5));
		if ((!(e.getDamager() instanceof LivingEntity) && !(e.getDamager() instanceof CraftArrow)) ||
				(e.getDamager() instanceof CraftArrow && !(((CraftArrow)e.getDamager()).getShooter() instanceof LivingEntity)))
			return;
		
		if (e.getEntity() instanceof Player && e.getDamager() instanceof LivingEntity) {
			if (currentPlayers.contains(e.getEntity().getUniqueId())) {
				//e.setCancelled(true);
				e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.BLOCK_CLOTH_FALL, 20, 1);
				((LivingEntity)e.getDamager()).damage(e.getDamage(DamageModifier.ARMOR)*1.5);
				//currentPlayers.remove(e.getEntity().getUniqueId());
			}
		}else if (e.getEntity() instanceof Player && e.getDamager() instanceof CraftArrow) {
			if (currentPlayers.contains(e.getEntity().getUniqueId())) {
				
				//e.setCancelled(true);
				e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.BLOCK_CLOTH_FALL, 20, 1);
				((LivingEntity)((CraftArrow)e.getDamager()).getShooter()).damage((e.getDamage() + e.getDamage(DamageModifier.ARMOR))*1.5);
				//currentPlayers.remove(e.getEntity().getUniqueId());
			}
		}
		
		
		
	}

}
