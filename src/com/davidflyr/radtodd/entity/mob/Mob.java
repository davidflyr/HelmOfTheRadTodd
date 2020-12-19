package com.davidflyr.radtodd.entity.mob;

import com.davidflyr.radtodd.entity.Entity;
import com.davidflyr.radtodd.entity.projectile.Projectile;
import com.davidflyr.radtodd.entity.projectile.WizardProjectile;
import com.davidflyr.radtodd.graphics.Screen;
import com.davidflyr.radtodd.graphics.Sprite;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	
	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;
		
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
	}
	
	public abstract void update();
	
	public abstract void render(Screen screen);
	
	public void shoot(int x, int y, double dir) {
		//dir *= 180 / Math.PI;
		Projectile p = new WizardProjectile(x, y, dir);
		level.add(p);
	}
	
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 9 - 5)>>4; 	/* Checks center pixel +5 to the right, or -6 to the left */
			int yt = ((y + ya) + c / 2 * 12 - 4)>>4; 	/* Checks center pixel +9 to downwards, or -5 to the upwards */
			if (level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
}