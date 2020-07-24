package me.joseph.skyblock.interfaces;

import me.lucko.helper.command.Command;

public interface HexionCommand {

	Command getCommand();
	String[] commandNames();
	String commandPermissions();
	HexionSubCommand[] subCommands();

	default void register() {
		getCommand().register(commandNames());
	}
}
