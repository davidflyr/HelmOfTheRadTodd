package com.davidflyr.radtodd.graphics;

public class Sprite {

	public final int SIZE;
	private int width, height;
	private int x, y;
	public int[] pixels;
	protected SpriteSheet sheet;
	
	public static Sprite grass      = new Sprite(16, 0, 6, SpriteSheet.tiles);
	public static Sprite flower     = new Sprite(16, 14, 2, SpriteSheet.tiles);
	public static Sprite rock       = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x1B87E0);
	
	// Spawn Level Sprites:
	public static Sprite spawn_grass     = new Sprite(16, 0, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_stonewall = new Sprite(16, 1, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_leaves    = new Sprite(16, 2, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_hardwood  = new Sprite(16, 0, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_sand      = new Sprite(16, 1, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_stones    = new Sprite(16, 2, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_bigbricks = new Sprite(16, 0, 2, SpriteSheet.spawn_level);
	public static Sprite spawn_snow      = new Sprite(16, 1, 2, SpriteSheet.spawn_level);
	public static Sprite spawn_water     = new Sprite(16, 2, 2, SpriteSheet.spawn_level);
	
	
	// Player Sprites:
	public static Sprite player_forward   = new Sprite(32, 1, 7, SpriteSheet.tiles);
	public static Sprite player_back      = new Sprite(32, 0, 7, SpriteSheet.tiles);
	public static Sprite player_side      = new Sprite(32, 2, 7, SpriteSheet.tiles);
	
	public static Sprite player_forward_1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_forward_2 = new Sprite(32, 1, 5, SpriteSheet.tiles);
	
	public static Sprite player_back_1    = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_back_2    = new Sprite(32, 0, 5, SpriteSheet.tiles);
	
	public static Sprite player_side_1    = new Sprite(32, 2, 6, SpriteSheet.tiles);
	public static Sprite player_side_2    = new Sprite(32, 2, 5, SpriteSheet.tiles);
	
	// Projectile Sprites:
	public static Sprite projectile_wizard = new Sprite(16, 0, 0, SpriteSheet.projectile_wizard);
	
	// Particle Sprites:
	public static Sprite particle_normal = new Sprite(2, 0xAAAAAA);
	
	
	protected Sprite(SpriteSheet sheet, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int width, int height, int color) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColor(color);
	}
	
	public Sprite(int size, int color) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE*SIZE];
		setColor(color);
	}
	
	public Sprite(int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	/*private void setColor(int color) {
		for (int i = 0; i < SIZE*SIZE; i++) {
			pixels[i] = color;
		}
	}*/
	
	private void setColor(int color) {
		for (int i = 0; i < width*height; i++) {
			pixels[i] = color;
		}
	}
	
	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y*SIZE] = sheet.pixels[(x + this.x) + (y + this.y)*sheet.SIZE];
			}
		}
	}
	
}