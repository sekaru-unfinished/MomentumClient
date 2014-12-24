package net.indierising.momentum;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.indierising.momentum.entities.GameObject;
import net.indierising.momentum.entities.Handler;
import net.indierising.momentum.entities.Property;
import net.indierising.momentum.network.Network;
import net.indierising.momentum.network.Network.Key;
import net.indierising.momentum.utils.TagReader;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState{
	// stores the state of the game
	int stateID = 0;
	
	// holds our registered classes and the client
	Network network;
	
	public Play(int stateID){
		this.stateID = stateID;
	}
	 
	public int getID() {
		return stateID;
	}
	
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
	}

	public void render(GameContainer gc,StateBasedGame sc, Graphics g) throws SlickException {
		// spaghetti test code can be removed whenever
		g.setColor(Color.white);
		for(int i = 0; i < Handler.gameObjects.size(); i++){
			GameObject gameObject = Handler.gameObjects.get(i);
			// check for render method, load our image if we don't have it yet otherwise the gameobject doesn't have a render method
			if(Functions.findProperty(gameObject, "render") != null){
				try {
					Functions.loadImage(gameObject);
				} catch (SlickException e) {
					e.printStackTrace();
				}
				Functions.render(g,gameObject);
			}
		}
		
		
		
	}
	
	public static void describe(ArrayList<Property> properties){
		for(int i = 0; i < properties.size(); i++){
			System.out.println(properties.get(i).getName() + "--");
			System.out.println(properties.get(i).getData().getClass().getName());
		}
	}

	public void update(GameContainer gc,StateBasedGame sc, int delta) throws SlickException {
		
	}

	public void keyPressed(int key,char c){
		// TODO eventually load a list of all keys that can be pressed to avoid clogging the server
		Key packet = new Key();
		packet.keyCode = key;
		packet.pressed = true;
		Network.client.sendUDP(packet);
	}
	
	public void keyReleased(int key,char c){
		Key packet = new Key();
		packet.keyCode = key;
		packet.pressed = true;
		Network.client.sendUDP(packet);
	}
}