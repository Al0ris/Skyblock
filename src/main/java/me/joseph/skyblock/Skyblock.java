package me.joseph.skyblock;

import me.joseph.skyblock.commands.IslandCommands;
import me.joseph.skyblock.managers.IslandManager;
import me.joseph.skyblock.managers.data.IslandDataManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Skyblock extends JavaPlugin {

	private static Skyblock instance;

	@Override
	public void onEnable() {
		// Plugin startup logic
		instance = this;
		WorldManager.generateSkyblockWorld();
		new IslandCommands().register();

		File file = new File(getDataFolder(), "config.yml");
		if (!file.exists()) {
			if (file.getParentFile().mkdirs()) saveResource("config.yml", false);
		}

		IslandDataManager.saveIslands(IslandManager.islands.values());
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}

	public static Skyblock getInstance() {
		return instance;
	}


}