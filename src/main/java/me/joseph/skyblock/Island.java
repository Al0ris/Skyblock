package me.joseph.skyblock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bukkit.Location;

import java.util.*;

@Data
@AllArgsConstructor
@Builder
public class Island {

	private UUID ownerUUID;

	private Map<Location, ValuedBlock> valuedBlocks;

	private Map<UUID, IslandRank> members;
	private Location home;

	enum IslandRank {
		MEMBER, MODERATOR, CO_OWNER, OWNER
	}

	private Set<UUID> getMemberUUIDS() {
		return members.keySet();
	}


}
