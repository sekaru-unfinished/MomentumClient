package net.indierising.momentum.client.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public class Entity {
	int id;
	private Vector2f pos;
	float width, height;
	int dir;
	private String imageLoc;
	private SpriteSheet image;
	
	public Entity(int id, Vector2f pos, float width, float height, int dir, String imageLoc){
		this.pos = pos;
		this.width = width;
		this.height = height;
		this.id = id;
		this.dir = dir;
		this.setImageLoc(imageLoc);
	}
	
	public void loadImage() throws SlickException {
		if(image == null){
			image = new SpriteSheet(new Image("data/assets/sprites/" + imageLoc + ".png"), (int) width, (int) height);
		}
	}
	
	public int getID() {
		return id;
	}
	
	public String getImageLoc() {
		return imageLoc;
	}

	public void setImageLoc(String location) {
		this.imageLoc = location;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
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

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(getX(), getY(), width, height);
		if(image != null){
			g.drawImage(image, getX(), getY());
		}
	}
	
	public void update(int delta) {
		setX(getX() + 0.3f);
	}

	public SpriteSheet getImage() {
		return image;
	}
}
