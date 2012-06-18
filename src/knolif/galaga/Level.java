package knolif.galaga;

import java.util.ArrayList;
import java.util.List;

import knolif.galaga.entity.Entity;
import knolif.galaga.level.levelgen.LevelGen;
import knolif.galaga.level.script.Script;
import knolif.galaga.level.tile.Tile;
import knolif.galaga.screen.Screen;

public class Level {
	public int length;
	public List<Entity> entities;
	public List<Script> scripts;
	@SuppressWarnings("unused")
	private Game game;
	public Screen screen;
	private LevelGen gen;
	@SuppressWarnings("unused")
	private Tile tile;
	/**TODO: Decide on either prerendering the background as an image, or render it through a byte array each pass*/
	byte[][] background;
	
	public Level(Game g, Screen s) {
		game = g;
		screen = s;
		gen = new LevelGen(this);
		entities = new ArrayList<Entity>();
		scripts = new ArrayList<Script>();
		
		length = s.height*4;
		//gen.init();
		gen.buildLevel();
		gen.buildScripts();
	}
	
	public void tick() {
		move();
		if (!scripts.isEmpty()) {
			scripts.get(0).tick(screen.position+screen.height);
		}
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
			if (!screen.onScreen(e.x, e.y, e.size)) {
				e.offscreen();
			}
			if (e.remove) {
				entities.remove(i--);
			}
		}
	}
	
	private int baaa;
	/**Moves the screen's position based on it's speed (decided by the script generator)
	 * End of level is handled by the script not this.*/
	private void move() {
		if (baaa<0) {
			screen.position+=screen.speed;
			for (Entity e : entities) {
				e.moveWithScreen(screen.speed);
			}
			baaa = 2;
		}
		else {baaa--;}
	}
	
	/**Renders the background, then all of the sprites. Currently does not support land sprites
	 * (Not sorted so that sprites at the top of the map are rendered first)*/
	public void render(Screen screen) {
		renderBackground(screen);
		renderEntities(screen);
	}
	private void renderBackground(Screen screen) {
		//TODO: Render all of the background elements such as dirt / trees / waves
	}
	private void renderEntities(Screen screen) {
		for (Entity e : entities) {
			e.render(screen);
		}
	}
	
}
