package knolif.asteroid.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import knolif.asteroid.InputHandler;
import knolif.asteroid.Level;
import knolif.asteroid.effects.PlayerDeath;
import knolif.asteroid.image.PlayerShip;

public class Player extends Entity {
	InputHandler input;
	Color color2;
	Color color3;
	int delayshot = 1;
	public int delaybomb = 1;
	public int maxdelaybomb = 200;
	int maxdelayshot = 12;
	int bullets = 2;
	int xlook = 0;
	int ylook = -1;
	BufferedImage image;
	
	public Player(Level level, InputHandler input) {
		this.input = input;
		this.level = level;
		level.entities.add(this);
		color[0] = new Color(0,0,0);
		size = PlayerShip.size();
		x = level.width/2;
		y = level.height/2;
		image = PlayerShip.image();
	}
	
	protected void showhurt() {image = PlayerShip.imageInvertColor();}
	protected void removehurt() {image = PlayerShip.image();}
	public void dieEffect() {
		level.background.add(new PlayerDeath(level, color[0], (int)x, (int)y, size));
	}
	protected void isDead() {
		loopAround();
		if (life <= 0) {
			remove = true;
			dieEffect();
		}
	}

	protected void move() {
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
			  for (int i=1; i<bullets; i++) {
				  level.entities.add(new Bullet(level,this,(0.1f*i)));
				  level.entities.add(new Bullet(level,this,(-0.1f*i)));
			  }
			  delayshot = maxdelayshot;
			}
		}
		if (input.extra.clicked) {
			if (delaybomb<=0) {
				level.entities.add(new Bomb(level, this));
				delaybomb = maxdelaybomb;
			}
		}
		if (delayshot!=0) delayshot--;
		if (delaybomb!=0) delaybomb--;
	
		if (xdir != 0 || ydir != 0) {
			this.xdir = xdir;
			this.ydir = ydir;
		}
		
		if (ydir != 0 && xdir != 0) {ss/=1.5;}
		x += xdir * ss;
		y += ydir * ss;
	}
	
	public void render(Graphics g) {
		double d = getDir((ydir+2)*10+(xdir+2));
		g.drawImage(PlayerShip.rotate(this.image, d), (int)(x-32),(int)(y-32), null);
	}
}
