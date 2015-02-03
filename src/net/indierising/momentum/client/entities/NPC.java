package net.indierising.momentum.client.entities;

import net.indierising.momentum.client.Globals;
import net.indierising.momentum.client.Play;
import net.indierising.momentum.client.entitydata.NPCData;
import net.indierising.momentum.client.gui.GUI;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class NPC extends Entity {
	private String name;
	private int map;
	private float damage, health;
	private Animation sprite[] = new Animation[4];
	
	public NPC(NPCData data){
		super(data.id, new Vector2f(data.x, data.y), data.width, data.height, data.dir, data.imageLoc);
		this.setName(data.name);
		this.setMap(data.map);
		this.setHealth(data.health);
		this.setDamage(data.damage);
	}
	
	public void loadImage() {
		try {
			super.loadImage();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		if(getImage() != null) {
			// up 
			if(sprite[Globals.DIR_UP] == null) {
				sprite[Globals.DIR_UP] = new Animation(new Image[] {
						getImage().getSubImage(0, 0), getImage().getSubImage(1, 0),
						getImage().getSubImage(2, 0), getImage().getSubImage(3, 0)}, 4);
			}
			
			// down
			if(sprite[Globals.DIR_DOWN] == null) {
				sprite[Globals.DIR_DOWN] = new Animation(new Image[] {
						getImage().getSubImage(0, 1), getImage().getSubImage(1, 1),
						getImage().getSubImage(2, 1), getImage().getSubImage(3, 1)}, 4);
			}
			
			// left
			if(sprite[Globals.DIR_LEFT] == null) {
				sprite[Globals.DIR_LEFT] = new Animation(new Image[] {
						getImage().getSubImage(0, 2), getImage().getSubImage(1, 2),
						getImage().getSubImage(2, 2), getImage().getSubImage(3, 2)}, 4);
			}
			
			// right
			if(sprite[Globals.DIR_RIGHT] == null) {
				sprite[Globals.DIR_RIGHT] = new Animation(new Image[] {
						getImage().getSubImage(0, 3), getImage().getSubImage(1, 3),
						getImage().getSubImage(2, 3), getImage().getSubImage(3, 3)}, 4);
			}
			
			// set the speeds
			for(int i=0; i<sprite.length; i++) {
				sprite[i].setSpeed(0.015f);
			}
		}
	}
	
	public void render(Graphics g){
		// sprite
		if(getImage() != null) {
			sprite[getDir()].draw(getX(), getY());
		}
	}
	
	public void renderName(GUI gui) {
		// name
		float x = getX() + getWidth()/2 - gui.font.getWidth(getName())/2;
		float y = getY() - 15;
		
		gui.font.drawString(x - Play.camera.x, y - Play.camera.y, getName());
	}
	
	public void update(GameContainer gc, int delta) {
		// update the sprite
		if(getImage()!=null) {
			sprite[getDir()].update(delta);
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setMap(int map) {
		this.map = map;
	}

	public int getMap() {
		return map;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public float getDamage() {
		return damage;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public float getHealth() {
		return health;
	}
}
