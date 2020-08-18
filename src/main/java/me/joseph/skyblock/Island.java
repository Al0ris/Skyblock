package me.joseph.skyblock;

import dev.morphia.annotations.Converters;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.converters.UUIDConverter;
import lombok.*;
import org.bukkit.Location;

import java.util.*;

@Data
@AllArgsConstructor
@Builder
@Entity
public class Island {

	@Id
	private UUID ownerUUID;

	private Set<ValuedBlock> valuedBlocks;

	private Map<UUID, IslandRank> members;
	private Location home;

	public enum IslandRank {
		MEMBER, MODERATOR, CO_OWNER, OWNER
	}

	public void addMember(UUID uuid) {
		members.put(uuid, IslandRank.MEMBER);
	}

	public void addMember(UUID uuid, IslandRank rank) {
		members.put(uuid, rank);
	}

	public void setRank(UUID uuid, IslandRank rank) {
		members.replace(uuid, rank);
	}

	public void removeMember(UUID uuid) {
		members.remove(uuid);
	}

	private Set<UUID> getMemberUUIDS() {
		return members.keySet();
	}


}
