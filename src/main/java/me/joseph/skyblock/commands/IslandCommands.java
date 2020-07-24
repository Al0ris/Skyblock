package me.joseph.skyblock.commands;

import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.world.DataException;
import me.joseph.skyblock.interfaces.HexionCommand;
import me.joseph.skyblock.interfaces.HexionSubCommand;
import me.lucko.helper.Commands;
import me.lucko.helper.command.Command;

import java.io.IOException;

public class IslandCommands implements HexionCommand {

	private final Command command;
	private final String[] commandNames = new String[]{"island", "is"};
	private final HexionSubCommand[] subCommands;
	private final String permission = "Hexion.Island";

	private final String[] helpMessage = new String[]{"Island Commands:", "	Create"};

	public IslandCommands() {
		this.subCommands = new HexionSubCommand[]{new IslandCreateCommand()};
		this.command = Commands.create()
			.assertPlayer()
			.assertPermission(permission)
			.handler(c -> {
				for (HexionSubCommand subCommand : subCommands()) {
					for (String commandName : subCommand.subCommandNames()) {
						if (!commandName.equalsIgnoreCase(c.rawArg(0))) continue;
						subCommand.getCommand().call(c);
						return;
					}
				}
				c.reply(helpMessage);
			});


	}

	@Override
	public Command getCommand() {
		return command;
	}

	@Override
	public String[] commandNames() {
		return commandNames;
	}

	@Override
	public String commandPermissions() {
		return permission;
	}

	@Override
	public HexionSubCommand[] subCommands() {
		return subCommands;
	}

	@Override
	public void register() {
		getCommand().register(commandNames);
	}


}
