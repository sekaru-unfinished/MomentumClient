package net.indierising.momentum.client.entities;

import net.indierising.momentum.client.entitydata.PlayerData;
import net.indierising.momentum.client.network.Packets.PlayerClass;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Player extends Entity {
	boolean up, down, left, right;
	private int connectionID;
	private String username;
	private int map;
	private PlayerClass playerClass;
	
	public Player(PlayerData data){
		super(data.connectionID, new Vector2f(data.x, data.y), 32, 32, data.dir, data.imageLoc);
		this.setConnectionID(data.connectionID);
		this.setUsername(data.username);
		this.setMap(data.map);
		this.setPlayerClass(data.playerClass);
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);
		g.fillRect(getX(), getY(), width, height);
		if(playerClass != null){
			g.drawString(playerClass.name, 10, 10);
		}
	}
	
	public void update(int delta) {
		
	}
	
	// TODO collisions
	public boolean clearLocation(float nx, float ny) {
		return true;
	}

	public int getConnectionID() {
		return connectionID;
	}

	public void setConnectionID(int connectionID) {
		this.connectionID = connectionID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getMap() {
		return map;
	}
	
	public void setMap(int map) {
		this.map = map;
	}

	public void setPlayerClass(PlayerClass playerClass) {
		this.playerClass = playerClass;
	}

	public PlayerClass getPlayerClass() {
		return playerClass;
	}
}
