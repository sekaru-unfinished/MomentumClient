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
	
	public static void addPlayer(PlayerPacket packet){
		players.add(new Player(packet.connectionID,packet.username,packet.x,packet.y,packet.direction));
	}
}
