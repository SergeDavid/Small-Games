package knolif.galaga.level.script;

import java.util.Random;

import knolif.galaga.Level;
import knolif.galaga.entity.senery.Tree;
import knolif.galaga.screen.Screen;

public class RandomScript extends Script {
	Random rand = new Random();

	public RandomScript(Level l, Screen s, int b, int e) {
		super(l, s, b, e);
		screen.speed = 2;
		// TODO Auto-generated constructor stub
	}
	
	protected void spawn(int position) {
		if (rand.nextInt(1000) < 10)
		level.entities.add(new Tree(level, rand.nextInt(screen.width), position));
	}

}
