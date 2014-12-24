package net.indierising.momentum.entities;

import java.util.ArrayList;

public class Handler {
	public static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	public static void describe(ArrayList<Property> properties){
		for(int i = 0; i < properties.size(); i++){
			System.out.print(properties.get(i).getName() + " -- " + properties.get(i).getData() + "  --  ");
			System.out.println(properties.get(i).getData().getClass().getName());
			System.out.println("----");
		}
	}
}
