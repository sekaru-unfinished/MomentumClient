package net.indierising.momentum.client.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;

public class Label {
	public String text; GUI gui;
	MouseOverArea labelArea; boolean labelAreaSet;
	public boolean isOver;
	
	public Label(String text, GUI gui) {
		text = text.toUpperCase();
		
		this.text = text;
		this.gui = gui;
	}

	int offset;
	public void render(int x, int y, boolean hov, GameContainer gc) {
		// pre-set the label area
		if(labelAreaSet==false) {
			try {
				labelArea = new MouseOverArea(gc, new Image(gui.font.getWidth(text), gui.font.getHeight(text)), x, y);
			} catch (SlickException e) {
				e.printStackTrace();
			}
			labelAreaSet=true;
		}
		
		// reset the label area
		setLabelArea(x, y);
		
		// check for hover
		if(labelArea.isMouseOver()){
			if(!gc.getInput().isMouseButtonDown(0)) {
				isOver = true;
			}
		} else {
			isOver = false;
		}
		
		// render it
		if(isOver && hov) {
			gui.font.drawString(x, y, text, new Color(50, 50, 50));
		} else {
			gui.font.drawString(x, y, text, new Color(250, 250, 250));
		}
	}
	
	public void setLabelArea(int x, int y) {
		labelArea.setX(x);
		labelArea.setY(y);
	}
	
	public int getWidth() {
		return gui.font.getWidth(text);
	}
	
	public int getHeight() {
		return gui.font.getHeight(text);
	}
}

