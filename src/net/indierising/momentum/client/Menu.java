package net.indierising.momentum.client;

import net.indierising.momentum.client.gui.GUI;
import net.indierising.momentum.client.gui.Label;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {
	// gui
	GUI gui;
	Label login, register, credits;
	Image logo, bg;
	
	public Menu() {}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// gui
		AngelCodeFont font = new AngelCodeFont("data/assets/fonts/font.fnt", "data/assets/fonts/font.png");
		gui = new GUI(font);
		
		// labels
		login = new Label("Login", gui);
		register = new Label("Register", gui);
		credits = new Label("Credits", gui);
		
		// logo and background
		logo = new Image("data/assets/gui/logo.png");
		bg = new Image("data/assets/gui/background.png");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(new Color(150, 150, 150));
		
		bg.draw();
		logo.draw(gc.getWidth()/2-logo.getWidth()/2, gc.getHeight()/2-logo.getHeight()/2-40);
		
		// labels
		login.render(gc.getWidth()/2-login.getWidth()/2, gc.getHeight()/2 + 40, true, gc);
		register.render(gc.getWidth()/2-register.getWidth()/2, gc.getHeight()/2 + 90, true, gc);
		credits.render(gc.getWidth()/2-credits.getWidth()/2, gc.getHeight()/2 + 140, true, gc);
	}
	
	public void mouseReleased(int button, int x, int y) {
		// left mouse button
		if(button==0) {
			// labels
			if(login.isOver) {
				connect = true;
			}
		}
	}
	
	private boolean connect;
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// connect
		if(connect) {
			sbg.enterState(Game.PLAY);
			Play.tryConnect();
			connect = false;
		}
	}

	public int getID() {
		return Game.MENU;
	}
}
