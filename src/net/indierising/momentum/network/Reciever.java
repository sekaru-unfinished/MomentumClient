package net.indierising.momentum.network;

import java.io.IOException;

import net.indierising.momentum.Globals;
import net.indierising.momentum.entities.Handler;
import net.indierising.momentum.network.Packets.EntityPacket;
import net.indierising.momentum.network.Packets.PlayerPacket;

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
		if(obj instanceof EntityPacket){
			Handler.addNPC((EntityPacket) obj);
		}
	}

	public void disconnected(Connection con) {}
}
