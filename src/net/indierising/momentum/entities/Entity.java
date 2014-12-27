package net.indierising.momentum.entities;

public class Entity {
	private float x;
	private float y;
	int width,height;
	
	public Entity(float x,float y,int width,int height){
		this.setX(x);
		this.setY(y);
		this.width = width;
		this.height = height;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
