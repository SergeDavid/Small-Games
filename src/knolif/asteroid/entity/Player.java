package knolif.asteroid.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import knolif.asteroid.InputHandler;
import knolif.asteroid.Level;
import knolif.asteroid.effects.PlayerDeath;

public class Player extends Entity {
	InputHandler input;
	Color color2;
	Color color3;
	int delayshot = 1;
	int delaybomb = 1;
	int maxdelaybomb = 80;
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
		size = 11;
		x = level.width/2;
		y = level.height/2;
		image = CreateImage();
	}
	
	private BufferedImage CreateImage() {
		BufferedImage bs = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bs.getGraphics();
		
		g.setColor(new Color(180,0,0));//lighter
		int xpoints[] = {32, 36, 48, 32, 16, 28};
	    int ypoints[] = {50, 44, 22, 30, 22, 44};
	    int npoints = 6;
	    g.fillPolygon(xpoints, ypoints, npoints);
	    
	    g.setColor(new Color(120,0,0));//darker
		int xpoint[] = {32, 42, 32, 22};
	    int ypoint[] = {31, 28, 14, 28};
	    int npoint = 4;
	    g.fillPolygon(xpoint, ypoint, npoint);
	    
	    g.setColor(new Color(160,200,230));//cockpit
	    g.fillOval(32-4, 32, 8, 12);
		return bs;
	}

	protected void isDead() {
		loopAround();
		if (life <= 0) {
			remove = true;
			level.background.add(new PlayerDeath(level, color[0], (int)x, (int)y, size));
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
		delayshot--;
		delaybomb--;
	
		if (xdir != 0 || ydir != 0) {
			this.xdir = xdir;
			this.ydir = ydir;
		}
		
		if (ydir != 0 && xdir != 0) {ss/=2;}
		x += xdir * ss;
		y += ydir * ss;
	}
	
	public void render(Graphics g) {
		BufferedImage image = this.image;
		AffineTransform tx = new AffineTransform();
		tx.rotate(getDir((ydir+2)*10+(xdir+2)), image.getWidth()/2, image.getHeight()/2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		image = op.filter(image, null);
		g.drawImage(image, (int)(x-32),(int)(y-32), null);
	}
}
