package net.indierising.momentum.client.entitydata;

import net.indierising.momentum.client.entities.NPC;

public class NPCData {
	public int id, map;
	public String name;
	public float x, y, speed;
	public int dir;
	public float width, height;
	public String imageLoc;
	public float health, damage;
	
	public NPCData() {}
	
	public NPCData(NPC npc) {
		this.id = npc.getID();
		this.name = npc.getName();
		this.x = npc.getX();
		this.y = npc.getY();
		this.map = npc.getMap();
		this.dir = npc.getDir();
		this.width = npc.getWidth();
		this.height = npc.getHeight();
		this.imageLoc = npc.getImageLoc();
		this.health = npc.getHealth();
		this.damage = npc.getDamage();
	}
	
	public String toString() {
		return "ID: " + id + ", Name: " + name + ", pos: " + x + ", " + y + " on map "  + map;
	}
}
