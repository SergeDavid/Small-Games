package knolif.asteroid;

import java.util.List;
import java.util.Random;

import knolif.asteroid.entity.BabyChaserMob;
import knolif.asteroid.entity.ChaserMob;
import knolif.asteroid.entity.Entity;
import knolif.asteroid.entity.ExploderMob;
import knolif.asteroid.entity.HugeMob;
import knolif.asteroid.entity.RandomMob;
import knolif.asteroid.entity.ZipZagMob;

public class SpawnHandler {
	Random rand = new Random();
	public int Objects;
	private int maxObjects;
	private int maxSpawns;
	private Level level;
	
	public SpawnHandler(Level level) {
		this.level = level;
		maxObjects = 30;
		maxSpawns = 20;
	}
	
	public List<Entity> SpawnNormal(List<Entity> e) {
		if (Objects < maxSpawns) {
			if (rand.nextInt(10) == 0) {
				int r = rand.nextInt(100);
				Objects++;
				if (r<14) {e.add(new ChaserMob(level, level.game.player));}
				else if (r<22) {e.add(new HugeMob(level));}
				else if (r<30) {e.add(new ExploderMob(level));}
				else {e.add(new RandomMob(level));}
			}
		}
		return e;
	}
	public List<Entity> SpawnFollowers(List<Entity> e) {
		if (Objects < maxSpawns) {
			if (rand.nextInt(10) == 0) {
				int r = rand.nextInt(100);
				Objects++;
				if (r<66) {e.add(new ChaserMob(level, level.game.player));}
				else {e.add(new ExploderMob(level));}
			}
		}
		return e;
	}
	
	public List<Entity> SpawnBabies(List<Entity> e, float x, float y) {
		for (int a = 0; a < rand.nextInt(5)+1; a++) {
			if (Objects < maxObjects) {
				Objects++;
				e.add(new BabyChaserMob(level, level.game.player, x, y));
			}
		}
		return e;	
	}

	public List<Entity> SpawnRandom(List<Entity> e) {
		if (Objects < maxSpawns) {
			if (rand.nextInt(10) == 0) {
				Objects++;
				e.add(new ZipZagMob(level));
			}
		}
		return e;
	}
}
