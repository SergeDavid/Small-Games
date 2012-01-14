package knolif.asteroid.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import knolif.asteroid.Level;

public class Entity {
	Random rand = new Random();
	protected Level level;
	public int size = 10;//To hell with it, this is now Radii
	public int life = 100;
	int hurtlength = 10;
	int hurttime = 0;

	public float x = 0;//position
	public float y = 0;
	int xdir = 0;
	int ydir = -1;
	
	float speed;
	double dir;

	public Color[] color = new Color[3];
	public boolean remove = false;
	public boolean blownup;
	
	public Entity() {}
	
	public void tick() {
		isDead();
		move();
		if (hurttime>0) {
			hurttime--;
			if (hurttime==0) {
				color = invertColor(color);
			}
		}
	}
	/**Part of tick*/
	protected void isDead() {}
	/**Part of tick*/
	protected void move() {
		x = (float) (x - speed * Math.sin(dir));
		y = (float) (y + speed * Math.cos(dir));
	}
	
	public void render(Graphics g) {
		int xx = (int) (x-size);
		int yy = (int) (y-size);
		g.setColor(color[0]);
		g.fillOval(xx,yy,size*2,size*2);
	}
	protected Color[] invertColor(Color[] color) {
		for (int i = 0; i < color.length; i++) {
			Color c = color[i];
			int r = 255 - c.getRed();
			int g = 255 - c.getGreen();
			int b = 255 - c.getBlue();
			color[i] = new Color(r,b,g);
		}
		return color;
	}
	
	public void hurt(int damage) {
		life-=damage;
		if (hurttime==0) {color = invertColor(color);}
		hurttime = hurtlength;
	}
	
	protected void loopAround() {
		int r = 10;
		if (x<-r) {x = level.width+r;}
		if (y<-r) {y = level.height+r;}
		if (x>level.width+r) {x = -r;}
		if (y>level.height+r) {y = -r;}
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
