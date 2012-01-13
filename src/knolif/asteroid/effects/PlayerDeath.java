package knolif.asteroid.effects;

import java.awt.Color;
import java.awt.Graphics;

import knolif.asteroid.Level;

public class PlayerDeath extends Effect {
	int flipmax;
	int flipmin;
	
	public PlayerDeath(Level level, Color c, int x, int y, int s) {
		super(level, c, x, y, s);
		growth = s/3;
		increase = 0.2f;
		life = 70;
		rings = 20;
		flipmax = 30;
		flipmin = -10;
	}

	public void tick() {
		if (life<0) {remove = true;}
		life--;
		growth += increase;
		size += growth;
		if (increase>0 && size > flipmax) {increase*=-1;}
		if (increase<0 && size < flipmin) {increase*=-1;}
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		int size2 = size;
		for (int i=0; i<rings; i++) {
			int xx = (int) (x-size2);
			int yy = (int) (y-size2);
			g.drawOval(xx,yy,size2*2,size2*2);
			size2 -= size/10;
		}
	}

}
