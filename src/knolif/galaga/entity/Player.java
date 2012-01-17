package knolif.galaga.entity;

import knolif.galaga.InputHandler;
import knolif.galaga.Level;

public class Player extends Mob {
	InputHandler input;
	
	public Player(Level l, InputHandler input) {
		super(l);
		this.input = input;
		level.entities.add(this);
		speed = 3;
		size = 2;
		image = 16*6;
	}
	
	public void tick() {
		if (input.up.down) y-=speed;
		if (input.down.down) y+=speed;
		if (input.left.down) x-=speed;
		if (input.right.down) x+=speed;
	}
}
