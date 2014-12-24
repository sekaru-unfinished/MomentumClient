package net.indierising.momentum.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network {
	public static String ip = "localhost";
	public static int TCP_PORT = 9000, UDP_PORT = 9001;
	
	static public void register (EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		
	}
}
