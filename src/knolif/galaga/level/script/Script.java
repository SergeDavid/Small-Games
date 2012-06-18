package knolif.galaga.level.script;

import knolif.galaga.Level;
import knolif.galaga.screen.Screen;

public class Script {
	
	Level level;
	Screen screen;
	int start;//Starting position of the script
	int end;//End position of the script
	public boolean remove;
	
	public Script(Level l, Screen s, int b, int e) {
		level = l;
		screen = s;
		start = b;
		end = e;
	}
	
	/**The position attribute is screen.position and shows how far along the script you currently are.*/
	public void tick(int position) {
		die(position);
		spawn(position);
	}
	
	protected void spawn(int position) {}
	
	protected void die(int p) {
		if (p >= end) {remove = true;}
	}
	
}
