package me.gmx.olympus.setup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import me.gmx.olympus.OlympusTools;
import me.gmx.olympus.config.Lang;
import me.gmx.olympus.config.Settings;
import me.gmx.olympus.entities.CustomWolf;
import me.gmx.olympus.handler.AbstractHandler;
import me.gmx.olympus.handler.PacketHandler;
import me.gmx.olympus.lists.OlympusItems;
import me.gmx.olympus.util.InvenUtil;
import me.gmx.olympus.util.RandomUtils;

public class HandlerSetup {
	
	public static void initHandlers() {



		//OlympusItems.PORTABLE_CRAFTER.setHandler(null);
		/*OlympusItems.PORTABLE_CRAFTER.setHandler(new ListenHandler(OlympusTools.getInstance()) {
			
				@EventHandler
				public void entity(EntityInteractEvent e) {
					Bukkit.broadcastMessage("testx2");
				}
		});
		
		OlympusItems.THOR_AXE.setHandler(new Listener() {
			
			@EventHandler
			public void entityInteract(EntityInteractEvent e) {
				Bukkit.broadcastMessage("thor");
				
			}
			
			
			
		});
		
		
		
		*/
		
		
		/*OlympusItems.PORTABLE_CRAFTER.setHandler(new InteractListener(OlympusTools.getInstance()) {
			
			
			public void execute() {
				player.openWorkbench(null, true);
			}
			
		});
		
		OlympusItems.THOR_AXE.setHandler(new InteractListener(OlympusTools.getInstance()) {
			
			public void execute() {
				Bukkit.broadcastMessage("strike");
				Block b = player.getTargetBlock(null, Settings.MJOLNIR_RANGE.getNumber());
				b.getWorld().strikeLightning(b.getLocation().add(0,1,0));
				InvenUtil.damageItemInHand(player,12);
			}
			
			
		});
		
		OlympusItems.K9_UNIT.setHandler(new InteractListener(OlympusTools.getInstance()) {
			
			public void execute() {
				Random r = new Random();
				for (int i = 0;i < Settings.K9_AMOUNT.getNumber();i++) {
				CustomWolf cw = new CustomWolf(((CraftWorld)player.getWorld()).getHandle(),player);
				cw.spawnEntity(player.getLocation().add(r.nextInt(4)-2, 0,r.nextInt(3)-1));
				new BukkitRunnable() {
					public void run() {
						cw.delete();
					}
				}.runTaskLater(OlympusTools.getInstance(),Settings.K9_DURATION.getNumber()*20);
				}
			}
		});*/
		
		/*OlympusItems.PORTABLE_CRAFTER.setHandler(new AbstractHandler(OlympusItems.PORTABLE_CRAFTER) {
			public boolean executeAction(Action a, Player p) {
				if(!super.executeAction(a, p))
					return false;
				if (!item.allowedActions.contains(a)) {
					Bukkit.broadcastMessage("not allowed");
					return false;
				}else {
					super.executeAction(a, p);
				}
				p.playSound(p.getLocation(), Sound.BLOCK_WOOD_STEP, 0, 2);
				p.openWorkbench(null, true);			
				return true;
			}
		});
		
		OlympusItems.THOR_AXE.setHandler(new AbstractHandler(OlympusItems.THOR_AXE) {
			
			public boolean executeAction(Action a,Player p) {
				if(!super.executeAction(a, p))
					return false;
				Block b = p.getTargetBlock(null, Settings.MJOLNIR_RANGE.getNumber());
				b.getWorld().strikeLightning(b.getLocation().add(0,1,0));
				InvenUtil.damageItemInHand(p,12);
				return true;
			}
			
		});
		
		OlympusItems.K9_UNIT.setHandler(new AbstractHandler(OlympusItems.K9_UNIT) {

			public boolean executeAction(Action a,Player p) {
				if(!super.executeAction(a, p))
					return false;
				Random r = new Random();
				for (int i = 0;i < Settings.K9_AMOUNT.getNumber();i++) {
				CustomWolf cw = new CustomWolf(((CraftWorld)p.getWorld()).getHandle(),p);
				cw.spawnEntity(p.getLocation().add(r.nextInt(4)-2, 0,r.nextInt(3)-1));
				new BukkitRunnable() {
					public void run() {
						cw.delete();
					}
				}.runTaskLater(OlympusTools.getInstance(),Settings.K9_DURATION.getNumber()*20);
				}
				return true;
			}
		});
		
		OlympusItems.DISCO_BALL.setHandler(new AbstractHandler(OlympusItems.DISCO_BALL) {
			
			public boolean executeAction(Action a,Player p) {
				if (!super.executeAction(a, p))
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
					int lineParticles = 75, sphereParticles = 50;
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
				                for (Entity e : loc.getNearbyEntitiesByType(LivingEntity.class, 0.25, 0.1, 0.25)) {
				                	if (!e.getName().equals(p.getName()))
				                		((LivingEntity)e).damage(0.2);
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
		});
		
		
		OlympusItems.NECRONOMICON.setHandler(new AbstractHandler(OlympusItems.NECRONOMICON){
			private Map<Entity,Boolean> entities;
			
			public boolean executeAction(Action a,Player p) {
				if (!super.executeAction(a, p))
					return false;
				
				entities = new HashMap<Entity,Boolean>();
				OlympusTools.stayers.add(p.getName());
		        final Scoreboard board = p.getScoreboard() == null ? Bukkit.getScoreboardManager().getMainScoreboard() : p.getScoreboard();
		        
            	p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_AMBIENT, 500, 0.02F);
		        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,Settings.NECRONOMICON_DURATION.getNumber()*20,1));
		        
		        
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
	      					p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,100,6));
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
		
		});*/
		
		
		
		
		
	}

	
}
