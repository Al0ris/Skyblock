package me.joseph.skyblock;

import me.joseph.skyblock.commands.IslandCommands;
import me.joseph.skyblock.managers.IslandManager;
import me.joseph.skyblock.managers.data.IslandDataManager;
import me.lucko.helper.Schedulers;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Skyblock extends JavaPlugin {

	private static Skyblock instance;


	@Override
	public void onEnable() {
		// Plugin startup logic
		instance = this;
		new IslandCommands().register();

		File file = new File(getDataFolder(), "config.yml");
		if (!file.exists()) {
			if (file.getParentFile().mkdirs()) saveResource("config.yml", false);
		}

		//Saves worlds every 10 mins
		Schedulers.async().runLater(() -> IslandDataManager.saveIslands(IslandManager.islands.values()), 12000L);

	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
		IslandDataManager.saveIslands(IslandManager.islands.values());
	}

	public static Skyblock getInstance() {
		return instance;
	}


}