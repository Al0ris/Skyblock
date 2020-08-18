package me.joseph.skyblock.commands;

import me.joseph.skyblock.commands.predicates.IslandPredicates;
import me.joseph.skyblock.interfaces.HexionSubCommand;
import me.joseph.skyblock.managers.IslandManager;
import me.lucko.helper.Commands;
import me.lucko.helper.Schedulers;
import me.lucko.helper.command.Command;
import me.lucko.helper.text.Text;
import org.bukkit.entity.Player;

public class IslandInviteCommand implements HexionSubCommand {

	private Command command;
	private String[] subCommandNames = new String[]{"invite"};
	private String permission = "Hexion.Island.Invite";

	public IslandInviteCommand() {
		command = Commands.create()
				.assertUsage("invite (player)")
				.assertPlayer()
				.assertPermission(permission)
				.assertSender(IslandPredicates.IS_IN_ISLAND, "not in island")
				.assertSender(IslandPredicates.NO_OTHER_INVITES, "other invites")
				.handler(c -> {
					Player other = c.arg(1).parseOrFail(Player.class);
					Player sender = c.sender();
					other.sendMessage(Text.colorize("&dYou have been invited to " + sender.getDisplayName() + "'s Island. You have 5 minutes to join"));
					IslandManager.invites.put(sender.getUniqueId(), other.getUniqueId());

					Schedulers.async().runLater(() -> {
						if (IslandManager.invites.get(sender.getUniqueId()) == other.getUniqueId()) {
							IslandManager.invites.remove(sender.getUniqueId());
						}
					}, 6000);
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
