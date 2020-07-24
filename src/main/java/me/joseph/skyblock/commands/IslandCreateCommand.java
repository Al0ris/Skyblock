package me.joseph.skyblock.commands;

import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.world.DataException;
import me.joseph.skyblock.Island;
import me.joseph.skyblock.interfaces.HexionSubCommand;
import me.joseph.skyblock.managers.IslandManager;
import me.lucko.helper.Commands;
import me.lucko.helper.command.Command;
import me.lucko.helper.text.Text;

import java.io.IOException;
import java.util.HashMap;

public class IslandCreateCommand implements HexionSubCommand {

	private final Command command;
	private final String[] subCommandNames = new String[]{"create"};
	private final String permission = "Factions.Create";

	public IslandCreateCommand() {
		command = Commands.create()
				.assertPlayer()
				.assertPermission(permission, "You do not have permission to perform this command")
				.assertUsage("create", "test")
				.handler(c -> {
					c.sender().sendMessage(Text.colorize("&cIsland created"));
					IslandManager.generateNewIsland();
					if (IslandManager.getLastLocation() == null) return;
					Island island = new Island(c.sender().getUniqueId(), new HashMap<>(), new HashMap<>(), IslandManager.getLastLocation());
					c.sender().teleport(IslandManager.getLastLocation());
					IslandManager.islands.put(c.sender().getUniqueId(), island);
				});

	}

	@Override
	public Command getCommand() {
		return command;
	}

	@Override
	public String[] subCommandNames() {
		return subCommandNames;
	}

	@Override
	public String subCommandPermission() {
		return permission;
	}
}
