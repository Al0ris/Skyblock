package me.joseph.skyblock.events;

import me.joseph.skyblock.Island;
import me.joseph.skyblock.managers.WorldManager;
import me.joseph.skyblock.managers.IslandManager;
import me.joseph.skyblock.managers.data.IslandDataManager;
import me.lucko.helper.Events;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoin {

	public void onJoin() {
		Events.subscribe(PlayerJoinEvent .class).handler(e -> {
			if (IslandDataManager.getIsland(e.getPlayer().getUniqueId()) != null) return;
			WorldManager.loadSkyblockWorld(e.getPlayer());
			Island island = IslandDataManager.getIsland(e.getPlayer().getUniqueId());
			IslandManager.islands.put(e.getPlayer().getUniqueId(), island);

		});
	}
	public void onLeave() {
		Events.subscribe(PlayerQuitEvent.class).handler(e -> {
			if (!IslandManager.islands.containsKey(e.getPlayer().getUniqueId())) return;
			Island island = IslandManager.islands.get(e.getPlayer().getUniqueId());
			IslandDataManager.saveIsland(island);
			IslandManager.islands.remove(e.getPlayer().getUniqueId());
		});
	}

}
