package me.joseph.skyblock.interfaces;

import me.lucko.helper.command.Command;

public interface HexionSubCommand {
	Command getCommand();
	String[] subCommandNames();
	String subCommandPermission();

}
