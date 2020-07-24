package me.joseph.skyblock;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class GridManager {

	private Skyblock skyblock = Skyblock.getInstance();

	@Getter
	private static final GridManager instance = new GridManager();

	private int lastX = skyblock.getConfig().getInt("islands" + ".lastX");
	private int lastZ = skyblock.getConfig().getInt("islands" + ".lastZ");

	private String lastDirection = skyblock.getConfig().getString("islands" + "lastDirection");

	public Location getNextSpot() {
		Location next;
		World world = Bukkit.getWorld("SkyblockWorld");
		if (lastDirection == "X") {
			next = new Location(world, lastX, 80, lastZ + 1500);
			skyblock.getConfig().set("islands" + ".lastZ", lastZ + 1500);
			skyblock.getConfig().set("islands" + ".lastDirection", "Z");
		} else {
			next = new Location(world, lastX + 1500, 80, lastZ);
			skyblock.getConfig().set("islands" + ".lastX", lastX + 1500);
			skyblock.getConfig().set("islands" + ".lastDirection", "X");
		}

		return next;
	}
}

