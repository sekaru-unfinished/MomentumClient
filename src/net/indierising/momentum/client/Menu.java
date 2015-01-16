package net.indierising.momentum.client;

import net.indierising.momentum.client.gui.GUI;
import net.indierising.momentum.client.gui.Label;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {
	// gui
	GUI gui;
	Label play;
	
	public Menu() {}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// gui
		AngelCodeFont font = new AngelCodeFont("data/assets/fonts/font.fnt", "data/assets/fonts/font.png");
		gui = new GUI(font);
		
		// labels
		play = new Label("Play", gui);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(new Color(150, 150, 150));
		
		// labels
		play.render(20, 20, true, gc);
	}
	
	public void mouseReleased(int button, int x, int y) {
		// left mouse button
		if(button==0) {
			// labels
			if(play.isOver) {
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
