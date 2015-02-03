package net.indierising.momentum.client.entities;

import java.util.ArrayList;

import net.indierising.momentum.client.Globals;
import net.indierising.momentum.client.gui.GUI;
import net.indierising.momentum.client.network.Packets.NPCMove;
import net.indierising.momentum.client.network.Packets.NPCPacket;
import net.indierising.momentum.client.network.Packets.PlayerPacket;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class EntityHandler {
	public static ArrayList<Player> players = new ArrayList<Player>();
	public static ArrayList<NPC> npcs = new ArrayList<NPC>();
	
	public static void render(Graphics g) {
		// players
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getMap()==getPlayerByID(Globals.connectionID).getMap()) {
				players.get(i).render(g);
			}
		}
		
		// npcs
		for(int i = 0; i < npcs.size(); i++) {
			if(npcs.get(i).getMap()==getPlayerByID(Globals.connectionID).getMap()) {
				npcs.get(i).render(g);
			}
		}
		
		// load the images
		try {
			loadEntityImages();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static void renderNames(GUI gui) {
		// players
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getMap()==getPlayerByID(Globals.connectionID).getMap()) {
				players.get(i).renderName(gui);
			}
		}
		
		// npcs
		for(int i = 0; i < npcs.size(); i++) {
			if(npcs.get(i).getMap()==getPlayerByID(Globals.connectionID).getMap()) {
				npcs.get(i).renderName(gui);
			}
		}
	}
	
	public static Player getPlayerByID(int connectionID){
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).getConnectionID() == connectionID) {
				return players.get(i);
			}
		}
		
		// if we can't find them
		return null;
	}
	
	public static void update(GameContainer gc, int delta) {
		// players
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getMap()==getPlayerByID(Globals.connectionID).getMap()) {
				players.get(i).update(gc, delta);
			}
		}
		
		// npcs
		for(int i = 0; i < npcs.size(); i++) {
			if(npcs.get(i).getMap()==getPlayerByID(Globals.connectionID).getMap()) {
				npcs.get(i).update(gc, delta);
			}
		}
	}
	
	public static void addPlayer(PlayerPacket packet) {
		players.add(new Player(packet.data));
	}
	
	public static void addNPC(NPCPacket packet) {
		npcs.add(new NPC(packet.data));
	}
	
	public static NPC getNPCByID(int id){
		for(int i = 0; i < npcs.size(); i++){
			if(npcs.get(i).id == id) {
				return npcs.get(i);
			}
		}
		
		// if we can't find them
		return null;
	}
	
	public static void moveNPC(NPCMove packet) {
		if(getNPCByID(packet.id)!=null) {
			getNPCByID(packet.id).setX(packet.x);
			getNPCByID(packet.id).setY(packet.y);
		}
	}
	
	private static void loadEntityImages() throws SlickException{
		for(int i = 0; i < npcs.size(); i++) {
			npcs.get(i).loadImage();
		}
		
		for(int i = 0; i < players.size(); i++) {
			players.get(i).loadImage();
		}
	}
}
