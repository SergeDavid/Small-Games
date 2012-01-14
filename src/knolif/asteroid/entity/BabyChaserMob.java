package knolif.asteroid.entity;

import knolif.asteroid.Level;

public class BabyChaserMob extends ChaserMob {

	public BabyChaserMob(Level level, Player player, float x, float y) {
		super(level, player);
		this.x = x + rand.nextInt(5)-2;
		this.y = y + rand.nextInt(5)-2;
		maxspeed = 0.75f;
		damage = 4;
		distance = 50;
	}

}
