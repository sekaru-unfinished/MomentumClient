package net.indierising.momentum.network;

public class Packets {
	// inputs
	public static class Key {
		public int key;
		public boolean pressed; // whether the key was pressed or released.
	}
	
	public static class PlayerPacket {
		public int connectionID;
		public float x, y;
		public int direction;
		public String username;
		public float speed;
	}
}
