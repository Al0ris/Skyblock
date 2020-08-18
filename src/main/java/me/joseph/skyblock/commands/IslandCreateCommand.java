package me.joseph.skyblock.commands;

import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.world.DataException;
import me.joseph.skyblock.Island;
import me.joseph.skyblock.Skyblock;
import me.joseph.skyblock.managers.WorldEditManager;
import me.joseph.skyblock.managers.WorldManager;
import me.joseph.skyblock.commands.predicates.IslandPredicates;
import me.joseph.skyblock.interfaces.HexionSubCommand;
import me.joseph.skyblock.managers.IslandManager;
import me.lucko.helper.Commands;
import me.lucko.helper.command.Command;
import me.lucko.helper.text.Text;
import org.bson.types.ObjectId;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class IslandCreateCommand implements HexionSubCommand {

	private final Command command;
	private final String[] subCommandNames = new String[]{"create"};
	private final String permission = "Hexion.Islands.Create";

	public IslandCreateCommand() {
		command = Commands.create()
				.assertPlayer()
				.assertPermission(permission, "You do not have permission to perform this command")
				.assertUsage("create", "test")
				.assertSender(IslandPredicates.NOT_IN_ISLAND, Text.colorize("&4You already have an island"))
				.handler(c -> {
					c.reply("", "", "");
					Player player = c.sender();

					World world = generateIslandWorld(player);

					Island island = Island.builder()
							.ownerUUID(player.getUniqueId())
							.members(new HashMap<>())
							.home(world.getWorldBorder().getCenter())
							.valuedBlocks(new HashSet<>()).build();
					island.addMember(player.getUniqueId(), Island.IslandRank.OWNER);
					player.teleport(island.getHome());
					IslandManager.registerIsland(island);

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

	private World generateIslandWorld(Player player) {
		WorldManager.generateSkyblockWorld(player);
		World world = Bukkit.getWorld(player.getUniqueId().toString());
		world.getWorldBorder().setSize(125);
		world.getWorldBorder().setCenter(world.getSpawnLocation().clone().add(0, 80, 0));
		Vector vector = new Vector(world.getSpawnLocation().getX(), world.getSpawnLocation().getY() + 80, world.getSpawnLocation().getZ());
		try {
			WorldEditManager.loadArea(world, WorldEditManager.getSchematic(), vector);
		} catch (IOException | DataException | MaxChangedBlocksException e) {
			Bukkit.getConsoleSender().sendMessage(Text.colorize("&4Error in loading schematic"));
		}

		Block b = world.getBlockAt(world.getSpawnLocation().clone().add(0, 80, -2));
		if (b.getType() != Material.CHEST) return world;
		Chest chest = (Chest) b.getState();
		chest.getBlockInventory().addItem(new ItemStack(Material.LAVA_BUCKET));
		chest.getBlockInventory().addItem(new ItemStack(Material.ICE, 2));
		return world;
	}


}
