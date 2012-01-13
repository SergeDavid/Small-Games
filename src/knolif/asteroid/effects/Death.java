package knolif.asteroid.effects;

import java.awt.Color;
import java.awt.Graphics;

import knolif.asteroid.Level;

public class Death extends Effect {
	
	public Death(Level level, Color c, int x, int y, int s) {
		super(level, c, x, y, s);
		growth = s/3;
		increase = 0.1f;
		life = 10+(s/10);
		rings = s/2;
	}

	public void tick() {
		if (life<0) {remove = true;}
		life--;
		growth += increase;
		size += growth;
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		int size2 = size;
		for (int i=0; i<rings; i++) {
			int xx = (int) (x-size2);
			int yy = (int) (y-size2);
			g.drawOval(xx,yy,size2*2,size2*2);
			size2 -= size/5;
		}
	}

}
