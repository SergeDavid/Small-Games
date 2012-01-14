package knolif.asteroid.entity;

import java.awt.Color;

import knolif.asteroid.Level;
import knolif.asteroid.effects.Death;

public class ExploderMob extends Monster {

	public ExploderMob(Level level) {
		super(level);
		size = 30 + rand.nextInt(11);//0-10
		color[0] = new Color(30,40,200);
		speed = 0.3f;
	}

	protected void isDead() { 
		if (!draw(-20,-20,level.width+20,level.height+20)) {remove = true;}
		if (intersects(level.game.player)) {
			level.game.player.hurt(damage);
			level.background.add(new Death(level, color[0], (int)x, (int)y, size));
			remove = true;
		}
	}
}
