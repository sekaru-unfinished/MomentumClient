package net.indierising.momentum.client.entities;

import net.indierising.momentum.client.Globals;
import net.indierising.momentum.client.entitydata.PlayerData;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Player extends Entity{
	boolean up,down,left,right;
	private int connectionID;
	private String username;
	
	public Player(PlayerData data){
		// set our player up with the speed and a width and height of TILE_SIZE
		super(data.connectionID, new Vector2f(data.x, data.y), Globals.TILE_SIZE, Globals.TILE_SIZE, data.dir, data.imageLoc);
		this.setConnectionID(connectionID);
		this.setUsername(username);
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);
		g.fillRect(getX(), getY(), width, height);
	}
	
	public void update(int delta){
		
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

}
