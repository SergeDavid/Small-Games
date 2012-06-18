package knolif.galaga.entity;

import knolif.galaga.entity.bullet.Bullet;
import knolif.galaga.entity.senery.Tree;
import knolif.galaga.InputHandler;
import knolif.galaga.Level;

public class Player extends Mob {
	InputHandler input;
	int attackinterval;
	int attacktimetogo;
	
	public Player(Level l, InputHandler input) {
		super(l);
		this.input = input;
		level.entities.add(this);
		speed = 1;
		size = 2;
		image = 16*6;
		attackinterval = 50;
	}
	public void moveWithScreen(int s) {
		y+=s;
	}
	
	public void offscreen() {}
	
	public void tick() {
		if (input.up.down) y+=speed;
		if (input.down.down) y-=speed;
		if (input.left.down) x-=speed;
		if (input.right.down) x+=speed;
		if (input.down2.down) {
			if (attacktimetogo<=0) {
				attacktimetogo = 10;
				level.entities.add(new Tree(level, x, y));
			}
		}
		if (input.attack.down) {
			if (attacktimetogo<=0) {
				attacktimetogo = attackinterval;
				level.entities.add(new Bullet(level, this));
			}
		}
		attacktimetogo--;
	}
}
