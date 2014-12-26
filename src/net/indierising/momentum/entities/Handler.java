package net.indierising.momentum.entities;

import java.util.ArrayList;

import net.indierising.momentum.network.Network.PlayerPacket;

import org.newdawn.slick.Graphics;

public class Handler {
	public static ArrayList<Player> players = new ArrayList<Player>();
	
	public static void render(Graphics g){
		for(int i = 0; i < players.size(); i++){
			players.get(i).render(g);
		}
	}
	
	public static void update(int delta){
		for(int i = 0; i < players.size(); i++){
			players.get(i).update(delta);
		}
	}
	
	public static Player getPlayerByID(int connectionID){
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).getConnectionID() == connectionID){
				return players.get(i);
			}
		}
		// if we can't find them sorry.
		return null;
	}
	
	public static void addPlayer(PlayerPacket packet){
		if(getPlayerByID(packet.connectionID) == null){
			System.out.println(packet.imageLocation);
			players.add(new Player(packet.connectionID,packet.username,packet.x,packet.y,packet.speed,packet.direction,packet.imageLocation));
		}else{
			getPlayerByID(packet.connectionID).setX(packet.x);
			getPlayerByID(packet.connectionID).setY(packet.y);
		}
	}
}
