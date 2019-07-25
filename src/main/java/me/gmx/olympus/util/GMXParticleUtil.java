package me.gmx.olympus.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.gmx.olympus.OlympusTools;
import me.gmx.olympus.setup.HandlerSetup;

public class GMXParticleUtil {

	
	
	
	public static void sphereParticles(Player p,int size,int layers,int duration) {
		final Location l = p.getLocation();
		
		
		//OlympusTools.stayers.add(p.getName());

				
				
				new BukkitRunnable() {/*sphere*/ double radius = size;
				Location loc = l;				int timer = 0;

					public void run() {
                        double r = size;
                        for(double phi = 0; phi <= Math.PI; phi += Math.PI / layers) {
                            double y = r * Math.cos(phi) + 1.5;
                            for(double theta = 0; theta <= 2 * Math.PI; theta += Math.PI / 30) {
                                double x = r * Math.cos(theta) * Math.sin(phi);
                                double z = r * Math.sin(theta) * Math.sin(phi);

                                l.add(x, y, z);
                                l.getWorld().spawnParticle(Particle.DRAGON_BREATH, l, 1, 0F, 0.02F, 0F, 0.002);
                                l.subtract(x, y, z);
                            }
                        }
                        if (timer > duration*10)
                        	this.cancel();
					timer++;
					}
				}.runTaskTimer(OlympusTools.getInstance(), 0, 2);
				
				

	}
	public static void discoBall(Player p,int duration,int step, int direction) {
		
		new BukkitRunnable() {
			int timer = 0;
			public void run() {
				Location location = p.getLocation().add(0, 5, 0);
				 int lineParticles = 100, sphereParticles = 50;
			     int max = 15;
			     float sphereRadius = .6f;
			     Particle lineParticle = Particle.REDSTONE; Particle sphereParticle = Particle.FLAME;
		        //Lines
			     Color lineColor = null;
			 int maxLines = 7;
		        //Lines
		        int mL = RandomUtils.random.nextInt(maxLines - 2) + 2;
		        for (int m = 0; m < mL * 2; m++) {
		            double x = RandomUtils.random.nextInt(max - max * (-1)) + max * (-1);
		            double y = RandomUtils.random.nextInt(max - max * (-1)) + max * (-1);
		            double z = RandomUtils.random.nextInt(max - max * (-1)) + max * (-1);
		            if (direction == 0) {
		                y = RandomUtils.random.nextInt(max * 2 - max) + max;
		            } else if (direction == 1) {
		                y = RandomUtils.random.nextInt(max * (-1) - max * (-2)) + max * (-2);
		            }
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
		                loc.getWorld().spawnParticle(lineParticle, loc,0,0,0,0);
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
		    
	    }
	
	public static void vortexDirection(Player player) {
		/**
	     * ParticleType of spawned particle
	     */
	     Particle particle = Particle.FLAME;

	    /**
	     * Radius of vortex (2)
	     */
	     float radius = 2;

	    /**
	     * Growing per iteration (0.05)
	     */
	     float grow = 0.2f;

	    /**
	     * Radials per iteration (PI / 16)
	     */
	     double radials = Math.PI / 16;

	    /**
	     * Helix-circles per iteration (3)
	     */
	     int circles = 6;

	    /**
	     * Amount of helices (4)
	     * Yay for the typo
	     */
	     int helixes = 4;

	    /**
	     * Current step. Works as counter
	     */
		new BukkitRunnable() {
		     int step = 0;
			Location location = player.getLocation().add(0,1,0);
			 @Override
			    public void run() {
				 if (step > 200)
					 step = 0;
			        for (int x = 0; x < circles; x++) {
			            for (int i = 0; i < helixes; i++) {
			                double angle = step * radials + (2 * Math.PI * i / helixes);
			                Vector v = new Vector(Math.cos(angle) * radius, step * grow, Math.sin(angle) * radius);
			                VectorUtils.rotateAroundAxisX(v, (location.getPitch() + 90) * MathUtils.degreesToRadians);
			                VectorUtils.rotateAroundAxisY(v, -location.getYaw() * MathUtils.degreesToRadians);

			                location.add(v);
			                //display(particle, location);
			                location.getWorld().spawnParticle(particle, location,0);
			                location.subtract(v);
			            }
			            step++;
			        }
			 }
		
		}.runTaskTimer(OlympusTools.getInstance(),0,2);
	}
	
	public static void dnaParticle(Player p,int duration) {
		int t = 3;
		        new BukkitRunnable() {
		        	 Particle particleHelix = Particle.FLAME;
		    	     Color colorHelix = null;

		    	     Particle particleBase1 = Particle.WATER_WAKE;
		    	     Color colorBase1 = null;


		    	     Particle particleBase2 = Particle.REDSTONE;
		    	     Color colorBase2 = null;

		    	     double radials = Math.PI / 8;

		    	     float radius = 0.50f;

		    	     int particlesHelix = 5;

		    	     int particlesBase = 8;
		    	 
		    	     float length = 3;

		    	     float grow = 0.3f;


		    	     float baseInterval = 2;

		    	     int step = 0;
		        	int iterations = 0;
		        	public void run() {
		        		Location location = p.getLocation().add(0,2,0).setDirection(new Vector(0,1,0));
		        		location.setYaw(0);
		        	        for (int j = 0; j < particlesHelix; j++) {
		        	            if (step * grow > length) {
		        	                step = 0;
		        	            }
		        	            for (int i = 0; i < 2; i++) {
		        	                double angle = step * radials + Math.PI * i;
		        	                Vector v = new Vector(Math.cos(angle) * radius, step * grow, Math.sin(angle) * radius);
		        	                drawParticle(location, v, particleHelix, colorHelix);
		        	            }
		        	            if (step % baseInterval == 0) {
		        	                for (int i = -particlesBase; i <= particlesBase; i++) {
		        	                    if (i == 0) {
		        	                        continue;
		        	                    }
		        	                    Particle particle = particleBase1;
		        	                    Color color = colorBase1;
		        	                    if (i < 0) {
		        	                        particle = particleBase2;
		        	                        color = colorBase2;
		        	                    }
		        	                    double angle = step * radials;
		        	                    Vector v = new Vector(Math.cos(angle), 0, Math.sin(angle)).multiply(radius * i / particlesBase).setY(step * grow);
		        	                    drawParticle(location, v, particle, color);
		        	                }
		        	            }
		        	            step++;
		        	            
		        	        }
		        	        iterations++;
		        	        if (iterations > (20/t)*duration) {this.cancel();}
		        	}
		        }.runTaskTimer(OlympusTools.getInstance(), 0, t);
        
	}
	protected static void calculateSideRatio(float sideRatio, float radius, float height) {
        float grounds, side;
        grounds = MathUtils.PI * MathUtils.PI * radius * 2;
        side = 2 * MathUtils.PI * radius * height;
        sideRatio = side / (side + grounds);
    }
	
		
	protected static void drawParticle(Location location, Vector v, Particle particle, Color color) {
        VectorUtils.rotateAroundAxisX(v, (location.getPitch() + 90) * MathUtils.degreesToRadians);
        VectorUtils.rotateAroundAxisY(v, -location.getYaw() * MathUtils.degreesToRadians);

        location.add(v);
        //display(particle, location, color);
        location.getWorld().spawnParticle(particle,location,0);
        location.subtract(v);
    }
	
	public static boolean cataclysm(final Player p){
	    final Location l = p.getLocation();
        //end of the field
        
        

        OlympusTools.stayers.add(p.getName());
        
        new BukkitRunnable()
        {
          double stop = 0.0D;
          
          public void run()
          {
        	  
        	  stop+=.2;
        	  
        	  final Location loc = l;
          	  new BukkitRunnable(){
          		  double phi = 0;
          		  double r = 9-stop;
          		  public void run(){
          			
          			  phi += Math.PI/20;
          			  for (double theta = 0;theta <= 2*Math.PI;theta+=Math.PI/40){ //change 40 if perf is bad
          				  double x = r*Math.cos(theta)*Math.sin(phi);
          				  double y = r*Math.cos(phi) ;
          				  double z = r*Math.sin(theta)*Math.sin(phi);
          				  loc.add(x,y,z);
          				  loc.getWorld().playEffect(loc, Effect.SMALL_SMOKE, 1);//
          				 // loc.getWorld().playEffect(loc, Effect.SPELL, 1);//
          				  loc.getWorld().playEffect(loc, Effect.FLYING_GLYPH, 1);//
          				  if (stop > 4.8) {
          					  loc.getWorld().playEffect(loc, Effect.COLOURED_DUST, 0);
          				  }
          				  loc.subtract(x,y,z);
          			  }
          			  if (stop > Math.PI ){ // Multiply math.pi to make more up/downs
          				  this.cancel();
          			  }
          			  
          		  }
          		  
          	  }.runTaskTimer(OlympusTools.getInstance(),0,1L);
          	  
           
            if (this.stop >= 7) {
            	l.getWorld().playEffect(l, Effect.EXPLOSION_HUGE, 102);
            	l.getWorld() .playSound(l, Sound.ENTITY_ENDERDRAGON_AMBIENT, 500, 0.02F);
            	OlympusTools.stayers.remove(p.getName());
              this.cancel();
            }
          }
        }.runTaskTimer(OlympusTools.getInstance(),0L,5L);
        
      
        
        
        return true;
  }
	
	public static void voidRocket(final Player player)
	  {
		 //final MPlayer p = MPlayer.get(player.getName());
		  
	    new BukkitRunnable()
	    {
	      final Location loc = player.getLocation();
	      final Vector direction = this.loc.getDirection().normalize();
	      double t = 0.0D;
	      private List<LivingEntity> ent = new ArrayList();
	      
	      public void run()
	      {
	        double x = this.direction.getX() * this.t;
	        double y = this.direction.getY() * this.t + 1.5D;
	        double z = this.direction.getZ() * this.t;
	        this.loc.add(x, y, z);
	        for (int i = 0; i < 4; i++)
	        {
	          player.getWorld().playEffect(this.loc, Effect.FLYING_GLYPH, 1);
	          player.getWorld().playEffect(this.loc, Effect.PORTAL, 1);
	          player.getWorld().playEffect(this.loc, Effect.PORTAL, 1);
	          player.getWorld().playEffect(this.loc, Effect.WITCH_MAGIC, 10);
	        }
	        for (Entity e : player.getNearbyEntities(500.0D, 500.0D, 500.0D)) {
	          if ((e instanceof LivingEntity)) {
	            if ((e.getLocation().distance(this.loc) <= 4.0D)) {
	              this.ent.add((LivingEntity)e);
	            } else {
	              this.ent.remove(e);
	            }
	          }
	        }
	        this.t += 0.8D;
	        if ((this.t > 30.0D) || 
	          (this.loc.getBlock().getType() != Material.AIR) || 
	          (!this.ent.isEmpty()))
	        { 
	            player.getWorld().playEffect(this.loc, Effect.EXPLOSION_HUGE, 3);

	        	for (Entity e : player.getWorld().getNearbyEntities(loc, 6, 6, 6)){
	        		if (e instanceof LivingEntity){
	        			if (e instanceof Player){
	        				//MPlayer victim = MPlayer.get((Player)e);
	        				//if (!victim.getFaction().getName().equals(MPlayer.get(player).getFaction().getName())){
	        					//Bukkit.broadcastMessage("enemy: " + victim.getFaction().getName() + " friend: " + MPlayer.get(player).getFaction().getName());
	        			          //continue;
	                			((Damageable) e).damage(((Damageable)e).getHealth()/3);
	        				//}
	        			}else{
	            			((Damageable) e).damage(((Damageable)e).getHealth()/2);

	        			}
	        			
	        		}
	        	}
	          this.cancel();
	         
	        }
	        this.loc.subtract(x, y, z);
	      }
	    }.runTaskTimer(OlympusTools.getInstance(), 0L, 1L);
	  }
	
	 public static void sphereParticles(Player p, final Effect effect){
		  final Location loc = p.getLocation();
		  new BukkitRunnable(){
			  double phi = 0;
			  double r = 1.5;
			  public void run(){
				  phi += Math.PI/10;
				  for (double theta = 0;theta <= 2*Math.PI;theta+=Math.PI/20){ //change 40 if perf is bad
					  double x = r*Math.cos(theta)*Math.sin(phi);
					  double y = r*Math.cos(phi) + 1.5;
					  double z = r*Math.sin(theta)*Math.sin(phi);
					  loc.add(x,y,z);
					  loc.getWorld().playEffect(loc, effect, 1);//
					  loc.subtract(x,y,z);
				  }
				  if (phi > Math.PI){ // Multiply math.pi to make more up/downs
					  this.cancel();
				  }
				  
			  }
			  
		  }.runTaskTimer(OlympusTools.getInstance(),0,1L);
		  
		  
		  
	  }
	
}




/* if (this.stop == 0.0D)
{
  this.maxX = (l.getBlockX() + 10);
  this.minX = (l.getBlockX() - 10);
  
  this.maxY = (l.getBlockY() + 4);
  this.minY = (l.getBlockY() - 3);
  
  this.maxZ = (l.getBlockZ() + 10);
  this.minZ = (l.getBlockZ() - 10);
  
  this.x = this.minX;
  for (this.z = this.minZ; this.z <= this.maxZ; this.z += 1) {
    for (this.y = this.minY; this.y <= this.maxY; this.y += 1)
    {
      Block block = world.getBlockAt(this.x, this.y, this.z);
      this.b.add(block);
    }
  }
  this.x = this.maxX;
  for (this.z = this.minZ; this.z <= this.maxZ; this.z += 1) {
    for (this.y = this.minY; this.y <= this.maxY; this.y += 1)
    {
      Block block = world.getBlockAt(this.x, this.y, this.z);
      this.b.add(block);
    }
  }
  this.z = this.minZ;
  for (this.x = this.minX; this.x <= this.maxX; this.x += 1) {
    for (this.y = this.minY; this.y <= this.maxY; this.y += 1)
    {
      Block block = world.getBlockAt(this.x, this.y, this.z);
      this.b.add(block);
    }
  }
  this.z = this.maxZ;
  for (this.x = this.minX; this.x <= this.maxX; this.x += 1) {
    for (this.y = this.minY; this.y <= this.maxY; this.y += 1)
    {
      Block block = world.getBlockAt(this.x, this.y, this.z);
      this.b.add(block);
    }
  }
}
p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 35, 1));
this.stop += 1.0D;
for (Block ba : this.b) {
  if ((ba != null) && 
    (ba.getType() == Material.AIR)) {
    world.playEffect(ba.getLocation(), Effect.PARTICLE_SMOKE, 1);
    world.playEffect(ba.getLocation(), Effect.SMALL_SMOKE, 1);

  }
}*/
