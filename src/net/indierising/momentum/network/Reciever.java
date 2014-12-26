package net.indierising.momentum.network;

import net.indierising.momentum.entities.Handler;
import net.indierising.momentum.entities.Player;
import net.indierising.momentum.network.Network.PlayerPacket;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class Reciever extends Listener {
	public void connected(Connection con) {}
	
	public void received(Connection con, Object obj) {
		System.out.println(obj.getClass().getName());
		if(obj instanceof PlayerPacket){
			System.out.println("Added player");
			Handler.addPlayer((PlayerPacket) obj);
		}
	}

	public void disconnected(Connection con) {}
}
