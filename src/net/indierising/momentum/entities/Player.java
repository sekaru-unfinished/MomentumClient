package net.indierising.momentum.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends MovingEntity{
	
	boolean up,down,left,right;
	private int connectionID;
	private String username;
	private Image image;
	
	// only entites that need the directions get it.
	public Player(int connectionID,String username,float x,float y,float speed,int direction,String imageLocation){
		// set our player up with the speed and a width and height of 32.
		super(connectionID,x,y,32,32,speed,direction,imageLocation);
		this.setConnectionID(connectionID);
		this.setUsername(username);
	}
	
	public void render(Graphics g){
		// make sure the image we are rendering isn't null.
		if(image != null)	g.drawImage(image,getX()-(width/2),getY()-(height/2));
	}
	
	public void update(int delta){
		loadImage();
	}
	
	// TODO collisions
	public boolean clearLocation(float nx,float ny){
		return true;
	}
	
	// load image function
	public void loadImage(){
		if(image == null){
			try {
				image = new Image(imageLocation);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
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
