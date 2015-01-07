package net.indierising.momentum.client.network;

import java.io.IOException;

import net.indierising.momentum.client.Globals;
import net.indierising.momentum.client.Play;
import net.indierising.momentum.client.entities.EntityHandler;
import net.indierising.momentum.client.network.Packets.ConstantsPacket;
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
		// constants
		if(obj instanceof ConstantsPacket) {
			Globals.TILE_SIZE = ((ConstantsPacket) obj).TILE_SIZE;
			Globals.MAX_MAPS = ((ConstantsPacket) obj).MAX_MAPS;
			Globals.MAX_MAP_NPCS = ((ConstantsPacket) obj).MAX_MAP_NPCS;
			
			// init the maps
		    Play.doInitMaps = true;
		}
		
		// players
		if(obj instanceof PlayerPacket) {
			EntityHandler.addPlayer((PlayerPacket) obj);
		}
		
		// movement
		if(obj instanceof PlayerMove) {
			int connectionID = ((PlayerMove) obj).connectionID;
			float x = ((PlayerMove) obj).x;
			float y = ((PlayerMove) obj).y;
			int dir = ((PlayerMove) obj).dir;
			
			EntityHandler.getPlayerByID(connectionID).setX(x);
			EntityHandler.getPlayerByID(connectionID).setY(y);
			EntityHandler.getPlayerByID(connectionID).setDir(dir);
		}
		
		if(obj instanceof EntityPacket){
			EntityHandler.addNPC((EntityPacket) obj);
		}
	}

	public void disconnected(Connection con) {}
}
