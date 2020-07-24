package me.joseph.skyblock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bukkit.Location;

@Data
@AllArgsConstructor
@Builder
public class ValuedBlock {

	private ValuedBlockType valuedBlockType;
	private Location location;

}
