package me.joseph.skyblock;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class ProtocolLibManager {

	private static ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

	public static void sendWorldBorder(Player player, double newDiam, long speed) {
		PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.WORLD_BORDER);
		packet.getWorldBorderActions().writeSafely(0, EnumWrappers.WorldBorderAction.INITIALIZE);
		packet.getModifier().writeDefaults();
		packet.getDoubles().write(3, newDiam);
		packet.getLongs().write(0, speed);

		try {
			protocolManager.sendServerPacket(player, packet);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}