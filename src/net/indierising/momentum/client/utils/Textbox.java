package net.indierising.momentum.client.utils;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.MouseOverArea;

public class Textbox {
	private AngelCodeFont font; 
	public String text = "";
	private Vector2f pos;
	private int width, limit;
	private boolean hasFocus;
	private Color boxColour, textColour;
	public MouseOverArea textboxArea;
	
	public Textbox(GameContainer gc, AngelCodeFont font, Vector2f pos, int width, int limit, Color boxColour, Color textColour) {
		this.font = font;
		this.pos = pos;
		this.width = width;
		this.limit = limit;
		this.boxColour = boxColour;
		this.textColour = textColour;
		
		try {
			textboxArea = new MouseOverArea(gc, new Image(width, font.getLineHeight()+4), (int) pos.x, (int) pos.y);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(boxColour);
		g.fillRect(pos.x, pos.y, width+4, font.getLineHeight()+4);
		
		String textToDraw = text;
		if(hasFocus) textToDraw = text + "|";
		font.drawString(pos.x+2, pos.y+2, textToDraw, textColour);
	}
	
	public void addChar(char c) {
		if(text.length()<limit) text += c;
	}
	
	public void delChar() {
		if(text.length()<=0) return;
		text = text.substring(0, text.length()-1);
	}
	
	public void setFocus() {
		this.hasFocus = true;
	}
	
	public boolean isFocused() {
		return hasFocus;
	}
}
