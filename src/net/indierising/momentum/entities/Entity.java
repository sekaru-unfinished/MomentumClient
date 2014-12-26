package net.indierising.momentum.entities;

import org.newdawn.slick.geom.Vector2f;

public class Entity {
	private Vector2f pos;
	float width, height;
	
	public Entity(Vector2f pos, float width, float height) {
		this.pos = pos;
		this.width = width;
		this.height = height;
	}

	public float getX() {
		return pos.getX();
	}

	public void setX(float x) {
		pos.set(x, pos.getY());
	}

	public float getY() {
		return pos.getY();
	}

	public void setY(float y) {
		pos.set(pos.getX(), y);
	}
}
