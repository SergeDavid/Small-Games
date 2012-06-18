package knolif.galaga.level.script;

import knolif.galaga.Level;
import knolif.galaga.screen.Screen;

public class SlowScript extends Script {
	int startingspeed;
	public SlowScript(Level l, Screen s, int b, int e) {
		super(l, s, b, e);
		startingspeed = l.screen.speed;
		l.screen.speed /= 2;
	}
	
	public void die(int p) {
		if (p >= end) {remove = true;}
		level.screen.speed *= startingspeed;
	}

}
