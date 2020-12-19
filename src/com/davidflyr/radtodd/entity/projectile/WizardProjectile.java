package com.davidflyr.radtodd.entity.projectile;

import com.davidflyr.radtodd.entity.spawner.ParticleSpawner;
import com.davidflyr.radtodd.graphics.Screen;
import com.davidflyr.radtodd.graphics.Sprite;

public class WizardProjectile extends Projectile {
	
	public static final int FIRE_RATE = 10;

	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 170;
		speed = 4;
		damage = 20;
		sprite = Sprite.projectile_wizard;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public void update() {
		if (level.tileCollision((int)(x + nx), (int)(y + ny), 6, 3, 5)) {
			level.add(new ParticleSpawner((int)x, (int)y, 20, 50, level));
			remove();
		}
		move();
	}
	
	protected void move() {
		x += nx;
		y += ny;
		
		if (distance() > range) remove();
	}
	
	private double distance() {
		double dist = 0;
		dist = Math.sqrt((xOrigin - x)*(xOrigin - x) + (yOrigin - y)*(yOrigin - y));
		
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int)x - 8, (int)y - 5, this);
	}
	

}