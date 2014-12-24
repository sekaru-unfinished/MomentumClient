package net.indierising.momentum.network;

import net.indierising.momentum.entities.GameObject;
import net.indierising.momentum.entities.Handler;


import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class Reciever extends Listener{
	public void connected (Connection connection) {}
	
	public void received (Connection connection,final Object object) {
		if(object instanceof GameObject){
			GameObject packet = (GameObject) object;
			// check if we have it already
			if(!Handler.gameObjects.contains(packet)){
				Handler.describe(packet.properties);
				Handler.gameObjects.add(packet);// add our game object to our list, TODO add instance objects instead of singular datatypes.
			}			
		}
	}
	
	public void disconnected (Connection connection){}
}
