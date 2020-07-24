package me.joseph.skyblock;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;

public class ProtocolLibManager {
	private ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

	Skyblock skyblock = Skyblock.getInstance();

	public void sendPacket() {
		PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.WORLD_BORDER);
		packet.getModifier().writeDefaults();

	}

}
