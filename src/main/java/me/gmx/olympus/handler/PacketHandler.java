package me.gmx.olympus.handler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.comphenix.protocol.events.PacketAdapter;

import net.minecraft.server.v1_12_R1.DataWatcher;
import net.minecraft.server.v1_12_R1.DataWatcherObject;
import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.EntityLiving;
import net.minecraft.server.v1_12_R1.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_12_R1.PacketPlayOutScoreboardTeam;

public class PacketHandler {
	public static List<UUID> seeGlow = new ArrayList<UUID>();
	public static PacketAdapter packetadapter;
	
	
	public static void initPacketLib() {

	}
	
	@SuppressWarnings("static-access")
	public static void setGlowing(Player p, LivingEntity target, boolean wasGlowing) throws IllegalArgumentException, InstantiationException, ReflectiveOperationException {
        


    }
	
	@SuppressWarnings("unchecked")
	public static PacketPlayOutEntityMetadata createGlowingPacket(Entity entity, boolean enabled) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		//DataWatcherObject obj = new DataWatcherObject(entity.getId(),new DataWatcherSerializer())
		//obj.a(); // ID
		Field f = Entity.class.getDeclaredField("Z");
		f.setAccessible(true);

		byte flags = (byte)entity.getDataWatcher().get((DataWatcherObject<Byte>) f.get(entity)); //func_184212_Q
	    
		flags = (byte) (flags & 0b1011_1111); // Clear 0x40
		if (enabled) {
			flags |= 0b0100_0000; // Set 0x40
		}
		
		
		DataWatcher manager = new DataWatcher(entity); // contains no metadata currently
		//manager.set((DataWatcherObject<Byte>)f.get(entity), (byte)0b00000000);
		manager.register((DataWatcherObject<Byte>)f.get(entity), flags);
		
		return new PacketPlayOutEntityMetadata(entity.getId(), manager, true);
	}
	
	public static PacketPlayOutEntityMetadata createArrowsOnEntityPacket(EntityLiving entity, int arrows) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Field f = EntityLiving.class.getDeclaredField("br");
		f.setAccessible(true);

		//EntityEnderDragon entityender;
		//entityender = new EntityEnderDragon();
		 DataWatcherObject<Integer> br = (DataWatcherObject<Integer>) f.get(entity);
		int flags = (int) entity.getDataWatcher().get((DataWatcherObject<Integer>) f.get(entity)); //func_184212_Q
	    
	//	flags = (byte) (flags & 0b1011_1111); // Clear 0x40
	//		flags |= 0b0100_0000; // Set 0x40
		
		
		
		DataWatcher manager = new DataWatcher(entity); // contains no metadata currently
		//manager.set((DataWatcherObject<Byte>)f.get(entity), (byte)0b00000000);
		//this.set(EntityLiving.br, i);
		manager.register((DataWatcherObject<Integer>) f.get(entity), arrows);
		manager.set((DataWatcherObject<Integer>) f.get(entity), arrows);
		return new PacketPlayOutEntityMetadata(entity.getId(), manager, true);
		
	}
	
	
	
	 public void setField(PacketPlayOutScoreboardTeam packet, String field, Object value) {
	        try {
	            Field f = packet.getClass().getDeclaredField(field);
	            f.setAccessible(true);
	            f.set(packet, value);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	  
	  
		  
	


}
