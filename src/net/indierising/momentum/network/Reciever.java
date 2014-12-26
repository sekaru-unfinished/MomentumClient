package net.indierising.momentum.network;

import net.indierising.momentum.entities.Handler;
import net.indierising.momentum.entities.Player;
import net.indierising.momentum.network.Network.PlayerPacket;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class Reciever extends Listener {
	public void connected(Connection con) {}
	
	public void received(Connection con, Object obj) {
		if(obj instanceof PlayerPacket){
			PlayerPacket packet = (PlayerPacket) obj;
			Handler.players.add(new Player(packet.connectionID,packet.x,packet.y,packet.direction));
			System.out.println("recieved player" + packet.connectionID);
		}
	}

	public void disconnected(Connection con) {}
}
