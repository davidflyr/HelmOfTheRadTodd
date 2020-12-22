package com.davidflyr.radtodd.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.davidflyr.radtodd.entity.mob.Chaser;
import com.davidflyr.radtodd.entity.mob.Dummy;
import com.davidflyr.radtodd.util.Vector2i;

public class SpawnLevel extends Level {
	
	public SpawnLevel(String path) {
		super(path);
	}
	
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Whoops! Couldn't load level file.");
		}
		add(new Dummy(24, 26));
		add(new Chaser(22, 23));
	}
	
	public static Vector2i dummySpawn() {
		Random random = new Random();
		int pick = random.nextInt(5);
		
		switch (pick) {
		case 0:
			return new Vector2i(24, 26);
		case 1:
			return new Vector2i(20, 13);
		case 2:
			return new Vector2i(13, 35);
		case 3:
			return new Vector2i(26, 48);
		case 4:
			return new Vector2i(19, 64);
		}
		return null;
	}
}