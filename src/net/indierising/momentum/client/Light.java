package net.indierising.momentum.client;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Light {
	public float x, y;
	float size, baseAlpha;
	Image lightImage;

	public Light(float x, float y, float size, float baseAlpha, String image) {
		this.x = x;
		this.y = y;
		this.size = size;
        this.baseAlpha = baseAlpha;
		initSprite(image);
	}
	
	public void initSprite(String image) {
		try {
			lightImage = new Image("data/assets/lights/" + image + ".png");

			for(int i=0; i<4; i++) {
				lightImage.setColor(i, 1f, 1f, 1f, baseAlpha);
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void render() {
		float width = 1f*(lightImage.getWidth()*size);
		float height = 1f*(lightImage.getHeight()*size);
		
		lightImage.startUse();
		lightImage.drawEmbedded(x-Play.camera.x-width/2+Globals.TILE_SIZE/2, y-Play.camera.y-height/2+Globals.TILE_SIZE/2, width, height);
		lightImage.endUse();
	}
	
	public void renderNoCentre() {
		float width = 1f*(lightImage.getWidth()*size);
		float height = 1f*(lightImage.getHeight()*size);
		
		lightImage.startUse();
		lightImage.drawEmbedded(x-Play.camera.x, y-Play.camera.y, width, height);
		lightImage.endUse();
	}
	
	public void update(int delta) {}
}
