package net.indierising.momentum.network;

import net.indierising.momentum.entities.Handler;
import net.indierising.momentum.network.Network.EntityPacket;
import net.indierising.momentum.network.Network.PlayerPacket;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class Reciever extends Listener {
	public void connected(Connection con) {}
	
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
