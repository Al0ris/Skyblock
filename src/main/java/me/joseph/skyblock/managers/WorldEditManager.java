package me.joseph.skyblock.managers;

import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.world.DataException;
import lombok.Data;
import lombok.Getter;
import me.joseph.skyblock.Skyblock;
import org.bukkit.World;

import java.io.File;
import java.io.IOException;

@Data
public class WorldEditManager {

	private EditSessionFactory editSessionFactory;

	@Getter
	private static final File schematic = new File(Skyblock.getInstance().getDataFolder() + "/schematics/island.schematic");

	@SuppressWarnings( "deprecation" )
	public static void loadArea(World world, File file, Vector origin) throws DataException, IOException, MaxChangedBlocksException {
		EditSession es = new EditSession(new BukkitWorld(world), 999999999);
		CuboidClipboard cc = CuboidClipboard.loadSchematic(file);
		cc.paste(es, origin, false);
	}
}

