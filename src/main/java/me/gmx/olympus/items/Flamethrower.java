package me.gmx.olympus.items;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.gmx.olympus.OlympusTools;
import me.gmx.olympus.core.Rarity;
import me.gmx.olympus.tools.OlympusItem;

public class Flamethrower extends OlympusItem<PlayerInteractEvent>{
	public Random random = new Random();
	public MaterialData[] flameTypes;
	private ArrayList<FallingBlock> flame;
	public Flamethrower(Rarity r, Material type, String name, String id, String[] lore, boolean unplaceable, int durability,
			ArrayList<ItemFlag> flags, Map<Enchantment, Integer> ench, Class<? extends Event> clas, byte data) {
		super(r, type, name, id, lore, unplaceable, durability, flags, ench, clas, data);
	}

	@Override
	public boolean executeAction(Player player, PlayerInteractEvent e) {
		if (!super.canExecute(player))
		return false;
		
		ItemStack offhand = player.getInventory().getItemInOffHand();
		player.getInventory().setItemInOffHand(new ItemStack(Material.SHIELD,1,(byte)0));
		flame = new ArrayList<FallingBlock>();
		this.flameTypes = new MaterialData[] { new MaterialData(Material.FIRE, (byte)0), new MaterialData(Material.FIRE, (byte)0), new MaterialData(Material.LAVA, (byte)4) };
		new BukkitRunnable() {
			public void run() {
				if (!player.isBlocking()) {
					player.getInventory().setItemInOffHand(offhand);
					this.cancel();
				}
				
				 final Location from = player.getLocation();
			        from.setPitch(from.getPitch() - 10.0f);
			        final Vector baseDirection = from.getDirection();
			        from.add(baseDirection.multiply(1.5));
			        for (int flames = (int)Math.floor(random.nextFloat() + 10 / 5.0), i = 0; i < flames; ++i) {
			            final MaterialData flameType = flameTypes[random.nextInt(flameTypes.length)];
			            final FallingBlock entity = player.getWorld().spawnFallingBlock(from, flameType.getItemType(), flameType.getData());
			            entity.setFireTicks(20);
			            final double dx = baseDirection.getX() * 0.6 + random.nextGaussian() * 0.2;
			            final double dy = baseDirection.getY() * 0.6 + random.nextDouble() * 0.4;
			            final double dz = baseDirection.getZ() * 0.6 + random.nextGaussian() * 0.2;
			            entity.setVelocity(new Vector(dx, dy, dz));
			            entity.setDropItem(false);
			            flame.add(entity);
			        }
			        
			}
		}.runTaskTimer(OlympusTools.getInstance(),10,5);
		
		return true;
	}

}
