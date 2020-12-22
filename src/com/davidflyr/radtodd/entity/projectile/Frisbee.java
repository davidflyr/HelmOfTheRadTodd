package com.davidflyr.radtodd.entity.projectile;

import java.util.List;

import com.davidflyr.radtodd.entity.Entity;
import com.davidflyr.radtodd.entity.mob.Chaser;
import com.davidflyr.radtodd.entity.mob.Dummy;
import com.davidflyr.radtodd.entity.spawner.ParticleSpawner;
import com.davidflyr.radtodd.graphics.AnimatedSprite;
import com.davidflyr.radtodd.graphics.Screen;
import com.davidflyr.radtodd.graphics.SpriteSheet;

public class Frisbee extends Projectile {
	
	public static final int FIRE_RATE = 13;
	
	private AnimatedSprite frisbee_anim = new AnimatedSprite(SpriteSheet.frisbee_anim, 16, 16, 4, 5);
	private AnimatedSprite animSprite = null;
	
	public Frisbee(double x, double y, double dir) {
		super(x, y, dir);
		range = 1000;
		speed = 3;
		damage = 20;
		animSprite = frisbee_anim;
		sprite = animSprite.getSprite();
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public void update() {
		List<Entity> entities = level.getEntities(this, 12);
		
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			entity.getHit();
			if (entity instanceof Chaser || entity instanceof Dummy) remove();
		}
		
		if (level.tileCollision((int)(x + nx), (int)(y + ny), 6, 3, 5)) {
			level.add(new ParticleSpawner((int)x, (int)y, 20, 50, level));
			remove();
		}
		
		animSprite.update();
		sprite = animSprite.getSprite();
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
