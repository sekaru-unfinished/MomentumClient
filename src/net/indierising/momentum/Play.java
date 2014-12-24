package net.indierising.momentum;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState{
	// stores the state of the game
	int stateID = 0;
	
	public Play(int stateID){
		this.stateID = stateID;
	}
	 
	public int getID() {
		return stateID;
	}
	
	public void init(GameContainer gc,StateBasedGame sc) throws SlickException {
		
	}

	public void render(GameContainer gc,StateBasedGame sc, Graphics g) throws SlickException {
		
	}

	public void update(GameContainer gc,StateBasedGame sc, int delta) throws SlickException {
		
	}
}