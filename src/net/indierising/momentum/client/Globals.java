package net.indierising.momentum.client;

import java.util.ArrayList;
import net.indierising.momentum.client.utils.Chat;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Globals {
	public static String username = "mali";
	public static int connectionID = -1;
	
	// maps stuff
	public static int TILE_SIZE;
	public static int MAX_MAPS;
	public static int MAX_MAP_NPCS;
	public static ArrayList<TiledMap> maps = new ArrayList<TiledMap>();
	public static boolean mapsInited;
	
	// used for checking which keys can be sent
	public static ArrayList<Integer> allowedKeys = new ArrayList<Integer>();
	
	// directional constants
	public static int DIR_UP;
	public static int DIR_DOWN;
	public static int DIR_LEFT;
	public static int DIR_RIGHT;
	
	// our chatbox
	public static Chat chat;
	
	// maps
	public static void initMaps() throws SlickException {
		maps.clear();
		
		for(int i=0; i<MAX_MAPS; i++) {
			maps.add(new TiledMap("data/maps/map" + (i+1) + ".tmx"));
		}
		
		mapsInited = true;
	}
}
