package net.indierising.momentum.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class Reciever extends Listener{
	public void connected (Connection connection) {}
	
	public void received (Connection connection,Object object) {}
	
	public void disconnected (Connection connection){}
}
