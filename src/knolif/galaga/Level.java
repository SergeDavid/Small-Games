package knolif.galaga;

import java.util.ArrayList;
import java.util.List;

import knolif.galaga.entity.Entity;
import knolif.galaga.level.levelgen.LevelGen;
import knolif.galaga.level.script.Script;
import knolif.galaga.level.tile.Tile;
import knolif.galaga.screen.Screen;

public class Level {
	int length;
	public List<Entity> entities;
	List<Script> scripts;
	Game game;
	Screen screen;
	LevelGen gen;
	Tile tile;
	/**TODO: Decide on either prerendering the background as an image, or render it through a byte array each pass*/
	byte[][] background;
	
	public Level(Game g, Screen s) {
		game = g;
		gen = new LevelGen(this);
		entities = new ArrayList<Entity>();
		scripts = new ArrayList<Script>();
		
		length = s.height*3;
		//gen.init();
		gen.buildLevel();
		gen.buildScripts();
	}
	
	public void tick() {
		move();
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
			if (e.remove) {
				entities.remove(i--);
			}
		}
	}
	
	private void move() {
		//screen.position+=screen.speed;
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
