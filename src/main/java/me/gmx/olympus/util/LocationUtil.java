package me.gmx.olympus.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class LocationUtil {


	public static List<Block> getBlocks(Location center, int xRadius, int yRadius, int zRadius) {
        if (center == null) {
            throw new IllegalArgumentException("center cannot be null.");
        }

        int minX = center.getBlockX() - xRadius;
        int maxX = center.getBlockX() + xRadius;
        int minY = center.getBlockY() - yRadius;
        int maxY = center.getBlockY() + yRadius;
        int minZ = center.getBlockZ() - zRadius;
        int maxZ = center.getBlockZ() + zRadius;

        Location currentLocation = new Location(center.getWorld(), 0.0d, 0.0d, 0.0d);
        List<Block> blocks = new ArrayList<>();

        for (int x = minX; x <= maxX; x++) {
            currentLocation.setX(x);
            for (int z = minZ; z <= maxZ; z++) {
                currentLocation.setZ(z);
                for (int y = minY; y <= maxY; y++) {
                    currentLocation.setY(y);
                    blocks.add(currentLocation.getBlock());
                }
            }
        }

        return blocks;
    }	
	
	
	   public static Set<Block> getCircleAround(Location loc, double radius, int numPoints, int maxHeight) {
	        Set<Block> retVal = new HashSet<>();
	        double piSlice = (2*Math.PI) / numPoints;

	        double angle = loc.getYaw();

	        while (angle < 0.0d) {
	            angle += 360.0d;
	        }
	        while (angle > 360.0d) {
	            angle -= 360.0d;
	        }

	        angle = angle * (2*Math.PI) / 360.0d;

	        for (int i = 0; i < numPoints; i++) {
	            double newAngle = angle + piSlice * i;
	            Block b = new Location(loc.getWorld(), loc.getX() + radius * Math.cos(newAngle), loc.getY(), loc.getZ() + radius * Math.sin(newAngle)).getBlock();
	            if (Math.abs(b.getLocation().getY() - loc.getY()) <= maxHeight) {
	                retVal.add(b);
	            }
	        }

	        return retVal;
	    }
	
}
