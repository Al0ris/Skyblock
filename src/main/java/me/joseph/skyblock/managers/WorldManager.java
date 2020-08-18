package me.joseph.skyblock.managers;

import me.joseph.skyblock.Island;
import org.bukkit.Bukkit;

import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

public class WorldManager {

	private WorldManager instance = new WorldManager();


	public static void generateSkyblockWorld(Player player) {
		WorldCreator wc = new WorldCreator(player.getUniqueId().toString());
		wc.type(WorldType.FLAT);
		wc.generatorSettings("2;0;1;");
		wc.createWorld();
	}

	public static void loadSkyblockWorld(Player player) {
		WorldCreator wc = new WorldCreator(player.getUniqueId().toString());
		if (Bukkit.getWorld("SkyblockWorld") == null) {
			Bukkit.createWorld(wc);
		}
	}

	public static void unloadIsland(Island island) {
		Bukkit.unloadWorld(Bukkit.getWorld(island.getOwnerUUID().toString()), true);
	}

}
