package knolif.asteroid.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import knolif.asteroid.Level;

public class Entity {
	Random rand = new Random();
	protected Level level;
	public int size = 10;//To hell with it, this is now Radii

	public float x = 0;//position
	public float y = 0;
	int xdir = 0;
	int ydir = -1;

	public Color color;
	public boolean remove = false;
	public boolean blownup;
	
	public Entity() {}
	
	public void tick() {
		isDead();
		move();
	}
	/**Part of tick*/
	protected void isDead() {}
	/**Part of tick*/
	protected void move() {}
	
	public void render(Graphics g) {
		int xx = (int) (x-size);
		int yy = (int) (y-size);
		g.setColor(color);
		g.fillOval(xx,yy,size*2,size*2);
	}
	
	public boolean draw(int x0,int y0,int x1,int y1) {
		return !(x < x0 || y < y0 || x > x1 || y > y1);
	}
	protected boolean intersects(Entity e) {
	    final double a = size + e.size;
	    final double dx = x - e.x;
	    final double dy = y - e.y;
	    return a * a > (dx * dx + dy * dy);
	}
}
