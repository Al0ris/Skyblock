package me.joseph.skyblock.managers.data;

import dev.morphia.Datastore;
import dev.morphia.InsertOptions;
import dev.morphia.query.Query;
import me.joseph.skyblock.Island;
import me.joseph.skyblock.managers.IslandManager;
import me.joseph.skyblock.managers.MongoManager;
import org.bukkit.Bukkit;

import java.util.Collection;
import java.util.UUID;

public class IslandDataManager {

	private static final Datastore datastore = MongoManager.getDatastore();

	public static Island getIsland(UUID id) {
		return datastore.createQuery(Island.class).field("ownerUUID").equalIgnoreCase(id).first();
	}

	public static void saveIsland(Island island) {
		datastore.save(island);
	}

	public static void saveIslands(Collection<Island> islands) {
		datastore.save(islands, new InsertOptions());
	}

	public static void removeIsland(UUID id) {
		Query<Island> islandQuery = datastore.createQuery(Island.class).field("ownerUUID").equalIgnoreCase(id);
		datastore.delete(islandQuery);
	}


}
