package net.indierising.momentum.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class MovingEntity extends Entity{
	int id;
	float speed;
	int direction;
	String imageLocation;
	public MovingEntity(int id,float x,float y,int width,int height,float speed,int direction,String imageLocation){
		super(x,y,width,height);
		this.id = id;
		this.speed = speed;
		this.direction = direction;
		this.imageLocation = imageLocation;
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);
		g.drawRect(getX(), getY(), width, height);
	}
	
	public void update(int delta){
		
	}
}
