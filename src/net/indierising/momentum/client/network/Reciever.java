package net.indierising.momentum.client.network;

import java.io.IOException;

import net.indierising.momentum.client.Globals;
import net.indierising.momentum.client.entities.Handler;
import net.indierising.momentum.client.network.Packets.EntityPacket;
import net.indierising.momentum.client.network.Packets.PlayerMove;
import net.indierising.momentum.client.network.Packets.PlayerPacket;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class Reciever extends Listener {
	public void connected(Connection con) {
		try {
			Globals.downloadData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void received(Connection con, Object obj) {
		if(obj instanceof PlayerPacket){
			Handler.addPlayer((PlayerPacket) obj);
		}
		
		// movement
		if(obj instanceof PlayerMove){
			int connectionID = ((PlayerMove) obj).connectionID;
			float x = ((PlayerMove) obj).x;
			float y = ((PlayerMove) obj).y;
			int dir = ((PlayerMove) obj).dir;
			
			Handler.getPlayerByID(connectionID).setX(x);
			Handler.getPlayerByID(connectionID).setY(y);
			Handler.getPlayerByID(connectionID).setDir(dir);
		}
		
		if(obj instanceof EntityPacket){
			Handler.addNPC((EntityPacket) obj);
		}
	}

	public void disconnected(Connection con) {}
}
