package knolif.asteroid.entity;

import java.awt.Color;

import knolif.asteroid.Level;

public class Bullet extends Entity {
	int life;
	float speed;
	double dir;
	Entity owner;
	
	public Bullet(Level level, Player ply, double offset) {
		this.level = level;
		color = new Color(255, 245, 230);
		size = 2;
		x = ply.x + ply.xlook*4;
		y = ply.y + ply.ylook*4;
		dir = getDir((ply.ylook+2)*10+(ply.xlook+2));	
		dir += offset;
		speed = 4;
		life = 130;
		owner = ply;
	}
	
	/**To hell with everything about directions.*/
	private double getDir(int i) {
		double pi = Math.PI;
		double out = 0;
		switch (i) {
		case 32:out = 0;break;
		case 31:out = pi/4;break;
		case 21:out = pi/2;break;
		case 11:out = pi-pi/4;break;
		case 12:out = pi;break;
		case 13:out = pi+pi/4;break;
		case 23:out = -pi/2;break;
		case 33:out = -pi/4;break;
		}
		return out;
	}

	public void tick() {
		if (life<0 || !draw(-10,-10,level.width+10,level.height+10)) {remove = true;}

		for (Entity e : level.entities) {
			if (intersects(e)) {
				if (e instanceof Monster) {
					level.game.score++;
					e.remove = true;
					e.blownup = true;
					remove = true;
				}
			}
		}
		
		life--;
		x = (float) (x - speed * Math.sin(dir));
		y = (float) (y + speed * Math.cos(dir));
	}

}
