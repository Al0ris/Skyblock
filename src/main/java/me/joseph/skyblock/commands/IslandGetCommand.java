package me.joseph.skyblock.commands;

import me.joseph.skyblock.Island;
import me.joseph.skyblock.interfaces.HexionSubCommand;
import me.joseph.skyblock.managers.IslandManager;
import me.lucko.helper.Commands;
import me.lucko.helper.command.Command;
import org.bukkit.entity.Player;

public class IslandGetCommand implements HexionSubCommand {

	private Command command;
	private String[] subCommandNames = new String[]{"list"};
	private String permission = "Hexion.Island.Get";

	public IslandGetCommand() {
		command = Commands.create()
				.assertPlayer()
				.assertPermission(permission)
				.handler(c -> {
					Player other = c.arg(1).parseOrFail(Player.class);

					if (IslandManager.islands.containsKey(other.getUniqueId())) {
						Island island = IslandManager.islands.get(other.getUniqueId());
						System.out.println(island);
						return;
					}
					for (Island island : IslandManager.islands.values()) {
						if (island.getMembers().containsKey(other.getUniqueId())) {
							System.out.println(island);
							return;
						}
					}
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
