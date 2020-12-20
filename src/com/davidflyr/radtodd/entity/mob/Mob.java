package com.davidflyr.radtodd.entity.mob;

import com.davidflyr.radtodd.entity.Entity;
import com.davidflyr.radtodd.entity.projectile.Frisbee;
import com.davidflyr.radtodd.entity.projectile.Projectile;
import com.davidflyr.radtodd.graphics.Screen;
import com.davidflyr.radtodd.graphics.Sprite;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected boolean moving = false;
	
	protected enum Direction {
		UP, RIGHT, DOWN, LEFT
	}
	
	protected Direction dir;
	
	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if (xa > 0) dir = Direction.RIGHT;
		if (xa < 0) dir = Direction.LEFT;
		if (ya > 0) dir = Direction.DOWN;
		if (ya < 0) dir = Direction.UP;
		
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
	}
	
	public abstract void update();
	
	public abstract void render(Screen screen);
	
	public void shoot(int x, int y, double dir) {
		//dir *= 180 / Math.PI;
		Projectile p = new Frisbee(x, y, dir);
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