package me.gmx.olympus.handler;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import me.gmx.olympus.OlympusTools;
import me.gmx.olympus.tools.OlympusItem;

public class MotionHandler extends AbstractHandler implements Listener{
	private ArrayList<UUID> prevPlayersOnGround;

	public MotionHandler(OlympusItem item) {
		super(item);
		OlympusTools.getInstance().getServer().getPluginManager().registerEvents(this, OlympusTools.getInstance());
		prevPlayersOnGround = new ArrayList<UUID>();
	}
	
	
	@EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (player.getVelocity().getY() > 0) {
            double jumpVelocity = (double) 0.42F;
            if (player.hasPotionEffect(PotionEffectType.JUMP)) {
                jumpVelocity += (double) ((float) (player.getPotionEffect(PotionEffectType.JUMP).getAmplifier() + 1) * 0.1F);
            }
            if (e.getPlayer().getLocation().getBlock().getType() != Material.LADDER && prevPlayersOnGround.contains(player.getUniqueId())) {
                if (!player.isOnGround() && Double.compare(player.getVelocity().getY(), jumpVelocity) == 0) {
                    player.sendMessage("Jumping!");
                    player.setVelocity(player.getVelocity().normalize());
                }
            }
        }
        if (player.isOnGround()) {
            prevPlayersOnGround.add(player.getUniqueId());
        } else {
            prevPlayersOnGround.remove(player.getUniqueId());
        }
    }
	
}
