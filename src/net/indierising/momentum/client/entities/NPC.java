package net.indierising.momentum.client.entities;

import net.indierising.momentum.client.entitydata.NPCData;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class NPC extends Entity{
	private int damage,health;
	private String name;
	private Animation leftAnim,rightAnim,upAnim,downAnim;
	
	public NPC(NPCData data){
		super(data.id, new Vector2f(data.x, data.y), data.width, data.height, data.dir, data.imageLoc);
		this.setHealth(health);
		this.setDamage(damage);
		this.setName(name);
	}
	
	public void loadImage(){
		try {
			super.loadImage();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		if(getImage() != null){
			if(downAnim == null){
				downAnim = new Animation(new Image[] {
						getImage().getSubImage(0, 0), getImage().getSubImage(1, 0),
						getImage().getSubImage(2, 0),getImage().getSubImage(3, 0) }, 4);
			}
			if(leftAnim == null){
				leftAnim = new Animation(new Image[] {
						getImage().getSubImage(0, 1), getImage().getSubImage(1, 1),
						getImage().getSubImage(2, 1),getImage().getSubImage(3, 1) }, 4);
			}
			if(rightAnim == null){
				rightAnim = new Animation(new Image[] {
						getImage().getSubImage(0, 2), getImage().getSubImage(1, 2),
						getImage().getSubImage(2, 2),getImage().getSubImage(3, 2) }, 4);
			}
			if(upAnim == null){
				upAnim = new Animation(new Image[] {
						getImage().getSubImage(0, 3), getImage().getSubImage(1, 3),
						getImage().getSubImage(2, 3),getImage().getSubImage(3, 3) }, 4);
			}
			upAnim.setSpeed(0.03f);
			downAnim.setSpeed(0.03f);
			leftAnim.setSpeed(0.03f);
			rightAnim.setSpeed(0.03f);
			
		}
	}
	
	public void render(Graphics g){
		if(getImage() != null){
			if(getDir() == 0){
				downAnim.start();
				downAnim.draw(getX(),getY());
			}else if(getDir() == 3){
				upAnim.start();
				upAnim.draw(getX(),getY());
			}else if(getDir() == 1){
				leftAnim.start();
				leftAnim.draw(getX(),getY());
			}else if(getDir() == 2){
				rightAnim.start();
				rightAnim.draw(getX(),getY());
			}
		}
		if(name != null){
			g.drawString(name, getX(), getY());
		}
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


}
