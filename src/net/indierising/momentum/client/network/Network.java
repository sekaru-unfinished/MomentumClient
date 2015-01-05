package net.indierising.momentum.client.network;

import java.io.IOException;
import java.util.ArrayList;

import net.indierising.momentum.client.network.Packets.EntityPacket;
import net.indierising.momentum.client.network.Packets.Key;
import net.indierising.momentum.client.network.Packets.PlayerPacket;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.EndPoint;

public class Network {
	public static String IP = "localhost";
	public static int TCP_PORT = 9000, UDP_PORT = 9001, TIMEOUT = 5000;
	
	public static Client client;
	
	public Network(String IP, int TCP_PORT,int UDP_PORT) throws IOException {
		client = new Client();
		client.start();
		
		// set the network variables
		Network.IP = IP;
		Network.TCP_PORT = TCP_PORT;
		Network.UDP_PORT = UDP_PORT;
		
		// register all our classes for the network - must be the same on client and server
		register(client);
		
		client.addListener(new Reciever());
		client.connect(TIMEOUT, IP, TCP_PORT, UDP_PORT);
	}
	
	public static void register(EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		

		// register the classes we'll be transferring
		kryo.register(Key.class);
		kryo.register(ArrayList.class);
		
		kryo.register(PlayerPacket.class);
		kryo.register(EntityPacket.class);
	}
	
	
}
