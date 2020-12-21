package com.davidflyr.radtodd.entity.mob;

import com.davidflyr.radtodd.graphics.AnimatedSprite;
import com.davidflyr.radtodd.graphics.Screen;
import com.davidflyr.radtodd.graphics.Sprite;
import com.davidflyr.radtodd.graphics.SpriteSheet;
import com.davidflyr.radtodd.level.SpawnLevel;
import com.davidflyr.radtodd.util.Vector2i;

public class Dummy extends Mob {

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 4, 10);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 4, 10);
	private AnimatedSprite side = new AnimatedSprite(SpriteSheet.dummy_side, 32, 32, 4, 10);
	
	private AnimatedSprite animSprite = down;
	private boolean walking = false;
	private boolean alive = true;
	
	private int time = 0;
	private int deathTime;
	private int interval = 70;
	private double xa = 1, ya = 0;
	
	private static int DUMMY_COUNT = 0;
	
	public Dummy(double x, double y) {
		this.x = x * 16;
		this.y = y * 16;
		sprite = Sprite.player_back;
		DUMMY_COUNT++;
	}
	
	public void getHit() {
		System.out.println("Dummy: 'I'm hit!'");
		alive = false;
		deathTime = time + 30;
	}
	
	public void update() {
		time++;
		if (alive) aliveUpdate();
		else deadUpdate();
		
		
	}
	
	private void aliveUpdate() {
		if (walking) animSprite.update();
		else animSprite.setFrame(1);
		
		if (time % interval == 0) { // change direction after interval
			interval = random.nextInt(60) + 40; // pick new interval
			xa = random.nextInt(3) - 1; // change direction
			ya = random.nextInt(3) - 1;
			if (random.nextInt(3) == 0) { // chance of stopping
				xa = 0;
				ya = 0;
			}
			if (xa != 0 && ya != 0) { //no diagonal movement
				if (random.nextInt(2) == 0) {
					xa = 0;
				} else {
					ya = 0;
				}
			}
		}
		
		if (xa < 0) {
			dir = Direction.LEFT;
			animSprite = side;
		}
		if (xa > 0) {
			dir = Direction.RIGHT;
			animSprite = side;
		}
		if (ya < 0) {
			dir = Direction.UP;
			animSprite = up;
		}
		if (ya > 0) {
			dir = Direction.DOWN;
			animSprite = down;
		}
		
		if (xa !=0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		
		sprite = animSprite.getSprite();
	}
	
	private void deadUpdate() {
		sprite = Sprite.dummy_catch;
		if (time >= deathTime) {
			Vector2i newSpawn = SpawnLevel.dummySpawn();
			level.add(new Dummy(newSpawn.getX(), newSpawn.getY()));
			
			if (DUMMY_COUNT < 30) {
				newSpawn = SpawnLevel.dummySpawn();
				level.add(new Dummy(newSpawn.getX(), newSpawn.getY()));
			}
			DUMMY_COUNT--;
			remove();
		}
	}

	public void render(Screen screen) {
		int flip = 0;
		
		if (dir == Direction.LEFT) flip = 1;
		
		screen.renderMob((int)x - 16, (int)y - 16, sprite, flip);
	}

}
