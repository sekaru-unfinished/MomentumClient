package net.indierising.momentum.entities;

import org.newdawn.slick.Graphics;

public class Player extends MovingEntity{
	
	boolean up,down,left,right;
	private int connectionID;
	
	// only entites that need the directions get it.
	public Player(int connectionID,float x,float y,int direction){
		// set our player up with the speed and a width and height of 32.
		super(x,y,32,32,0.3f,direction);
		this.setConnectionID(connectionID);
	}
	
	public void render(Graphics g){
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

}
