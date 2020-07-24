package me.joseph.skyblock.managers.data;

import dev.morphia.Datastore;
import dev.morphia.InsertOptions;
import dev.morphia.query.Query;
import me.joseph.skyblock.IslandPlayer;
import me.joseph.skyblock.managers.MongoManager;

import java.util.Collection;
import java.util.UUID;

public class PlayerDataManager {

	private static final Datastore datastore = MongoManager.getDatastore();

	public static IslandPlayer getPlayer(UUID id) {
		return datastore.createQuery(IslandPlayer.class).filter("id ==", id).first();
	}

	public static void savePlayer(IslandPlayer islandPlayer) {
		datastore.save(islandPlayer);
	}

	public static void savePlayers(Collection<IslandPlayer> players) {
		datastore.save(players, new InsertOptions());
	}

	public static void removePlayer(UUID id) {
		Query<IslandPlayer> islandPlayerQuery = datastore.createQuery(IslandPlayer.class)
				.filter("id ==", id);
		datastore.delete(islandPlayerQuery);
	}
}
