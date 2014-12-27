package net.indierising.momentum.entities;

import java.util.ArrayList;

import net.indierising.momentum.network.Packets.EntityPacket;
import net.indierising.momentum.network.Packets.PlayerPacket;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Handler {
	public static ArrayList<Player> players = new ArrayList<Player>();
	// the core sending for npcs.
	public static ArrayList<MovingEntity> npcs = new ArrayList<MovingEntity>();
	
	public static void render(Graphics g){
		for(int i = 0; i < players.size(); i++){
			players.get(i).render(g);
		}
		for(int i = 0; i < npcs.size(); i++){
			npcs.get(i).render(g);
		}
	}
	
	public static Player getPlayerByID(int connectionID){
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).getConnectionID() == connectionID) {
				return players.get(i);
			}
		}
		// if we can't find them sorry.
		return null;
	}
	
	public static void addPlayer(PlayerPacket packet) {
		if(getPlayerByID(packet.connectionID) == null) {
			players.add(new Player(packet.connectionID, packet.username, new Vector2f(packet.x, packet.y), packet.speed, packet.direction,packet.imageLocation));
		} else {
			getPlayerByID(packet.connectionID).setX(packet.x);
			getPlayerByID(packet.connectionID).setY(packet.y);
		}
	}
	
	public static MovingEntity getNPCByID(int id){
		for(int i = 0; i < npcs.size(); i++){
			if(npcs.get(i).id == id) {
				return npcs.get(i);
			}
		}
		// if we can't find them sorry.
		return null;
	}
	
	public static void addNPC(EntityPacket packet) {
		if(getNPCByID(packet.id) == null) {
			npcs.add(new MovingEntity(packet.id,packet.x, packet.y,32,32, packet.speed, packet.direction,packet.imageLocation));
		} else {
			getNPCByID(packet.id).setX(packet.x);
			getNPCByID(packet.id).setY(packet.y);
		}
	}
}
