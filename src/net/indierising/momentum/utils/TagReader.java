package net.indierising.momentum.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class TagReader {
	InputStream file; Reader reader; BufferedReader in;
	ArrayList<String> lines = new ArrayList<String>();
	
	public TagReader(InputStream file) {
		this.file = file;
		reader = new InputStreamReader(file);
        in = new BufferedReader(reader);
	}
	
	public void read() throws IOException {
		String line;
		
		// read all the lines until the bottom of the file
		while ((line = in.readLine()) != null) {
			lines.add(line);
		}
	}
	
	public String findData(String tag) {
		for(int i=0; i<lines.size(); i++) {
			// check for the tag
			if(lines.get(i).startsWith("<" + tag + ">")) {
				// replace the tag bit with nothing so you're left with just the data
				lines.set(i, lines.get(i).replace("<" + tag + ">", ""));
				
				// return the data
				return lines.get(i);
			}
		}
		
		return null;
	}
}
