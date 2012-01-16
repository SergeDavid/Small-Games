package knolif.galaga.entity;

import knolif.galaga.Level;
import knolif.galaga.screen.Screen;

public class Entity {
	Level level;
	int x, y;
	int maxhealth;
	int health;
	public boolean remove;
	int speed;//TODO: loosen up the controls with speed up / down?
	
	private int immunetime;
	/**Image location on the spritesheet*/private int image;
	/**collision size*/private int size;
	
	public Entity(Level l) {
		level = l;
		remove = false;
		maxhealth = health = 100;
		image = 0;
	}
	
	void pickup() {}
	void move() {}
	void logic() {}
	public final void tick() {
		logic();
		move();
	}
	public final void hurt(int damage) {
		if (immunetime == 0) {
			health-=damage;
			if (health<=0) {die();}
		}
	}
	private void die() {
		remove = true;
	}
	public final boolean intersects(float ex, float ey, int es) {
		final double a = size + es;
	    final double dx = x - ex;
	    final double dy = y - ey;
	    return a * a > (dx * dx + dy * dy);
	}
	
	void init() {}
	
	public void render(Screen screen) {
		//TODO: Render sprites and bla bla bla
		screen.drawSprite(0, 1, x, y);
	}
}
