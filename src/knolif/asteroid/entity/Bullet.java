package knolif.asteroid.entity;

import java.awt.Color;

import knolif.asteroid.Level;
import knolif.asteroid.effects.Death;

public class Bullet extends Entity {
	int life;
	Entity owner;
	
	public Bullet(Level level, Player ply, double offset) {
		this.level = level;
		color[0] = new Color(255, 245, 230);
		size = 2;
		x = ply.x + ply.xlook*4;
		y = ply.y + ply.ylook*4;
		dir = getDir((ply.ylook+2)*10+(ply.xlook+2));	
		dir += offset;
		speed = 4;
		life = 80;
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

	protected void isDead() {
		loopAround();
		if (life<0) {remove = true;}//!draw(-10,-10,level.width+10,level.height+10)

		for (Entity e : level.entities) {
			if (intersects(e)) {
				if (e instanceof Monster) {
					level.game.score++;
					e.blownup = true;
					e.remove = true;
					level.background.add(new Death(level,e.color[0], (int)e.x, (int)e.y, e.size));
					remove = true;
				}
			}
		}
		life--;
	}
}
