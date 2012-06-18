package knolif.galaga.entity.bullet;

import knolif.galaga.Level;
import knolif.galaga.entity.Entity;
import knolif.galaga.entity.Mob;

public class Bullet extends Entity {
	Mob parent;

	public Bullet(Level l, Mob p) {
		super(l);
		parent = p;
		x = p.x;
		y = p.y;
		size = 1;
	}
	
	public void tick() {
		y+=1;
	}
	
	public void offscreen() {die();}

}
