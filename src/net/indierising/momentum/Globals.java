package net.indierising.momentum;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class Globals {
	public static String DATA_PACKAGE_NAME = "ClientData.zip";
	public static String DATA_URL = "http://www.indierising.net/momentum/" + DATA_PACKAGE_NAME;
	public static String DATA_DIR = "data/" + DATA_PACKAGE_NAME;
	
	public static void downloadData() throws IOException {
		URL dataFile = new URL(DATA_URL);
		ReadableByteChannel rbc = Channels.newChannel(dataFile.openStream());
		FileOutputStream fos = new FileOutputStream(DATA_DIR);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		
	    // unzip it
	    unZip(DATA_DIR, System.getProperty("user.dir") + "/data/");
	}
	
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
}
