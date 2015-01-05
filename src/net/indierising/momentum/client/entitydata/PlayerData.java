package net.indierising.momentum.client.entitydata;

import net.indierising.momentum.client.entities.Player;

public class PlayerData {
	public int connectionID;
	public String username;
	public float x, y;
	public int dir;
	public String imageLoc;
	
	public PlayerData() {}
	
	public PlayerData(Player player) {
		this.connectionID = player.getConnectionID();
		this.username = player.getUsername();
		this.x = player.getX();
		this.y = player.getY();
		this.dir = player.getDir();
		this.imageLoc = player.getImageLoc();
	}
	
	public String toString() {
		return "ID: " + connectionID + ", User: " + username + ", pos: " + x + ", " + y;
	}
}
