package net.indierising.momentum.client.network;

import net.indierising.momentum.client.entitydata.NPCData;
import net.indierising.momentum.client.entitydata.PlayerData;

public class Packets {
	public static class ConstantsPacket {
		public int conID;
		public int TILE_SIZE, MAX_MAPS, MAX_MAP_NPCS;
		public float PLAYER_WIDTH, PLAYER_HEIGHT;
		public int DIR_UP, DIR_DOWN, DIR_LEFT, DIR_RIGHT;
	}
	
	public static class Key {
		public int key;
		public boolean pressed; // whether the key was pressed or released
	}
	
	public static class PlayerPacket {
		public PlayerData data;
	}
	
	public static class PlayerMove {
		public int connectionID;
		public float x, y;
		public int dir;
	}
	
	public static class NPCPacket {
		public NPCData data;
	}
	
	public static class NPCMove {
		public int id;
		public float x, y;
		public int dir;
	}
	
	public static class ChatMessage {
		public String title, message;
	}
	
	// information about player class
	public static class PlayerClass{
		public String name,description;
		public int id,damage,health;
		// TODO add abilities
	}
	
	public static class PlayerMapChange {
		public int playerID, mapID;
		public String mapName;
	}
}
