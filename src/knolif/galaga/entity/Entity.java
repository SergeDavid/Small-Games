package knolif.galaga.entity;

import knolif.galaga.Level;
import knolif.galaga.screen.Screen;

public class Entity {
	Level level;
	public int x;
	public int y;
	public boolean remove;
	/**Image location on the spritesheet*/protected int image;
	/**collision size*/public int size;
	
	public Entity(Level l) {
		level = l;
		remove = false;
		image = 0;
		size = 1;
	}

	public void tick() {}

	public void die() {
		remove = true;
	}
	
	public void interact() {}
	
	public final boolean intersects(float ex, float ey, int es) {
		final double a = size + es;
	    final double dx = x - ex;
	    final double dy = y - ey;
	    return a * a > (dx * dx + dy * dy);
	}
	
	public void render(Screen screen) {
		screen.drawSprite(image, size, x, y);
	}
}
