package com.davidflyr.radtodd.entity.projectile;

import java.util.Random;

import com.davidflyr.radtodd.entity.Entity;
import com.davidflyr.radtodd.graphics.Sprite;

public abstract class Projectile extends Entity {
	
	protected final double xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	protected double distance;
	protected double speed, range, damage;
	
	protected final Random random = new Random();
	
	public Projectile(double x, double y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public int getSpriteSize() {
		return sprite.SIZE;
	}
	
	protected void move() {
		
	}

}
