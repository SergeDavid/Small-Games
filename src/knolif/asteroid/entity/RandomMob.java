package knolif.asteroid.entity;

import knolif.asteroid.Level;

public class RandomMob extends Monster {

	public RandomMob(Level level) {
		super(level);
		size = 3 + rand.nextInt(20);
		damage = (int) (size/1.5);
		speed += rand.nextFloat()*2;
	}

}
