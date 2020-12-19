package com.davidflyr.radtodd.entity;

import java.util.Random;

import com.davidflyr.radtodd.graphics.Screen;
import com.davidflyr.radtodd.level.Level;

public class Entity {
	
	public int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update() {
		
	};
	
	public void render(Screen screen) {
		
	}
	
	public void remove() {
		// Remove from level
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}

}