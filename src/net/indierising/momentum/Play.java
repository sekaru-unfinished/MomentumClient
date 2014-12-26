package net.indierising.momentum;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.indierising.momentum.entities.Handler;
import net.indierising.momentum.network.Network;
import net.indierising.momentum.network.Network.Key;
import net.indierising.momentum.network.Network.PlayerPacket;
import net.indierising.momentum.utils.TagReader;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState {
	public static String username = "mali";
	// holds our registered classes and the client
	Network network;
	
	public Play(int stateID) {}
	 
	public void init(GameContainer gc,StateBasedGame sc) throws SlickException {
		TagReader config = null;
		try {
			config = new TagReader(new FileInputStream("data/config.txt"));
			config.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			// load the ports and ip from the config file
			int tcp_port = Integer.parseInt(config.findData("tcp_port"));
			int udp_port = Integer.parseInt(config.findData("udp_port"));
			
			// start the client with parsed data
			network = new Network(config.findData("ip"),tcp_port,udp_port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PlayerPacket packet = new PlayerPacket();
		packet.username = Play.username;
		Network.client.sendTCP(packet);
	}

	public void render(GameContainer gc,StateBasedGame sc, Graphics g) throws SlickException {
		Handler.render(g);
	}
	
	public void keyPressed(int key,char c) {
		// TODO eventually load a list of all keys that can be pressed to avoid clogging the server
		Key packet = new Key();
		packet.key = key;
		packet.pressed = true;
		Network.client.sendUDP(packet);
	}
	
	public void keyReleased(int key,char c) {
		Key packet = new Key();
		packet.key = key;
		packet.pressed = false;
		Network.client.sendUDP(packet);
	}

	public void update(GameContainer gc,StateBasedGame sc, int delta) throws SlickException {
		
	}

	public int getID() {
		return Game.PLAY;
	}
}