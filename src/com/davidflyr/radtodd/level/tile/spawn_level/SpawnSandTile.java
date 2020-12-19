package com.davidflyr.radtodd.level.tile.spawn_level;

import com.davidflyr.radtodd.graphics.Screen;
import com.davidflyr.radtodd.graphics.Sprite;
import com.davidflyr.radtodd.level.tile.Tile;

public class SpawnSandTile extends Tile {

	public SpawnSandTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}