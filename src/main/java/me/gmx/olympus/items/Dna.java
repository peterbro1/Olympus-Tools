package me.gmx.olympus.items;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.gmx.olympus.core.Rarity;
import me.gmx.olympus.tools.OlympusItem;
import me.gmx.olympus.util.GMXParticleUtil;

public class Dna extends OlympusItem<PlayerItemConsumeEvent>{

	public Dna(Rarity r, Material type, String name, String id, String[] lore, boolean unplaceable, int durability,
			ArrayList<ItemFlag> flags, Map<Enchantment, Integer> ench, Class<? extends Event> clas, byte data) {
		super(r, type, name, id, lore, unplaceable, durability, flags, ench, clas, data);
	}

	@Override
	public boolean executeAction(Player player, PlayerItemConsumeEvent e) {
		if (!super.canExecute(player))
			return false;
		
		GMXParticleUtil.dnaParticle(player,10);
		
		
		ArrayList<PotionEffect> k = new ArrayList<PotionEffect>();
		k.add(new PotionEffect(PotionEffectType.SPEED,10*20,4));
		k.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,10*20,3));
		k.add(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,10*20,2));
		k.add(new PotionEffect(PotionEffectType.FAST_DIGGING,10*20,3));
		k.add(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,10*20,1));
		k.add(new PotionEffect(PotionEffectType.HEALTH_BOOST,10*20,4));
		k.add(new PotionEffect(PotionEffectType.REGENERATION,10*20,3));
		k.add(new PotionEffect(PotionEffectType.JUMP,10*20,3));
		k.add(new PotionEffect(PotionEffectType.GLOWING,10*20,1));
		
		player.addPotionEffects(k);
		GMXParticleUtil.sphereParticles(player, Effect.FLYING_GLYPH);
		player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_FLAP, 1, 1);
		
		return true;
	}

}
