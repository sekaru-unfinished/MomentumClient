package net.indierising.momentum.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Random;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

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
	
	public static ArrayList<String> chat = new ArrayList<String>();
	public static Random random = new Random();
	
	public static void downloadData() throws IOException {
		URL dataFile = new URL(DATA_URL);
		ReadableByteChannel rbc = Channels.newChannel(dataFile.openStream());
		FileOutputStream fos = new FileOutputStream(DATA_DIR);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		
	    // unzip it
	    unZip(DATA_DIR, System.getProperty("user.dir") + "/data/");
	}
	
	// unzipping .zip files
	public static void unZip(String fileName, String outputFolder) throws IOException{

	    try {
	         ZipFile zipFile = new ZipFile(fileName);
	         zipFile.extractAll(outputFolder);
	    } catch (ZipException e) {
	        e.printStackTrace();
	    }
	    
	   File file = new File(fileName);
	   file.delete();
    }
	
	// maps
	public static void initMaps() throws SlickException {
		maps.clear();
		
		for(int i=0; i<MAX_MAPS; i++) {
			maps.add(new TiledMap("data/maps/map" + (i+1) + ".tmx"));
		}
		
		mapsInited = true;
	}
}
