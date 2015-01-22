package net.indierising.momentum.client.network;

import net.indierising.momentum.client.Globals;
import net.indierising.momentum.client.Play;
import net.indierising.momentum.client.entities.EntityHandler;
import net.indierising.momentum.client.entities.Player;
import net.indierising.momentum.client.network.Packets.ChatMessage;
import net.indierising.momentum.client.network.Packets.ConstantsPacket;
import net.indierising.momentum.client.network.Packets.NPCMove;
import net.indierising.momentum.client.network.Packets.NPCPacket;
import net.indierising.momentum.client.network.Packets.PlayerMapChange;
import net.indierising.momentum.client.network.Packets.PlayerMove;
import net.indierising.momentum.client.network.Packets.PlayerPacket;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class Reciever extends Listener {
	public void connected(Connection con) {}
	
	public void received(Connection con, Object obj) {
		// constants
		if(obj instanceof ConstantsPacket) {
			Globals.TILE_SIZE = ((ConstantsPacket) obj).TILE_SIZE;
			Globals.MAX_MAPS = ((ConstantsPacket) obj).MAX_MAPS;
			Globals.MAX_MAP_NPCS = ((ConstantsPacket) obj).MAX_MAP_NPCS;
			
			Player.WIDTH = ((ConstantsPacket) obj).PLAYER_WIDTH;
			Player.HEIGHT = ((ConstantsPacket) obj).PLAYER_HEIGHT;
			
			Globals.DIR_UP = ((ConstantsPacket) obj).DIR_UP;
			Globals.DIR_DOWN = ((ConstantsPacket) obj).DIR_DOWN;
			Globals.DIR_LEFT = ((ConstantsPacket) obj).DIR_LEFT;
			Globals.DIR_RIGHT = ((ConstantsPacket) obj).DIR_RIGHT;
			
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
			
			// center the camera on them
			if(connectionID==Globals.connectionID) Play.camera.centerOn(x, y);
		}
		
		// npcs
		if(obj instanceof NPCPacket){
			EntityHandler.addNPC((NPCPacket) obj);
		}
		
		// npc movement
		if(obj instanceof NPCMove){
			EntityHandler.moveNPC((NPCMove) obj);
		}
		
		if(obj instanceof ChatMessage){
			ChatMessage packet = (ChatMessage) obj;
			Globals.chat.add(packet);
		}
		
		// players changing maps
		if(obj instanceof PlayerMapChange) {
			int conID = ((PlayerMapChange) obj).playerID;
			EntityHandler.getPlayerByID(conID).setMap(((PlayerMapChange) obj).mapID);
		}
	}

	public void disconnected(Connection con) {}
}
