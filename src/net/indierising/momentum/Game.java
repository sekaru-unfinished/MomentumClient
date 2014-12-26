package net.indierising.momentum;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class Game extends StateBasedGame {
	public static AppGameContainer app;
	
	// list of states
	public static final int MENU = 0;
    public static final int PLAY = 1;
    
    public final static int WIDTH = 1280;
    public final static int HEIGHT = 720;
	
	public Game() {
		super("Momentum Client");
		this.addState(new Play(MENU));
		this.addState(new Play(PLAY));
        this.enterState(PLAY);
	}

	public static void main(String args[]) throws SlickException {
		 app = new AppGameContainer(new Game());
	     app.setShowFPS(false);
	     app.setDisplayMode(WIDTH, HEIGHT, false);
	     app.setTargetFrameRate(60);
	     app.setMaximumLogicUpdateInterval(10);
		 app.setMaximumLogicUpdateInterval(60);
	     app.setAlwaysRender(true);
	     app.start();
	}

	public void initStatesList(GameContainer gc) throws SlickException {
//		this.getState(MENU).init(gc, this);
		// don't init the state we are going into or else you end up with two connections.
	}
}