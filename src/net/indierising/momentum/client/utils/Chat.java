package net.indierising.momentum.client.utils;

import java.util.ArrayList;

import net.indierising.momentum.client.gui.GUI;
import net.indierising.momentum.client.network.Packets.ChatMessage;

import org.newdawn.slick.Color;

public class Chat {
	public int chatSize;
	public ArrayList<String> lines = new ArrayList<String>();

	public Chat(int chatSize){
		this.chatSize = chatSize;
		
		// fill our chat data with nothing to avoid nulls
		for(int i = 0; i < chatSize; i++){
			lines.add("");
		}
	}
	
	public void render(GUI gui){
		for(int i = 0; i < lines.size(); i++){
			gui.font.drawString(18, 500+(i*15), lines.get(i), new Color(1f, 1f, 1f,0.1f*i));
		}
	}
	
	public void add(ChatMessage packet){
		if(lines.size() > chatSize){
			lines.remove(0);
		}
		lines.add(packet.title + " " + packet.message);
	}
}
