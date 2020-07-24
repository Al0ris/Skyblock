package me.joseph.skyblock.managers;

import com.sk89q.worldedit.EditSessionFactory;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.world.DataException;
import lombok.Getter;
import me.joseph.skyblock.*;
import me.joseph.skyblock.managers.data.IslandDataManager;
import me.lucko.helper.Schedulers;
import org.bukkit.Location;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IslandManager {

	public static Map<UUID, Island> islands = new HashMap<>(); //TODO- UUID is the uuid of the owner of the island

	public static WorldEditManager worldEditManager = new WorldEditManager(new EditSessionFactory());

	private static final Skyblock skyblock = Skyblock.getInstance();

	@Getter
	private static Location lastLocation = null;

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
		for (ValuedBlock valuedBlock : island.getValuedBlocks().values()) {
			value = value + valuedBlock.getValuedBlockType().getValue();
		}
		return value;
	}

	public static void generateNewIsland() {
		Location home = GridManager.getInstance().getNextSpot();
		File file = new File(skyblock.getDataFolder() + File.separator + "/schematics/island.schematic");
		Vector vector = new Vector(home.getX(), home.getY(), home.getZ());
		//TODO- method to change the air blocks around the home to island blocks
		try {
			worldEditManager.loadArea(home.getWorld(), file, vector);
		} catch (DataException | IOException | MaxChangedBlocksException e) {
			e.printStackTrace();
		}
		lastLocation = home;
	}

}