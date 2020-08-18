package me.joseph.skyblock.commands;

import me.joseph.skyblock.interfaces.HexionSubCommand;
import me.joseph.skyblock.managers.IslandManager;
import me.lucko.helper.Commands;
import me.lucko.helper.command.Command;

public class IslandClearCommand implements HexionSubCommand {

	private final Command command;
	private final String[] subCommandNames = new String[]{"clear", "cleardata"};
	private final String permission = "Hexion.Islands.Clear";

	public IslandClearCommand() {
		this.command = Commands.create()
				.assertPermission(permission)
				.assertPlayer()
				.handler(c -> {
					IslandManager.islands.clear();
					System.out.println(IslandManager.islands);
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
