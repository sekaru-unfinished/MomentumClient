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
	
	// downloading client side data from an ftp
	public static String DATA_PACKAGE_NAME = "clientdata.zip";
	public static String DATA_URL = "http://www.indierising.net/momentum/" + DATA_PACKAGE_NAME;
	public static String DATA_DIR = "data/" + DATA_PACKAGE_NAME;
	
	// used for checking which keys can be sent
	public static ArrayList<Integer> allowedKeys = new ArrayList<Integer>();
	
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
