package me.joseph.skyblock.managers;

import com.sk89q.worldedit.EditSessionFactory;
import lombok.Getter;
import me.joseph.skyblock.*;
import me.joseph.skyblock.managers.data.IslandDataManager;
import me.lucko.helper.Schedulers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IslandManager {

	public static Map<UUID, Island> islands = new HashMap<>(); //TODO- UUID is the uuid of the owner of the island


	public static Map<UUID, UUID> invites = new HashMap<>();

	private static final Skyblock skyblock = Skyblock.getInstance();

	public static Island getIsland(UUID uuid, boolean temporaryLoad) {

		Island island = islands.get(uuid);
		if (island!= null) return island;
		island = IslandDataManager.getIsland(uuid);

		if (island == null) return null;

		islands.put(uuid, island);

		if (!temporaryLoad) return island;
		Schedulers.async().runLater(() -> islands.remove(uuid), 600L);
		return null;
	}

	public static double getIslandValue(Island island) {
		double value = 0;
		for (ValuedBlock valuedBlock : island.getValuedBlocks()) {
			value = value + valuedBlock.getValuedBlockType().getValue();
		}
		return value;
	}

	public static void registerIsland(Island island) {
		islands.put(island.getOwnerUUID(), island);
	}
	public static void unregisterIsland(UUID uuid) {
		islands.remove(uuid);
		IslandDataManager.removeIsland(uuid);
	}

}