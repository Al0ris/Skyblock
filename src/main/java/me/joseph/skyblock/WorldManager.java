package me.joseph.skyblock;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class WorldManager {

	private WorldManager instance = new WorldManager();


	public static void generateSkyblockWorld() {
		WorldCreator wc = new WorldCreator("SkyblockWorld");
		wc.type(WorldType.FLAT);
		wc.generatorSettings("2;0;1;");
		wc.createWorld();
	}

	public static void loadSkyblockWorld() {
		WorldCreator wc = new WorldCreator("SkyblockWorld");
		if (Bukkit.getWorld("SkyblockWorld") == null) {
			Bukkit.createWorld(wc);
		}
	}

	public static void sendWorldBorder() {

	}
}
