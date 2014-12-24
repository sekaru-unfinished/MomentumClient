package net.indierising.momentum.network;

import org.newdawn.slick.SlickException;

import net.indierising.momentum.Functions;
import net.indierising.momentum.entities.GameObject;
import net.indierising.momentum.entities.Handler;
import net.indierising.momentum.entities.Property;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class Reciever extends Listener{
	public void connected (Connection connection) {}
	
	public void received (Connection connection,final Object object) {
		if(object instanceof GameObject){
			GameObject packet = (GameObject) object;
			// if we recieve an object which has an image, load the imaeg
			Handler.gameObjects.add(packet);// add our game object to our list, TODO add instance objects instead of singular datatypes.
		}
	}
	
	public void disconnected (Connection connection){}
}
