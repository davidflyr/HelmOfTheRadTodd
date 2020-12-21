package com.davidflyr.radtodd.level;

import java.util.ArrayList;
import java.util.List;

import com.davidflyr.radtodd.entity.Entity;
import com.davidflyr.radtodd.entity.mob.Player;
import com.davidflyr.radtodd.entity.particle.Particle;
import com.davidflyr.radtodd.entity.projectile.Projectile;
import com.davidflyr.radtodd.graphics.Screen;
import com.davidflyr.radtodd.level.tile.Tile;

public class Level {
	
	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	protected int tile_size;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	
	private List<Player> players = new ArrayList<Player>();
	
	public static Level spawn = new SpawnLevel("spawn.png");
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width*height];
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void loadLevel(String path) {
	}

	protected void generateLevel() {
	}
	
	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).update();
		}
		remove();
	}
	
	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if(entities.get(i).isRemoved()) 
				entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if(projectiles.get(i).isRemoved()) 
				projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			if(particles.get(i).isRemoved()) 
				particles.remove(i);
		}
		for (int i = 0; i < players.size(); i++) {
			if(players.get(i).isRemoved()) 
				players.remove(i);
		}
	}
	
	public List<Projectile> getProjectiles(){
		return projectiles;
	}
	
	//private void time() {
		
	//}
	
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * (size) + xOffset) >> 4;
			int yt = (y - c / 2 * (size) + yOffset) >> 4;
			if (getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).render(screen);
		}
	}
	
	public void add(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add((Particle)e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else if (e instanceof Player) {
			players.add((Player) e);
		} else {			
			entities.add(e);
		}
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public Player getPlayerAt(int index) {
		return players.get(index);
	}
	
	public Player getClientPlayer() {
		return players.get(0);
	}
	
	public List<Entity> getEntities(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		double ex = e.getX();
		double ey = e.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			double x = entity.getX();
			double y = entity.getY();
			
			double dx = Math.abs(x - ex);
			double dy = Math.abs(y - ey);
			
			
			double distance = Math.sqrt((dx*dx) + (dy*dy));
			if (distance <= radius) {
				result.add(entity);
			}
		}
		
		return result;
	}
	
	public List<Player> getPlayers(Entity e, int radius) {
		List<Player> result = new ArrayList<Player>();
		double ex = e.getX();
		double ey = e.getY();
		
		for (int i = 0; i < players.size(); i++) {	
			Player player = players.get(i);
			double x = player.getX();
			double y = player.getY();
			
			double dx = Math.abs(x - ex);
			double dy = Math.abs(y - ey);
			
			double distance = Math.sqrt((dx*dx) + (dy*dy));
			if (distance <= radius) result.add(player);
		}
		
		return result;
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y*width] == Tile.col_spawn_grass) return Tile.spawn_grass;
		if (tiles[x + y*width] == Tile.col_spawn_stonewall) return Tile.spawn_stonewall;
		//if (tiles[x + y*width] == Tile.col_spawn_leaves) return Tile.spawn_leaves;
		if (tiles[x + y*width] == Tile.col_spawn_hardwood) return Tile.spawn_hardwood;
		//if (tiles[x + y*width] == Tile.col_spawn_sand) return Tile.spawn_sand;
		//if (tiles[x + y*width] == Tile.col_spawn_stones) return Tile.spawn_stones;
		if (tiles[x + y*width] == Tile.col_spawn_bigbricks) return Tile.spawn_bigbricks;
		//if (tiles[x + y*width] == Tile.col_spawn_snow) return Tile.spawn_snow;
		//if (tiles[x + y*width] == Tile.col_spawn_water) return Tile.spawn_water;
		return Tile.voidTile;
	}

}
