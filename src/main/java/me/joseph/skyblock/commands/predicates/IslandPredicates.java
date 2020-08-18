package me.joseph.skyblock.commands.predicates;

import me.joseph.skyblock.Island;
import me.joseph.skyblock.managers.IslandManager;
import org.bukkit.entity.Player;

import java.util.function.Predicate;

public class IslandPredicates {

	public static final Predicate<Player> NOT_IN_ISLAND = (player) -> {
		if (IslandManager.islands.keySet().isEmpty()) return true;

		for (Island island : IslandManager.islands.values()) {
			if (!island.getMembers().containsKey(player.getUniqueId())) continue;
			return false;
		}
		return true;
	};

	public static final Predicate<Player> IS_IN_ISLAND = (player -> {
		if (IslandManager.islands.keySet().isEmpty()) return false;
		for (Island island : IslandManager.islands.values()) {
			if (island.getMembers().containsKey(player.getUniqueId())) return true;
		}


		return false;
	});

	public static final Predicate<Player> NO_OTHER_INVITES = (player -> {
		if (IslandManager.invites.containsKey(player.getUniqueId())) {
			return true;
		}
		return false;
	});
}
