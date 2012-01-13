package knolif.asteroid.entity;

import java.awt.Color;
import java.awt.Graphics;

import knolif.asteroid.Level;

public class Bomb extends Entity {
	int life;
	float growth;
	float increase;
	Entity owner;
	
	public Bomb(Level level, Entity ply) {
		this.level = level;
		color = new Color(199, 255, 210);
		size = 2;
		x = ply.x + ply.xdir*4;
		y = ply.y + ply.ydir*4;
		xdir = ply.xdir;
		ydir = ply.ydir;
		growth = 1f;
		increase = 0.25f;
		life = 30;
		owner = ply;
	}
	
	public void tick() {
		if (life<0) {remove = true;}

		for (Entity e : level.entities) {
			if (intersects(e)) {
				if (e instanceof Monster) {
					level.game.score++;
					e.blownup = true;
					e.remove = true;
				}
			}
		}
		
		life--;
		growth += increase;
		size += growth;
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		int size2 = size;
		for (int i=0; i<5; i++) {
			int xx = (int) (x-size2);
			int yy = (int) (y-size2);
			g.drawOval(xx,yy,size2*2,size2*2);
			size2 -= 8;
		}
	}

}
