package me.joseph.skyblock.commands;

import me.joseph.skyblock.Island;
import me.joseph.skyblock.commands.predicates.IslandPredicates;
import me.joseph.skyblock.interfaces.HexionSubCommand;
import me.joseph.skyblock.managers.IslandManager;
import me.lucko.helper.Commands;
import me.lucko.helper.command.Command;
import me.lucko.helper.text.Text;
import org.bukkit.entity.Player;

public class IslandJoinCommand implements HexionSubCommand {

	private Command command;
	private String[] subCommandNames = new String[]{"join"};
	private String permission = "Hexion.Island.Join";

	public IslandJoinCommand() {
		command = Commands.create()
				.assertUsage("join (player)")
				.assertPlayer()
				.assertPermission(permission)
				.assertSender(IslandPredicates.NOT_IN_ISLAND)
				.handler(c -> {
					Player other = c.arg(1).parseOrFail(Player.class);
					if (IslandManager.invites.containsKey(other.getUniqueId())) {
						if (IslandManager.invites.get(other.getUniqueId()) == c.sender().getUniqueId()) {
							Island island = IslandManager.islands.get(other.getUniqueId());
							island.getMembers().put(c.sender().getUniqueId(), Island.IslandRank.MEMBER);
							IslandManager.invites.remove(other.getUniqueId());
							c.sender().teleport(island.getHome());
							c.sender().sendMessage(Text.colorize("&dYou have joined an island!"));
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
