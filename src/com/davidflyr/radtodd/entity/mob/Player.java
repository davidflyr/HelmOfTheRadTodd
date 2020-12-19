package com.davidflyr.radtodd.entity.mob;

import com.davidflyr.radtodd.Game;
import com.davidflyr.radtodd.entity.projectile.Projectile;
import com.davidflyr.radtodd.entity.projectile.WizardProjectile;
import com.davidflyr.radtodd.graphics.AnimatedSprite;
import com.davidflyr.radtodd.graphics.Screen;
import com.davidflyr.radtodd.graphics.SpriteSheet;
import com.davidflyr.radtodd.input.Keyboard;
import com.davidflyr.radtodd.input.Mouse;

public class Player extends Mob {
	
	private Keyboard input;
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite side = new AnimatedSprite(SpriteSheet.player_side, 32, 32, 3);

	private AnimatedSprite animSprite = null;
	
	private boolean walking = false;
	
	private int fireRate = 0;
	private int speed = 2;
	
	public Player(Keyboard input) {
		this.input = input;
		animSprite = down;
	}
	
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		animSprite = down;
		fireRate = WizardProjectile.FIRE_RATE;
	}
	
	public void update() {
		if (walking) animSprite.update();
		else animSprite.setFrame(2);
		
		int xa = 0, ya = 0;
		
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
			walking = true;
		} else {
			walking = false;
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
			fireRate = WizardProjectile.FIRE_RATE;
		}
		
	}

	public void render(Screen screen) {
		int flip = 0;
		
		if (dir == 3) flip = 1;
		
		screen.renderMob(x - 16, y - 16, animSprite.getSprite(), flip);
	}
}