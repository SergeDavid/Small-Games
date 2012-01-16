package knolif.galaga.entity;

import knolif.galaga.InputHandler;
import knolif.galaga.Level;

public class Player extends Entity {
	int score;
	InputHandler input;
	
	public Player(Level l, InputHandler input) {
		super(l);
		this.input = input;
		level.entities.add(this);
		speed = 1;
	}
	
	public void move() {
		if (input.up.down) y-=speed;
		if (input.down.down) y+=speed;
		if (input.left.down) x-=speed;
		if (input.right.down) x+=speed;
	}
}
