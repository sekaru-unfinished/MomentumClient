package net.indierising.momentum.client.network;

import java.io.IOException;
import java.util.ArrayList;

import net.indierising.momentum.client.entitydata.PlayerData;
import net.indierising.momentum.client.network.Packets.ConstantsPacket;
import net.indierising.momentum.client.network.Packets.EntityPacket;
import net.indierising.momentum.client.network.Packets.Key;
import net.indierising.momentum.client.network.Packets.PlayerMove;
import net.indierising.momentum.client.network.Packets.PlayerPacket;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.EndPoint;

public class Network {
	public static String ip = "localhost";
	public static int tcpPort = 9000, udpPort = 9001, timeOut = 5000;
	
	public static Client client;
	
	public Network(String ip, int tcpPort, int udpPort) throws IOException {
		client = new Client();
		client.start();
		
		// set the network variables
		Network.ip = ip;
		Network.tcpPort = tcpPort;
		Network.udpPort = udpPort;
		
		// register all our classes for the network - must be the same on client and server
		register(client);
		
		client.addListener(new Reciever());
		client.connect(timeOut, ip, tcpPort, udpPort);
	}
	
	public static void register(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		
		// register the classes we'll be transferring
		kryo.register(ConstantsPacket.class);
		kryo.register(Key.class);
		kryo.register(ArrayList.class);
		
		kryo.register(PlayerPacket.class);
		kryo.register(PlayerData.class);
		kryo.register(PlayerMove.class);
		kryo.register(EntityPacket.class);
	}
	
	
}
