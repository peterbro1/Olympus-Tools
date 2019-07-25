package me.gmx.olympus;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.gmx.olympus.config.Lang;
import me.gmx.olympus.config.Settings;
import me.gmx.olympus.entities.CustomWolf;
import me.gmx.olympus.lists.OlympusItems;
import me.gmx.olympus.util.CooldownUtil;
import me.gmx.olympus.util.InvenUtil;
import me.gmx.olympus.util.ItemMetadata;
import me.gmx.olympus.util.ItemUtil;
import me.gmx.olympus.util.ItemUtil.ScanType;
import net.md_5.bungee.api.ChatColor;

public class ItemListener implements Listener{

	
/*	
	@EventHandler
	public void interactItem(PlayerInteractEvent e) {
		if (e.getPlayer().getItemInHand() == null) { return;}
		
		if (ItemMetadata.getNBTDataString(CraftItemStack.asNMSCopy(e.getItem()), "ID") != null) {
			Bukkit.broadcastMessage(ItemMetadata.getNBTDataString(CraftItemStack.asNMSCopy(e.getItem()), "ID"));
			e.setCancelled(true);
		}else{
			return;
		}
			if (ItemUtil.isItemSimiliar(e.getItem(), OlympusItems.PORTABLE_CRAFTER, true, ScanType.FULL)) {
				e.getPlayer().openWorkbench(null, true);
			}
			
			
			
			
			if (ItemUtil.isItemSimiliar(e.getItem(),OlympusItems.THOR_AXE,true,ScanType.SKIP_DURABILITY)) {
				if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) {return;}
				
				if (CooldownUtil.onCooldown(e.getPlayer().getName(), OlympusItems.THOR_AXE.getId())) {
					e.getPlayer().sendMessage(Lang.MSG_ITEM_ONCOOLDOWN.toMsg().replace("%time%", String.valueOf(CooldownUtil.getCooldown(e.getPlayer().getName(), OlympusItems.THOR_AXE.getId())/1000)).replace("%item%",OlympusItems.THOR_AXE.getDisplayName() + ChatColor.RESET));
					return;
				}
				
				Block b = e.getPlayer().getTargetBlock(null, Settings.MJOLNIR_RANGE.getNumber());
				b.getWorld().strikeLightning(b.getLocation().add(0,1,0));
				InvenUtil.damageItemInHand(e.getPlayer(),25);
				CooldownUtil.addCooldown(e.getPlayer().getName(), OlympusItems.THOR_AXE.getId(), Settings.MJOLNIR_COOLDOWN.getNumber());
				return;
			}
			
			//Bukkit.broadcastMessage(ItemMetadata.getNBTDataString(CraftItemStack.asNMSCopy(e.getItem()),"ID"));
			//Bukkit.broadcastMessage(OlympusItems.K9_UNIT.getId());
			if (ItemMetadata.getNBTDataString(CraftItemStack.asNMSCopy(e.getItem()),"ID").equals(OlympusItems.K9_UNIT.getId())) {
				//Bukkit.broadcastMessage("test");
				if (CooldownUtil.onCooldown(e.getPlayer().getName(), OlympusItems.K9_UNIT.getId())) {
					e.getPlayer().sendMessage(Lang.MSG_ITEM_ONCOOLDOWN.toMsg().replace("%time%", String.valueOf(CooldownUtil.getCooldown(e.getPlayer().getName(), OlympusItems.K9_UNIT.getId())/1000)).replace("%item%",OlympusItems.K9_UNIT.getDisplayName() + ChatColor.RESET));
					return;
				}
				CustomWolf cw = new CustomWolf(((CraftWorld)e.getPlayer().getWorld()).getHandle());
				//CooldownUtil.addCooldown(e.getPlayer().getName(), OlympusItems.K9_UNIT.getId(), Settings.K9_COOLDOWN.getNumber());
				cw.spawnEntity(e.getPlayer().getLocation(),e.getPlayer());
				new BukkitRunnable() {
					public void run() {
						cw.delete();
					}
				}.runTaskLater(OlympusTools.getInstance(),100L);

				
			}
			
			
			
		
	}
	
	*/
	
	@EventHandler
	public void playerPlaceBlock(BlockPlaceEvent e) {
		if (e.getPlayer() != null) {
			if (e.getPlayer().getItemInHand() != null) {
				net.minecraft.server.v1_12_R1.ItemStack stack = CraftItemStack.asNMSCopy(e.getPlayer().getItemInHand());
				if (ItemMetadata.hasNBTDataString(stack,"unplaceable","true")) {
					e.setCancelled(true);
				}
				
			}
		}
	}
}
