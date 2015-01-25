package net.indierising.momentum.client.entities;

import net.indierising.momentum.client.Globals;
import net.indierising.momentum.client.Play;
import net.indierising.momentum.client.entitydata.PlayerData;
import net.indierising.momentum.client.network.Packets.PlayerClass;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Player extends Entity {
	public static float WIDTH, HEIGHT;
	
	boolean up, down, left, right;
	private int connectionID;
	private String username;
	private int map; public String mapName;
	private PlayerClass playerClass;
	private Animation sprite[] = new Animation[4];
	
	public Player(PlayerData data){
		super(data.connectionID, new Vector2f(data.x, data.y), WIDTH, HEIGHT, data.dir, data.imageLoc);
		this.setConnectionID(data.connectionID);
		this.setUsername(data.username);
		this.setMap(data.map);
		this.setPlayerClass(data.playerClass);
	}
	
	public void loadImage() {
		try {
			super.loadImage();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		if(getImage() != null) {
			// up 
			if(sprite[Globals.DIR_UP] == null) {
				sprite[Globals.DIR_UP] = new Animation(new Image[] {
						getImage().getSubImage(0, 0), getImage().getSubImage(1, 0),
						getImage().getSubImage(2, 0), getImage().getSubImage(3, 0)}, 4);
			}
			
			// down
			if(sprite[Globals.DIR_DOWN] == null) {
				sprite[Globals.DIR_DOWN] = new Animation(new Image[] {
						getImage().getSubImage(0, 1), getImage().getSubImage(1, 1),
						getImage().getSubImage(2, 1), getImage().getSubImage(3, 1)}, 4);
			}
			
			// left
			if(sprite[Globals.DIR_LEFT] == null) {
				sprite[Globals.DIR_LEFT] = new Animation(new Image[] {
						getImage().getSubImage(0, 2), getImage().getSubImage(1, 2),
						getImage().getSubImage(2, 2), getImage().getSubImage(3, 2)}, 4);
			}
			
			// right
			if(sprite[Globals.DIR_RIGHT] == null) {
				sprite[Globals.DIR_RIGHT] = new Animation(new Image[] {
						getImage().getSubImage(0, 3), getImage().getSubImage(1, 3),
						getImage().getSubImage(2, 3), getImage().getSubImage(3, 3)}, 4);
			}
			
			// set the speeds
			for(int i=0; i<sprite.length; i++) {
				sprite[i].setSpeed(0.03f);
				sprite[i].stop();
			}
		}
	}

	public void render(Graphics g){
		g.setColor(Color.white);
		
		// sprite
		if(getImage() != null) {
			sprite[getDir()].draw(getX(), getY());
		}
		
		if(playerClass != null){
			g.drawString(playerClass.name, 10, 10);
		}
	}
	
	public void renderName(Graphics g) {
		// name
		g.drawString(getUsername(), getX() - Play.camera.x, getY()-10 - Play.camera.y);
	}
	
	public void update(GameContainer gc, int delta) {
		Input input = gc.getInput();
		
		// check if any movement keys are down
		if(getImage()!=null) {
			sprite[getDir()].update(delta);
			if(input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_D)) {
				sprite[getDir()].start();
			} else {
				sprite[getDir()].stop();
				sprite[getDir()].setCurrentFrame(0);
			}
		}
	}
	
	public int getConnectionID() {
		return connectionID;
	}

	public void setConnectionID(int connectionID) {
		this.connectionID = connectionID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getMap() {
		return map;
	}
	
	public void setMap(int map) {
		this.map = map;
	}

	public void setPlayerClass(PlayerClass playerClass) {
		this.playerClass = playerClass;
	}

	public PlayerClass getPlayerClass() {
		return playerClass;
	}
}
