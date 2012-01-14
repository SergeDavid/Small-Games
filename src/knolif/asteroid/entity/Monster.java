package knolif.asteroid.entity;

import java.awt.Color;

import knolif.asteroid.Level;

public class Monster extends Entity {
	int damage = 25;
	public Monster(Level level) {
		x = 1 + rand.nextInt(level.width-2);
		y = 1 + rand.nextInt(level.height-2);
		switch (rand.nextInt(4)) {
		  case 0:x=-10;break;
		  case 1:y=-10;break;
		  case 2:x=level.width+10;break;
		  case 3:y=level.height+10;break;
		}
		this.level = level;
		dir = rand.nextInt(360);
		speed = rand.nextFloat()+0.1f + rand.nextFloat();
		
		color[0] = randColor();
	}
	
	private Color randColor() {
		int r = 255 - rand.nextInt(255);
		int b = 255 - rand.nextInt(255);
		int g = 255 - rand.nextInt(255);
		return new Color(r,b,g);
	}
	
	protected void isDead() { 
		if (!draw(-20,-20,level.width+20,level.height+20)) {loopAround();}//remove = true;}
		if (intersects(level.game.player)) {
			level.game.player.hurt(damage);
			dieEffect();
			remove = true;
		}
	}

	protected void move() {
		x = (float) (x - speed * Math.sin(dir));
		y = (float) (y + speed * Math.cos(dir));
	}
}
