package net.indierising.momentum;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame{
	static AppGameContainer app;
		
	public Game(){
		super("Test");
	}
	
	public void init(GameContainer arg0) throws SlickException {
		
	}

	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		
	}

	public void update(GameContainer arg0, int arg1) throws SlickException {
		
	}
	
	public static void main(String args[]) throws SlickException{
		 app = new AppGameContainer(new Game());
	     app.setShowFPS(false);
	     app.setDisplayMode(1280, 720, false);
	     app.setTargetFrameRate(60);
	     app.setMaximumLogicUpdateInterval(10);
		 app.setMaximumLogicUpdateInterval(60);
	     app.setAlwaysRender(true);
	     app.start();
	}
}