package knolif.asteroid.entity;

import knolif.asteroid.Level;

public class ZipZagMob extends RandomMob {
	int changetime = 0;
	public ZipZagMob(Level level) {
		super(level);
	}
	
	public void tick() {
		isDead();
		if (changetime == 0) {
			changetime = rand.nextInt(110)+10;
			dir = rand.nextInt(360);
		}
		move();
		changetime--;
	}

}
