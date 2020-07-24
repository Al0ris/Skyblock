package me.joseph.skyblock;

import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.world.DataException;
import org.bukkit.World;

import java.io.File;
import java.io.IOException;

public class WorldEditManager {

	private EditSessionFactory editSessionFactory;

	public WorldEditManager(EditSessionFactory editSessionFactory) {
		this.editSessionFactory = editSessionFactory;
	}

	@SuppressWarnings( "deprecation" )
	public static void loadArea(World world, File file, Vector origin) throws DataException, IOException, MaxChangedBlocksException {
		EditSession es = new EditSession(new BukkitWorld(world), 999999999);
		CuboidClipboard cc = CuboidClipboard.loadSchematic(file);
		cc.paste(es, origin, false);
	}
}

