package me.joseph.skyblock;


import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.NotSaved;
import lombok.Builder;
import lombok.Data;
import org.bukkit.entity.Player;

import java.util.UUID;

@Data
@Builder
@Entity
public class IslandPlayer {

	@Id
	private UUID UUID;

	@NotSaved
	private Player player;

}
