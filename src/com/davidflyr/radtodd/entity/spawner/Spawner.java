package com.davidflyr.radtodd.entity.spawner;

import com.davidflyr.radtodd.entity.Entity;
import com.davidflyr.radtodd.level.Level;

public abstract class Spawner extends Entity {
	
	public enum Type {
		MOB, PARTICLE
	}
	
	private Type type;
	
	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
}