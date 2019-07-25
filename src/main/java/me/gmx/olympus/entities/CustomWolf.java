package me.gmx.olympus.entities;

import java.lang.reflect.Field;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import me.gmx.olympus.OlympusTools;
import net.minecraft.server.v1_12_R1.EntityHuman;
import net.minecraft.server.v1_12_R1.EntitySkeleton;
import net.minecraft.server.v1_12_R1.EntityWolf;
import net.minecraft.server.v1_12_R1.GenericAttributes;
import net.minecraft.server.v1_12_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_12_R1.PathfinderGoalFollowEntity;
import net.minecraft.server.v1_12_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_12_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_12_R1.World;

public class CustomWolf extends EntityWolf{
	public Player p;
	private boolean first = false;
	public CustomWolf(World world,Player p) {
	    super(world);
	    this.p = p;
	  }
	
	
	
	 public void spawnEntity(Location loc) {
		 	this.setOwnerUUID(p.getUniqueId());
		 	//this.attachedToPlayer = true;
		    this.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		    this.getBukkitEntity().setMetadata("owner", new FixedMetadataValue(OlympusTools.getInstance(),p.getName()));
		    ((CraftWorld)loc.getWorld()).getHandle().addEntity(this);
		    //getAttributeMap().b(GenericAttributes.maxHealth).setValue(20.0D);
		  

		  }
	 
	 @Override
	 protected void initAttributes() {
	     super.initAttributes();
	     // Setting the 'defense' (armor) to 5:
	     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.3);
	 }
	 
	 
	/* @Override
	 public GroupDataEntity prepare(DifficultyDamageScaler dds, GroupDataEntity gde) {
	     // Calling the super method FIRST, so in case it changes the equipment, our equipment overrides it.
	     gde = super.prepare(dds, gde);
	     // We'll set the main hand to a bow and head to a pumpkin now!
	     //this.setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.BOW));
	     this.setSlot(EnumItemSlot.HEAD, new ItemStack(Blocks.PUMPKIN));
	     // Last, returning the GroupDataEntity called gde.
	     return gde;
	 }*/
	 
	 
	 @Override
	 protected void r() {
		 super.r();
	     // Adding our custom pathfinder selectors.
	     // Grants our zombie the ability to swim.
	     this.goalSelector.a(0, new PathfinderGoalFloat(this));
	     // This causes our zombie to shoot arrows.
	     // The parameters are: The ranged entity, movement speed, cooldown,
	     // maxDistance
	     // Or, with the second constructor: The ranged entity, movement speed,
	     // mincooldown, maxcooldown, maxDistance
	     //this.goalSelector.a(2, new PathfinderGoalArrowAttack(this, 1.0, 12, 20));
	     // Gets our zombie to attack creepers and skeletons!
	     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
	     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntitySkeleton.class, true));
	     this.goalSelector.a(9,new PathfinderGoalFollowEntity(this, 0.2, 4, 4));
	     // Causes our zombie to walk towards it restriction.
	     //this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0));
	     // Causes the zombie to walk around randomly.
	     //this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 0.5));
	     // Causes the zombie to look at players. Optional in our case. Last
	     // argument is range.
	     //this.goalSelector.a(arg0);
	     //this.setGoalTarget(entityliving);
	     this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0f));
	     // Causes the zombie to randomly look around.
	     //this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
	 }
	 
	  
	  public static Object getPrivateField(String fieldName, Class<?> clazz, Object object) {
	    Object o = null;

	    
	    try {
	      Field field = clazz.getDeclaredField(fieldName);
	      
	      field.setAccessible(true);
	      
	      o = field.get(object);
	    }
	    catch (NoSuchFieldException e) {
	      
	      e.printStackTrace();
	    }
	    catch (IllegalAccessException e) {
	      
	      e.printStackTrace();
	    } 
	    
	    return o;
	  }



	  
	 // public void move(double d1, double d2, double d3) {}


	  
	 /* public boolean damageEntity(DamageSource damagesource, float f) {
		return true;
		  
	  }*/


	  
	  public void delete() { this.world.removeEntity(this); }
}
