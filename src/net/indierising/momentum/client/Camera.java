package net.indierising.momentum.client;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.tiled.TiledMap;

public class Camera {

    // the map
	public TiledMap map;
	public GameContainer gc;
   
    // the number of tiles
    protected int numTilesX;
    protected int numTilesY;
   
    // map size
    protected int mapHeight;
    protected int mapWidth;
   
    // tile size
    protected int tileWidth;
    protected int tileHeight;

    // camera position
    public float x;
    public float y;
    
    // layer lists
    int doorsLayer, animatedLayer;
    private ArrayList<Integer> fringeLayers = new ArrayList<Integer>();
    private ArrayList<Integer> unrenderableLayers = new ArrayList<Integer>();

	public Camera(GameContainer gc, TiledMap map) {
		this.gc = gc;
	    this.map = map;
	      
	    this.numTilesX = map.getWidth();
	    this.numTilesY = map.getHeight();
	      
	    this.tileWidth = map.getTileWidth();
	    this.tileHeight = map.getTileHeight();
	      
	    this.mapWidth = this.numTilesX * this.tileWidth;
	    this.mapHeight = this.numTilesY * this.tileHeight;
	      
	    // fringe layers
	    int fringeLayer = map.getLayerIndex("Fringe"); 
	    if(fringeLayer==-1) fringeLayer = map.getLayerIndex("Fringe1");
	    int fringeLayer2 = map.getLayerIndex("Fringe2"); // todo come up with a better system
	    int fringeLayer3 = map.getLayerIndex("Fringe3");
	    
	    animatedLayer = map.getLayerIndex("Animated");
	    int attributesLayer = map.getLayerIndex("Attributes");
        int lightingLayer = map.getLayerIndex("Lighting");
        
        // fringe layers
        fringeLayers.add(fringeLayer);
        fringeLayers.add(fringeLayer2);
        fringeLayers.add(fringeLayer3);
        
        // unrenderable layers
        unrenderableLayers.add(attributesLayer);
        unrenderableLayers.add(lightingLayer);
	}
   
    // locks the camera on the given coordinates
    public void centerOn(float x, float y) {
	    // try to set the given position as center of the camera by default
	    this.x = x - gc.getWidth() / 2;
	    this.y = y - gc.getHeight() / 2;
	  
	    // if the camera is at the right or left edge lock it to prevent a black bar
	    if(this.x < 0) this.x = 0;
	    if(this.x + gc.getWidth() > mapWidth) this.x = mapWidth - gc.getWidth();
	  
	    // if the camera is at the top or bottom edge lock it to prevent a black bar
	    if(this.y < 0) this.y = 0;
	    if(this.y + gc.getHeight() > mapHeight) this.y = mapHeight - gc.getHeight();
    }
   
    // locks the camera onto a rectangle
    public void centerOn(float x, float y, float width, float height) {
       this.centerOn(x + width / 2, y + height / 2);
    }
   
    // draw the map
    public void drawMap() {
    	this.drawMap(0, 0);
    }

    public void drawMap(int offsetX, int offsetY) {
	    // calculate the offset to the next tile
	    int tileOffsetX = (int) - (x % tileWidth);
	    int tileOffsetY = (int) - (y % tileHeight);
	   
	    // calculate the index of the leftmost tile that is being displayed
	    int tileIndexX = (int) (x / tileWidth);
	    int tileIndexY = (int) (y / tileHeight);
	   
	    // draw the section of the map on the screen
	    for(int i=0; i<map.getLayerCount(); i++) {
		    if(!unrenderableLayers.contains(i) && !fringeLayers.contains(i)) {
	    		map.render(tileOffsetX+offsetX, tileOffsetY+offsetY, tileIndexX, tileIndexY, (gc.getWidth()-tileOffsetX)/tileWidth+1,
	    		(gc.getHeight()-tileOffsetY)/tileHeight+1, i, false);
		    }
	    }
    }

    // draw the fringe
    public void drawFringe() {
    	this.drawFringe(0, 0);
    }
    
    public void drawFringe(int offsetX, int offsetY) {
        // calculate the offset to the next tile
        int tileOffsetX = (int) - (x % tileWidth);
        int tileOffsetY = (int) - (y % tileHeight);
       
        // calculate the index of the leftmost tile that is being displayed
        int tileIndexX = (int) (x / tileWidth);
        int tileIndexY = (int) (y / tileHeight);
       
        // draw the section of the map on the screen
        for(int i=0; i<map.getLayerCount(); i++) {
    	    if(fringeLayers.contains(i)) {
    	    	map.render(tileOffsetX+offsetX, tileOffsetY+offsetY, tileIndexX, tileIndexY, (gc.getWidth()-tileOffsetX)/tileWidth+1,
    	    	(gc.getHeight()-tileOffsetY)/tileHeight+1, i, false);
    	    }
        }
    }
   
    public void translateGraphics() {
    	gc.getGraphics().translate((int)-x, (int)-y);
    }

    public void untranslateGraphics() {
    	gc.getGraphics().translate((int)x,(int) y);
    }
}
