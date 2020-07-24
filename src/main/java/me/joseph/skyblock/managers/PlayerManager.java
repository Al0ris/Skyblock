package me.joseph.skyblock.managers;

import me.joseph.skyblock.IslandPlayer;
import me.joseph.skyblock.managers.data.PlayerDataManager;
import me.lucko.helper.Schedulers;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {


	public static Map<UUID, IslandPlayer> players = new HashMap<>();

	/**
	 * @param uuid The UUID of the player you want to retrieve
	 * @param temporaryLoad When getting a player temporarily for a command like /stats when offline
	 * @return islandPlayer instance
	 */
	public static IslandPlayer getPlayer(UUID uuid, boolean temporaryLoad) {
		IslandPlayer islandPlayer = players.get(uuid);

		if (islandPlayer!= null) return islandPlayer;

		islandPlayer = PlayerDataManager.getPlayer(uuid);

		if (islandPlayer == null) return null;

		players.put(uuid, islandPlayer);

		if (!temporaryLoad) return islandPlayer;
		Schedulers.async().runLater(() -> {
			if (Bukkit.getPlayer(uuid) != null) return;
			players.remove(uuid);
		}, 600L);
		return islandPlayer;
	}

//	static { TODO- Put in PlayerEvents class
//		if (getPlayer(UUID.randomUUID(), false) == null) {
//			IslandPlayer islandPlayer = IslandPlayer.builder().player(null).UUID(UUID.randomUUID()).build();
//			players.put(islandPlayer.getUUID(), islandPlayer);
//		}
//	}
	/*
	run in main class on onEnable to register
	public static void registerEvents() {
	Events.subscribe(PlayerJoinEvent.class).handler(event -> {
	check here
	});
	}
	 */

}
