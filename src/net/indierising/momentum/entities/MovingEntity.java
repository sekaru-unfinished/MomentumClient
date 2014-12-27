package net.indierising.momentum.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class MovingEntity extends Entity{
	int id;
	float speed;
	int direction;
	String imageLocation;
	public MovingEntity(int id,float x,float y,int width,int height,float speed,int direction,String imageLocation){
		super(new Vector2f(x,y),width,height);
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
