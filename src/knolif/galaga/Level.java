package knolif.galaga;

import java.util.ArrayList;
import java.util.List;

import knolif.galaga.entity.Entity;
import knolif.galaga.screen.Screen;

public class Level {
	int width;
	int height;
	/**How fast the level moves*/int speed;
	/**How far you have to go until you win the level*/int distance;
	int somenumbertoshowhowfarintothegameyouare;
	public List<Entity> entities;
	Game game;
	
	public Level(Game g, int w, int h) {
		game = g;
		entities = new ArrayList<Entity>();
		width = w;
		height = h*2;
		distance = height-h;
		speed = 3;
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
	
	public void render(Screen screen) {
		renderBackground(screen);
		renderEntities(screen);
	}
	
	private void move() {
		distance-=speed;
		if (distance<=0) {
			distance = 0;
			speed = 0;
			//TODO: Win level
		}
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
