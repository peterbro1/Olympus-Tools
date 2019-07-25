package me.gmx.olympus.entities;

import java.util.ArrayList;
import java.util.List;

import me.gmx.olympus.util.NMSUtils;
import me.gmx.olympus.util.NMSUtils.Type;
import net.minecraft.server.v1_12_R1.EntityTypes;


public class EntityHandler {

	public static EntityTypes CUSTOM_WOLF;
	
	public static List<CustomWolf> alive;
	
	public static void init() {
		alive = new ArrayList<CustomWolf>();
		NMSUtils.registerEntity("OlympusWolf", Type.ZOMBIE, CustomWolf.class, false);
       // CUSTOM_WOLF = injectNewEntity("custom_zombie", "zombie", CustomWolf.class, CustomWolf::new);

	}
	
	

	
	
}
