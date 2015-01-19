package net.indierising.momentum.client.entitydata;

import net.indierising.momentum.client.entities.Player;
import net.indierising.momentum.client.network.Packets.PlayerClass;

public class PlayerData {
	public int connectionID;
	public String username;
	public float x, y;
	public float width, height;
	public int dir;
	public String imageLoc;
	public int map;
	public PlayerClass playerClass;
	
	public PlayerData() {}
	
	public PlayerData(Player player) {
		this.connectionID = player.getConnectionID();
		this.username = player.getUsername();
		this.x = player.getX();
		this.y = player.getY();
		this.width = player.getWidth();
		this.height = player.getHeight();
		this.dir = player.getDir();
		this.imageLoc = player.getImageLoc();
		this.playerClass = player.getPlayerClass();
	}
	
	public String toString() {
		return "ID: " + connectionID + ", User: " + username + ", pos: " + x + ", " + y;
	}
}
