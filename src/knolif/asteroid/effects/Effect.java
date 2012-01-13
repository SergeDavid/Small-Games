package knolif.asteroid.effects;

import java.awt.Color;
import java.awt.Graphics;

import knolif.asteroid.Level;

public class Effect {
	Level level;
	Color color;
	int life;
	int size;
	float growth;
	float increase;
	int rings;
	public boolean remove;
	int x;
	int y;
	
	public Effect(Level level, Color c, int x, int y, int s) {
		this.level = level;
		color = c;
		size = 2;
		this.x = x;
		this.y = y;
	}
	public void tick() {}
	public void render(Graphics g) {}
	protected void remove() {}
}
