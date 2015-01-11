package net.indierising.momentum.client.gui;

import java.util.ArrayList;

public class GUI {
	// textboxes
	public ArrayList<Textbox> textboxes = new ArrayList<Textbox>();
	
	public GUI() {}
	
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
