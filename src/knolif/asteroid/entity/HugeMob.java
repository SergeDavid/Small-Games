package knolif.asteroid.entity;

import knolif.asteroid.Level;

public class HugeMob extends Monster {

	public HugeMob(Level level) {
		super(level);
		size = 50;
		damage = 50;
		xdir /= 2; 
		ydir /= 2; 
	}

}
