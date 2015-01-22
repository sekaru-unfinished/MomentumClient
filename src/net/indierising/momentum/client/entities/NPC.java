package net.indierising.momentum.client.entities;

import net.indierising.momentum.client.Globals;
import net.indierising.momentum.client.entitydata.NPCData;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class NPC extends Entity{
	private int damage,health;
	private String name;
	private Animation sprite[] = new Animation[4];
	
	public NPC(NPCData data){
		super(data.id, new Vector2f(data.x, data.y), data.width, data.height, data.dir, data.imageLoc);
		this.setHealth(health);
		this.setDamage(damage);
		this.setName(name);
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
				sprite[i].setSpeed(0.03f);
			}
		}
	}
	
	public void render(Graphics g){
		// sprite
		if(getImage() != null) {
			sprite[getDir()].draw(getX(), getY());
		}
		
		// name
		if(name != null){
			g.drawString(name, getX(), getY());
		}
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


}
