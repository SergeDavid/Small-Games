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
		x = ply.x + ply.xdir*4;
		y = ply.y + ply.ydir*4;
		dir = getDir((ply.ydir+2)*10+(ply.xdir+2));	
		dir += offset;
		speed = 4;
		life = 80;
		owner = ply;
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
