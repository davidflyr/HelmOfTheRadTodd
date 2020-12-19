package com.davidflyr.radtodd.level.tile;

import com.davidflyr.radtodd.graphics.Screen;
import com.davidflyr.radtodd.graphics.Sprite;
import com.davidflyr.radtodd.level.tile.spawn_level.SpawnGrassTile;
import com.davidflyr.radtodd.level.tile.spawn_level.SpawnHardwoodTile;
import com.davidflyr.radtodd.level.tile.spawn_level.SpawnLeavesTile;
import com.davidflyr.radtodd.level.tile.spawn_level.SpawnSandTile;
import com.davidflyr.radtodd.level.tile.spawn_level.SpawnSnowTile;
import com.davidflyr.radtodd.level.tile.spawn_level.SpawnStonesTile;
import com.davidflyr.radtodd.level.tile.spawn_level.SpawnWallTile;
import com.davidflyr.radtodd.level.tile.spawn_level.SpawnWaterTile;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile grass    = new GrassTile(Sprite.grass);
	public static Tile flower   = new FlowerTile(Sprite.flower);
	public static Tile rock     = new RockTile(Sprite.rock);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);

	// Spawn Level Tiles:
	public static Tile spawn_grass     = new SpawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_stonewall = new SpawnWallTile(Sprite.spawn_stonewall);
	public static Tile spawn_leaves    = new SpawnLeavesTile(Sprite.spawn_leaves);
	public static Tile spawn_hardwood  = new SpawnHardwoodTile(Sprite.spawn_hardwood);
	public static Tile spawn_sand      = new SpawnSandTile(Sprite.spawn_sand);
	public static Tile spawn_stones    = new SpawnStonesTile(Sprite.spawn_stones);
	public static Tile spawn_bigbricks = new SpawnWallTile(Sprite.spawn_bigbricks);
	public static Tile spawn_snow      = new SpawnSnowTile(Sprite.spawn_snow);
	public static Tile spawn_water     = new SpawnWaterTile(Sprite.spawn_water);
	
	// Spawn Level Pixel Colors
	public static final int col_spawn_grass     = 0xff00FF00;
	public static final int col_spawn_stonewall = 0xff7F7F7F;
	public static final int col_spawn_leaves    = 0;  //unused.
	public static final int col_spawn_hardwood  = 0xff664625;
	public static final int col_spawn_sand      = 0;  //unused.
	public static final int col_spawn_stones    = 0;  //unused.
	public static final int col_spawn_bigbricks = 0xff2F2F2F;
	public static final int col_spawn_snow      = 0;  //unused.
	public static final int col_spawn_water     = 0;  //unused.
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		
	}
	
	public boolean solid() {
		return false;
	}

}
