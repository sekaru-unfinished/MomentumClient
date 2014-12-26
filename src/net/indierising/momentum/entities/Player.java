package net.indierising.momentum.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Player extends MovingEntity{
	
	boolean up,down,left,right;
	private int connectionID;
	private String username;
	
	// only entites that need the directions get it.
	public Player(int connectionID,String username,float x,float y,float speed,int direction){
		// set our player up with the speed and a width and height of 32.
		super(x,y,32,32,speed,direction);
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
