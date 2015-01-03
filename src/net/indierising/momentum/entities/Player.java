package net.indierising.momentum.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Player extends Entity{
	boolean up,down,left,right;
	private int connectionID;
	private String username;
	
	// only entites that need the directions get it.
	public Player(int connectionID, String username, Vector2f pos, float speed, int direction, String imageLoc){
		// set our player up with the speed and a width and height of 32.
		super(connectionID, pos, 32, 32, speed, direction, imageLoc);
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
	public boolean clearLocation(float nx,float ny){
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
