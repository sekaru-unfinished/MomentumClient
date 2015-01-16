package net.indierising.momentum.client.utils;

import java.util.ArrayList;

import net.indierising.momentum.client.network.Packets.ChatMessage;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Chat {
	public int CHAT_SIZE;
	public ArrayList<String> data = new ArrayList<String>();

	public Chat(int CHAT_SIZE){
		this.CHAT_SIZE = CHAT_SIZE;
		// fill our chat data with nothing to avoid nulls
		for(int i = 0; i < CHAT_SIZE; i++){
			data.add("");
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < data.size(); i++){
			g.setColor(new Color(1f,1f,1f,(float)i/8));
			g.drawString(data.get(i),18,500+(i*15));
		}
	}
	
	public void add(ChatMessage packet){
		if(data.size() > CHAT_SIZE){
			data.remove(0);
		}
		data.add(packet.title + " " + packet.message);
	}
}
