package me.gmx.olympus.items;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.scheduler.BukkitRunnable;

import me.gmx.olympus.OlympusTools;
import me.gmx.olympus.config.Settings;
import me.gmx.olympus.core.Rarity;
import me.gmx.olympus.tools.OlympusItem;

public class CloudBoots extends OlympusItem<PlayerToggleFlightEvent>{

	public CloudBoots(Rarity r, Material type, String name, String id, String[] lore, boolean unplaceable, int durability,
			ArrayList<ItemFlag> flags, Map<Enchantment, Integer> ench, Class<? extends Event> clas,byte data) {
		super(r, type, name, id, lore, unplaceable, durability, flags, ench, clas,data);
	}


	@Override
	public boolean executeAction(Player player, PlayerToggleFlightEvent e) {
		if (!super.canExecute(player))
			return false;
        e.setCancelled(true);
        player.setFlying(false);
        player.setVelocity(player.getLocation().getDirection().multiply(0.4D).setY(0.99D));
        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 1, 1);
        player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation().subtract(0,1,0),4, 0);
			if (!Boolean.valueOf(OlympusTools.getInstance().mainConfig.getConfig().getString(Settings.CLOUDBOOTS_MIDAIR.getPath()))) {
			player.setAllowFlight(false);
			new BukkitRunnable() {
				public void run() {
					if (player.isOnGround()) {
				        player.setAllowFlight(true);
				        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
				        this.cancel();
					}
				}
			}.runTaskTimer(OlympusTools.getInstance(), 10, 10);
			}
			
        return true;
		
	}
	



	
	
	
	
}
