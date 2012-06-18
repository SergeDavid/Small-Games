package knolif.galaga.entity;

import knolif.galaga.Level;
import knolif.galaga.screen.Screen;

public class Entity {
	protected Level level;
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
	
	/**Called in the level.move() method so that movement on entities that stay with the camera work.*/
	public void moveWithScreen(int s) {}

	public void tick() {}

	public void die() {
		System.out.println("Wakka" + x + " and " + y);
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

	/**Handles what happens if the entity leaves the screen.*/
	public void offscreen() {die();}
}
