package net.indierising.momentum.client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import net.indierising.momentum.client.entities.EntityHandler;
import net.indierising.momentum.client.entitydata.PlayerData;
import net.indierising.momentum.client.network.Network;
import net.indierising.momentum.client.network.Packets.ChatMessage;
import net.indierising.momentum.client.network.Packets.Key;
import net.indierising.momentum.client.network.Packets.PlayerPacket;
import net.indierising.momentum.client.utils.Chat;
import net.indierising.momentum.client.utils.TagReader;
import net.indierising.momentum.client.utils.Textbox;

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
	ArrayList<Textbox> textboxes = new ArrayList<Textbox>();
	
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
		AngelCodeFont font = new AngelCodeFont("data/assets/fonts/font.fnt", "data/assets/fonts/font.png");
		textboxes.add(new Textbox(gc, font, new Vector2f(50, 100), 500, 140, Color.white, Color.black));
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// draw the map
		if(Globals.connectionID!=-1 && Globals.mapsInited) {
			Globals.maps.get(EntityHandler.getPlayerByID(Globals.connectionID).getMap()).render(0, 0);
		}
		
		// render entities
		EntityHandler.render(g);
		
		Globals.chat.render(g);
		for(int i=0; i<textboxes.size(); i++) {
			textboxes.get(i).render(g);
		}
	}
	
	public void keyPressed(int key,char c) {
		// TODO eventually load a list of all keys that can be pressed to avoid clogging the server
		Key packet = new Key();
		packet.key = key;
		packet.pressed = true;
		Network.client.sendUDP(packet);
		
		if(key == Keyboard.KEY_SPACE){
			ChatMessage messagePacket = new ChatMessage();
			messagePacket.message = "Sending chat.";
			Network.client.sendUDP(messagePacket);
		}
	}
	
	public void keyReleased(int key, char c) {
		Key packet = new Key();
		packet.key = key;
		packet.pressed = false;
		Network.client.sendUDP(packet);
		
		// textboxes
		for(int i=0; i<textboxes.size(); i++) {
			if(textboxes.get(i).isFocused()) {
				// make sure it isnt backspace
				if(key!=Input.KEY_BACK) {
					textboxes.get(i).addChar(c);
				} else {
					textboxes.get(i).delChar();
				}
			}
		}
	}
	
	public void mouseReleased(int button, int x, int y) {
		// check for textboxes
		for(int i=0; i<textboxes.size(); i++) {
			if(textboxes.get(i).textboxArea.isMouseOver()) {
				textboxes.get(i).setFocus();
			}
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
		} 
	}

	public int getID() {
		return Game.PLAY;
	}
}