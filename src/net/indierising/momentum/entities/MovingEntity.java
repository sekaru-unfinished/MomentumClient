package net.indierising.momentum.entities;

public class MovingEntity extends Entity{
	float speed;
	int direction;
	public MovingEntity(float x,float y,int width,int height,float speed,int direction){
		super(x,y,width,height);
		this.speed = speed;
		this.direction = direction;
	}
}
