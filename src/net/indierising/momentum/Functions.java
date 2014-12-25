package net.indierising.momentum;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import net.indierising.momentum.entities.GameObject;
import net.indierising.momentum.entities.Property;

/**
 * This class holds all functions relating to game objects. If you want a new method for a game object, here is where you would put it.
 */
public class Functions {
	// CORE FUNCTIONS
	
	// gets the property name
	public static Property findProperty(GameObject object,String propertyTitle) {
		for(int i = 0; i < object.properties.size(); i++) {
			if(object.properties.get(i).getName().equals(propertyTitle)) {
				// we have a match!
				return object.properties.get(i);
			}
		}
		// if we can't find any then there is something messed up with the object being sent
		return null;
	}
	
	// sets the property data to what we want, used for image loading.
	public static void setProperty(GameObject object,String propertyTitle,Object data) {
		for(int i = 0; i < object.properties.size(); i++) {
			if(object.properties.get(i).getName().equals(propertyTitle)) {
				object.properties.get(i).setData(data);
			}
		}
	}
	
	public static float getX(GameObject object) {
		return (float) findProperty(object, "x").getData();
	}
	
	public static float getY(GameObject object) {
		return (float) findProperty(object, "y").getData();
	}
	
	public static void loadImage(GameObject object) throws SlickException {
		if(findProperty(object,"render").getData() instanceof String) {
			// if the render property is currently a string, convert it to an image
			String location = (String) findProperty(object, "render").getData();
			setProperty(object,"render",new Image(location));
		}
	}
	
	public static void render(Graphics g, GameObject object) {
		if(findProperty(object,"render").getData() instanceof Image) {
			// if its an image, render that shit!
			// so many casts, sorry.
			g.drawImage((Image)findProperty(object, "render").getData(), (int) getX(object), (int) getY(object));
		}
		
		// little test function to see everything working
		if(findProperty(object,"con-id") != null) {
			g.drawString("" + findProperty(object, "con-id").getData(), (int) getX(object), (int) getY(object));
		}
	}
	
	
}
