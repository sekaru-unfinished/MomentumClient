package net.indierising.momentum;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame{
	static AppGameContainer app;
	static int width=800,height=600;
		
	public static void main(String args[]) throws SlickException{
		 app = new AppGameContainer(new Game());
	     app.setShowFPS(false);
	     app.setDisplayMode(width, height, false);
	     app.setTargetFrameRate(60);
	     app.setMaximumLogicUpdateInterval(10);
		 app.setMaximumLogicUpdateInterval(60);
	     app.setAlwaysRender(true);
	     app.start();
	}
	
	public Game(){
		super("Test");
	}

	public void keyReleased(int key,char c){
		
	}	
	
	public void mouseMoved(int oldx,int oldy,int newx,int newy){

	}

	public void mousePressed(int button, int x, int y) {

	}

	public void mouseDragged(int oldx, int oldy, int newx, int newy){

	}
	
	public void mouseReleased(int button, int x,int y){

	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
}