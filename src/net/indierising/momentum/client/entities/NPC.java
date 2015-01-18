package net.indierising.momentum.client.entities;

import net.indierising.momentum.client.entitydata.NPCData;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class NPC extends Entity{
	private int damage,health;
	private String name;
		
	public NPC(NPCData data){
		super(data.id, new Vector2f(data.x, data.y), data.width, data.height, data.dir, data.imageLoc);
		this.setHealth(health);
		this.setDamage(damage);
		this.setName(name);
	}
	
	public void render(Graphics g){
		super.render(g);
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
