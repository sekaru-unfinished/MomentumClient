package net.indierising.momentum.client;

import java.io.File;
import java.io.IOException;

import net.indierising.momentum.client.entities.EntityHandler;
import net.indierising.momentum.client.entitydata.PlayerData;
import net.indierising.momentum.client.gui.GUI;
import net.indierising.momentum.client.gui.Textbox;
import net.indierising.momentum.client.network.Network;
import net.indierising.momentum.client.network.Packets.ChatMessage;
import net.indierising.momentum.client.network.Packets.Key;
import net.indierising.momentum.client.network.Packets.PlayerPacket;
import net.indierising.momentum.client.utils.Chat;
import net.indierising.momentum.client.utils.TagReader;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState {
	Network network;
	public static boolean doInitMaps; // maps need to be inited as part of the gameloop
	public GUI gui;
	public static Camera camera;
	
	public Play(int stateID) {}
	 
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		TagReader config = null;
		try {
			config = new TagReader(new File("data/config.txt"));
			config.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			// load the ports and ip from the config file
			int tcp_port = Integer.parseInt(config.findData("tcp_port"));
			int udp_port = Integer.parseInt(config.findData("udp_port"));
			
			// start the client with parsed data
			network = new Network(config.findData("ip"), tcp_port, udp_port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PlayerPacket packet = new PlayerPacket();
		packet.data = new PlayerData();
		packet.data.username = Globals.username;
		Network.client.sendTCP(packet);
		
		// initialise our chat
		Globals.chat = new Chat(10);
		
		// gui
		gui = new GUI();
		AngelCodeFont font = new AngelCodeFont("data/assets/fonts/font.fnt", "data/assets/fonts/font.png");
		gui.textboxes.add(new Textbox(gc, font, new Vector2f(16, gc.getHeight()-38), 500, 140, Color.white, Color.black));
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if(Globals.connectionID!=-1) {
			if(Globals.mapsInited) {
				// draw the map
				camera.drawMap();
				camera.translateGraphics();
				
				// render entities
				EntityHandler.render(g);
				
				// draw the fringe layers
				camera.untranslateGraphics();
		        camera.drawFringe();
		        camera.translateGraphics();
				
				// untranslate the map so we can render the gui normally
				camera.untranslateGraphics();
			}
			
			// gui
			Globals.chat.render(g);
			
			if(gui!=null) {
				for(int i=0; i<gui.textboxes.size(); i++) {
					gui.textboxes.get(i).render(g);
				}
			} else {
				g.drawString("Loading...", 50, 50);
			}
		} else {
			g.drawString("Connecting...", 50, 50);
		}
	}
	
	public void keyPressed(int key,char c) {
		// TODO eventually load a list of all keys that can be pressed to avoid clogging the server
		if(!gui.anyTextboxesFocused()) {
			Key packet = new Key();
			packet.key = key;
			packet.pressed = true;
			Network.client.sendUDP(packet);
		} else {
			// if there are textboxes selected TODO add a check to see if its the chat box
			if(key == Keyboard.KEY_RETURN){
				ChatMessage messagePacket = new ChatMessage();
				// assign the first textbox in the array, ROUGH JOB
				messagePacket.message = gui.textboxes.get(0).text;
				Network.client.sendUDP(messagePacket);
				gui.textboxes.get(0).text = "";
				gui.textboxes.get(0).unFocus();
			}
		}
		
	
	}
	
	public void keyReleased(int key, char c) {
		if(!gui.anyTextboxesFocused()) {
			Key packet = new Key();
			packet.key = key;
			packet.pressed = false;
			Network.client.sendUDP(packet);
		}
		
		// textboxes
		for(int i=0; i<gui.textboxes.size(); i++) {
			if(gui.textboxes.get(i).isFocused()) {
				// check the key
				switch(key) {
				case Input.KEY_BACK:
					gui.textboxes.get(i).delChar();
					break;
				case Input.KEY_RETURN:
					gui.textboxes.get(i).unFocus();
					break;
				default:
					gui.textboxes.get(i).addChar(c);
				}
			}
		}
	}
	
	public void mouseReleased(int button, int x, int y) {
		// check for textboxes
		for(int i=0; i<gui.textboxes.size(); i++) {
			if(gui.textboxes.get(i).textboxArea.isMouseOver()) {
				gui.textboxes.get(i).setFocus();
			}
		}
		
		// no textboxes selected? must've tried to deselect
		if(!gui.anyTextboxesFocused()) {
			gui.deselectTextboxes();
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// find their id
		if(Globals.connectionID==-1 && EntityHandler.players.size()>0) {
			for(int i=0; i<EntityHandler.players.size(); i++) {
				if(EntityHandler.players.get(i).getUsername().equals(Globals.username)) {
					Globals.connectionID = EntityHandler.players.get(i).getConnectionID();
					break;
				}
			}
		}
		
		// init the maps
		if(doInitMaps) {
			try {
				Globals.initMaps();
			} catch (SlickException e) {
				e.printStackTrace();
			}
			
			doInitMaps = false;
			
			// init the camera
			camera = new Camera(gc, Globals.maps.get(EntityHandler.getPlayerByID(Globals.connectionID).getMap()));
		} 
	}

	public int getID() {
		return Game.PLAY;
	}
}