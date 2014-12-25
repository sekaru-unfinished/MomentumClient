package net.indierising.momentum.network;

import net.indierising.momentum.entities.GameObject;
import net.indierising.momentum.entities.Handler;


import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class Reciever extends Listener {
	public void connected(Connection con) {}
	
	public void received(Connection con, Object obj) {
		if(obj instanceof GameObject) {
			GameObject packet = (GameObject) obj;
			
			// check if we have it already
			if(!Handler.gameObjects.contains(packet)) {
				Handler.describe(packet.properties);
				// add our game object to our list, TODO add instance objects instead of singular datatypes.
				Handler.gameObjects.add(packet); 
			}			
		}
	}
	
	public void disconnected(Connection con) {}
}
