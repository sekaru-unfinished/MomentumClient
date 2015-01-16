package net.indierising.momentum.client.gui;

import java.util.ArrayList;

import org.newdawn.slick.AngelCodeFont;

public class GUI {
	AngelCodeFont font;
	
	// textboxes
	public ArrayList<Textbox> textboxes = new ArrayList<Textbox>();
	
	public GUI(AngelCodeFont font) {
		this.font = font;
	}
	
	// textboxes selected
	public boolean anyTextboxesFocused() {
		for(int i=0; i<textboxes.size(); i++) {
			if(textboxes.get(i).isFocused()) {
				return true;
			}
		}
		
		return false;
	}
	
	// deselect textboxes
	public void deselectTextboxes() {
		for(int i=0; i<textboxes.size(); i++) {
			if(textboxes.get(i).isFocused()) {
				textboxes.get(i).unFocus();
			}
		}
	}
}
