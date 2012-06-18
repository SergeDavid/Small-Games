package knolif.galaga.entity.senery;

import knolif.galaga.Level;
import knolif.galaga.entity.Entity;

public class Tree extends Entity {

	public Tree(Level l, int x, int y) {
		super(l);
		size = 3;
		image = 13;
		this.x = x;
		this.y = y;
	}

}
