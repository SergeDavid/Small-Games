package knolif.asteroid;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import knolif.asteroid.effects.Effect;
import knolif.asteroid.effects.PlayerDeath;
import knolif.asteroid.entity.BabyChaserMob;
import knolif.asteroid.entity.ChaserMob;
import knolif.asteroid.entity.Entity;
import knolif.asteroid.entity.ExploderMob;
import knolif.asteroid.entity.Monster;
import knolif.asteroid.entity.RandomMob;

public class Level {
	Random rand = new Random();
	public List<Entity> entities;
	public List<Effect> background;
	public SimpleGame game;
	public int width;
	public int height;
	int maxObjects = 24;
	int Objects = 0;
	
	public Level(int h, int w, SimpleGame game) {
		this.game = game;
		width = w;
		height = h;
		entities = new ArrayList<Entity>();
		background = new ArrayList<Effect>();
	}

	public void tick() {
		if (Objects < maxObjects) {
			if (rand.nextInt(10) == 0) {
				Objects++;
				if (rand.nextInt(1) == 0) {
					entities.add(new ExploderMob(this));
				}
				else if (rand.nextInt(8) == 0) {
					entities.add(new ChaserMob(this, game.player));
				}
				else {entities.add(new RandomMob(this));}
			}
		}
		for (int i = 0; i < background.size(); i++) {
			Effect e = background.get(i);
			e.tick();
			if (e.remove) {
				if (e instanceof PlayerDeath) {game.restart();}
				background.remove(i--);
			} 
		}
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
			if (e.remove) {
				if (e instanceof Monster) {Objects--;}
				if (e instanceof ExploderMob) {
					if (e.blownup)
					for (int a = 0; a < rand.nextInt(2)+3; a++) {
						entities.add(new BabyChaserMob(this, game.player, e.x, e.y));
					}
				}
				entities.remove(i--);
			} 
		}
	}
	
	public void render(Graphics g) {
		RenderBG(g);
		RenderEnts(g);
	}

	private void RenderEnts(Graphics g) {
		for (Entity e : entities) {
			e.render(g);
		}
	}
	private void RenderBG(Graphics g) {
		for (Effect e : background) {
			e.render(g);
		}
	}
}
