package knolif.asteroid.entity;

import java.awt.Color;
import java.awt.Graphics;

import knolif.asteroid.InputHandler;
import knolif.asteroid.Level;
import knolif.asteroid.effects.PlayerDeath;

public class Player extends Entity {
	InputHandler input;
	Color color2;
	Color color3;
	int delayshot = 1;
	int maxdelayshot = 12;
	int bullets = 2;
	int xlook = 0;
	int ylook = -1;
	
	public Player(Level level, InputHandler input) {
		this.input = input;
		this.level = level;
		level.entities.add(this);
		color = new Color(0,0,0);
		color2 = new Color(22,22,22);
		color3 = new Color(255,0,0);
		size = 15;
		x = level.width/2;
		y = level.height/2;
	}
	
	public void tick() {
		int xlook = 0; int ylook = 0;
		if (input.up2.down) {ylook--;} if (input.down2.down) {ylook++;}
		if (input.left2.down) {xlook--;} if (input.right2.down) {xlook++;}
		if (xlook != 0 || ylook != 0) {
			this.xlook = xlook;
			this.ylook = ylook;
		}
		
		int xdir = 0;
		int ydir = 0;
		float ss = 3;//speed
		
		if (input.up.down) {ydir--;}
		if (input.down.down) {ydir++;}
		if (input.left.down) {xdir--;}
		if (input.right.down) {xdir++;}
		
		if (input.attack.down) {
			if (delayshot<=0) {
			  level.entities.add(new Bullet(level,this,0));
			  for (int i=1; i<bullets; i++) {
				  level.entities.add(new Bullet(level,this,(0.2f*i)));
				  level.entities.add(new Bullet(level,this,(-0.2f*i)));
			  }
			  delayshot = maxdelayshot;
			}
		}
		if (input.extra.clicked) {
			level.background.add(new PlayerDeath(level, color2, (int)x, (int)y, size));
		}
		delayshot--;
	
		if (xdir != 0 || ydir != 0) {
			this.xdir = xdir;
			this.ydir = ydir;
		}
		
		if (ydir != 0 && xdir != 0) {ss/=2;}
		x += xdir * ss;
		y += ydir * ss;
	}
	
	public void render(Graphics g) {
		int xx = (int) (x-size);
		int yy = (int) (y-size);
		g.setColor(color);
		g.fillOval(xx,yy,size*2,size*2);
		g.setColor(color2);
		g.fillOval(xx+4,yy+2,size+4,size+4);
		g.setColor(color3);
		xx = (int)x+(xlook*8)-4;
		yy = (int)y+(ylook*8)-4;
		g.fillOval(xx, yy, 8, 8);
	}
}
