package knolif.asteroid.entity;

import knolif.asteroid.Level;
import knolif.asteroid.effects.HugeDeath;

public class HugeMob extends Monster {

	public HugeMob(Level level) {
		super(level);
		size = 50;
		damage = 50;
		xdir /= 2; 
		ydir /= 2; 
	}
	
	public void dieEffect() {
		level.background.add(new HugeDeath(level, color[0], (int)x, (int)y, size));
	}

}
