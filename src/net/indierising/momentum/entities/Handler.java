package net.indierising.momentum.entities;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class Handler {
	public static ArrayList<Player> players = new ArrayList<Player>();
	
	public static void render(Graphics g){
		for(int i = 0; i < players.size(); i++){
			players.get(i).render(g);
		}
	}
}
