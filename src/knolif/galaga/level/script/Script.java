package knolif.galaga.level.script;

import knolif.galaga.Level;

public class Script {
	
	Level level;
	int start;//Starting position of the script
	int end;//End position of the script
	public boolean remove;
	
	public Script(Level l, int s, int e) {
		level = l;
		start = s;
		end = e;
		start();
	}
	
	/**The position attribute is screen.position and shows how far along the script you currently are.*/
	public void tick(int position) {
		die(position);
		spawn();
	}
	
	protected void spawn() {}
	/**Might just use the constructor, called once on startup*/
	protected void start() {}
	
	protected void die(int p) {
		if (p >= end) {remove = true;}
	}
	
}
