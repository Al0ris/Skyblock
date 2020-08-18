package me.joseph.skyblock;


import dev.morphia.annotations.Converters;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.NotSaved;
import dev.morphia.converters.UUIDConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

import lombok.Setter;
import org.bson.types.ObjectId;
import org.bukkit.entity.Player;

@Converters(UUIDConverter.class)
@Data
@Builder
@Entity
public class IslandPlayer {

	@Id
	@Setter(value = AccessLevel.NONE)
	private ObjectId id;


	private UUID UUID;

	@NotSaved
	private Player player;

}
