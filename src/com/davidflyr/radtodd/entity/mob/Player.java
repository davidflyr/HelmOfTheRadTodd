package com.davidflyr.radtodd.entity.mob;

import com.davidflyr.radtodd.Game;
import com.davidflyr.radtodd.entity.projectile.Frisbee;
import com.davidflyr.radtodd.entity.projectile.Projectile;
import com.davidflyr.radtodd.graphics.AnimatedSprite;
import com.davidflyr.radtodd.graphics.Screen;
import com.davidflyr.radtodd.graphics.SpriteSheet;
import com.davidflyr.radtodd.input.Keyboard;
import com.davidflyr.radtodd.input.Mouse;

public class Player extends Mob {
	
	private Keyboard input;
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 6, 10);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 6, 10);
	private AnimatedSprite side = new AnimatedSprite(SpriteSheet.player_side, 32, 32, 6, 10);

	private AnimatedSprite animSprite = down;
	
	private int fireRate = 0;
	private double speed = 2.0;
	
	private double prevX = 0;
	private double prevY = speed;
	
	public Player(Keyboard input) {
		this.input = input;
		animSprite = down;
	}
	
	public Player(double x, double y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		animSprite = down;
		fireRate = Frisbee.FIRE_RATE;
	}
	
	public void update() {
		System.out.println(this.x);
		
		animSprite.update();
		
		double xa = 0, ya = 0;
		
		if (fireRate > 0) fireRate--;
		
		if (input.left) {
			animSprite = side;
			xa -= speed;
		}
		if (input.right) {
			animSprite = side;
			xa += speed;
		}
		if (input.up) {
			animSprite = up;
			ya -= speed;
		}
		if (input.down) {
			animSprite = down;
			ya += speed;
		}
		
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			prevX = xa;
			prevY = ya;
		} else {
			move(prevX, prevY);
		}
		clear();
		updateShooting();
	}
	
	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) level.getProjectiles().remove(i);
		}
		
	}

	private void updateShooting() {
		if (Mouse.getButton() == 1 && fireRate <= 0) {
			double dx = Mouse.getX() - Game.getWindowWidth()/2;
			double dy = Mouse.getY() - Game.getWindowHeight()/2;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = Frisbee.FIRE_RATE;
		}
		
	}

	public void render(Screen screen) {
		int flip = 0;
		
		if (dir == Direction.LEFT) flip = 1;
		
		screen.renderMob((int)x - 16, (int)y - 16, animSprite.getSprite(), flip);
	}
}