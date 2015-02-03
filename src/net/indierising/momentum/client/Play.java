package net.indierising.momentum.client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState {
	public static Network network;
	public static boolean doInitMaps; // maps need to be inited as part of the gameloop
	public GUI gui;
	public static Camera camera;
	
	 // lighting
    private Image lightsImage;
    private Graphics lightsGraphics;
    public static ArrayList<Light> lights = new ArrayList<Light>();
	
	public Play() {}
	 
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// initialise our chat
		Globals.chat = new Chat(10);
		
		// gui
		AngelCodeFont font = new AngelCodeFont("data/assets/fonts/font_small.fnt", "data/assets/fonts/font_small.png");
		gui = new GUI(font);
		gui.textboxes.add(new Textbox(gc, gui, new Vector2f(16, gc.getHeight()-38), 500, 140, Color.white, Color.black));
		
		// add acceptable keys do this somewhere else eventually
		Globals.allowedKeys.add(Keyboard.KEY_W);
		Globals.allowedKeys.add(Keyboard.KEY_A);
		Globals.allowedKeys.add(Keyboard.KEY_S);
		Globals.allowedKeys.add(Keyboard.KEY_D);
		Globals.allowedKeys.add(Keyboard.KEY_SPACE);
		
		// lights
        lightsImage = Image.createOffscreenImage(gc.getWidth(), gc.getHeight());
        lightsGraphics = lightsImage.getGraphics();
        
        // temporary, just to show off :)
        lights.add(new Light(18*32, 3*32, 0.3f, 0.2f, "white_c"));
        lights.add(new Light(17*32, 3*32, 0.5f, 0.2f, "magenta_c"));
        lights.add(new Light(19*32, 3*32, 0.5f, 0.2f, "magenta_c"));
        lights.add(new Light(18*32, 4*32, 0.5f, 0.2f, "cyan_c"));
        lights.add(new Light(18*32, 2*32, 0.6f, 0.2f, "yellow_c"));
        
        lights.add(new Light(10*32, 6*32, 1f, 0.5f, "magenta_c"));
        lights.add(new Light(16*32, 14*32, 1f, 0.5f, "yellow_c"));
        lights.add(new Light(28*32, 11*32, 1f, 0.5f, "cyan_c"));
        
        lights.add(new Light(21*32, 21*32, 1, 0.2f, "white_c"));
	}
	
	public static void tryConnect() {
		TagReader config = new TagReader(new File("data/config.txt"));
		
		try {
			// load the ports and ip from the config file
			int tcp_port = Integer.parseInt(config.findData("tcp_port"));
			int udp_port = Integer.parseInt(config.findData("udp_port"));
			
			// start the client with parsed data
			network = new Network(config.findData("ip"), tcp_port, udp_port);
		} catch (IOException e) {
			enterMenu = true;
			e.printStackTrace();
		}
		
		PlayerPacket packet = new PlayerPacket();
		packet.data = new PlayerData();
		packet.data.username = Globals.username;
		Network.client.sendTCP(packet);
	}
	
	Color lighting;
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if(Globals.connectionID!=-1) {
			if(Globals.mapsInited) {
				 // set the brightness outside lit-up areas
		        int brightness = Integer.valueOf(camera.map.getMapProperty("brightness", "75"));
		        lighting = new Color(brightness, brightness, brightness);
		        
		        // start lighting
		        Graphics.setCurrent(lightsGraphics);
		        lightsGraphics.setBackground(lighting);
		        lightsGraphics.clear();
		        GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);

		        for(int i = 0; i < lights.size(); i++) {
		            lights.get(i).render();
		        }

		        lightsGraphics.setDrawMode(Graphics.MODE_NORMAL);
		        lightsGraphics.flush();
		        Graphics.setCurrent(g);
		        
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
				
				// end lighting
		        GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_SRC_COLOR);
		        lightsImage.draw();
		        g.setDrawMode(Graphics.MODE_NORMAL);
			}
			
			// gui
			if(gui!=null) {
				// names
				EntityHandler.renderNames(gui);
				
				// chat
				Globals.chat.render(gui);
				
				// textboxes
				for(int i=0; i<gui.textboxes.size(); i++) {
					gui.textboxes.get(i).render(g);
				}
			}
		} else {
			g.drawString("Connecting...", 50, 50);
		}
	}
	
	private boolean checkKey(int key){
		for(int i = 0; i < Globals.allowedKeys.size(); i++){
			if(key == Globals.allowedKeys.get(i)){
				return true;
			}
		}
		return false;
	}
	
	public void keyPressed(int key,char c) {
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
				if(!messagePacket.message.equals("")){
					Network.client.sendTCP(messagePacket);
					gui.textboxes.get(0).text = "";
					gui.textboxes.get(0).unFocus();
				}
			}
			if(key == Keyboard.KEY_ESCAPE){
				gui.textboxes.get(0).unFocus();
			}
		}
	}
	
	public void keyReleased(int key, char c) {
		if(!gui.anyTextboxesFocused()) {
			if(checkKey(key)) { // checks keys that send release code
				Key packet = new Key();
				packet.key = key;
				packet.pressed = false;
				Network.client.sendUDP(packet);
			}
		} else {
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

	public static boolean enterMenu;
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// going back to the main menu
		if(enterMenu) {
			sbg.enterState(Game.MENU);
			enterMenu = false;
		}
		
		// init the maps
		if(doInitMaps && EntityHandler.getPlayerByID(Globals.connectionID) != null) {
			try {
				Globals.initMaps();
			} catch (SlickException e) {
				e.printStackTrace();
			}
			doInitMaps = false;
			
			// init the camera
			camera = new Camera(gc, Globals.maps.get(EntityHandler.getPlayerByID(Globals.connectionID).getMap()));
			Play.camera.centerOn(EntityHandler.getPlayerByID(Globals.connectionID).getX(), EntityHandler.getPlayerByID(Globals.connectionID).getY());
		}
		
		// update entities
		if(Globals.connectionID!=-1) {
			EntityHandler.update(gc, delta);
		}
	}

	public int getID() {
		return Game.PLAY;
	}
}