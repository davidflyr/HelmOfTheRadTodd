package com.davidflyr.radtodd.entity.spawner;

import com.davidflyr.radtodd.entity.particle.Particle;
import com.davidflyr.radtodd.level.Level;

public class ParticleSpawner extends Spawner {

	public ParticleSpawner(int x, int y, int life, int amount, Level level) {
		super(x, y, Type.PARTICLE, amount, level);
		for (int i = 0; i < amount; i++)
			level.add(new Particle(x, y, life));
	}

}