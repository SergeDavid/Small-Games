package knolif.asteroid.entity;

import java.awt.Color;

import knolif.asteroid.Level;

public class BabyChaserMob extends ChaserMob {

	public BabyChaserMob(Level level, Player player, float x, float y) {
		super(level, player);
		this.x = x + rand.nextInt(5)-2;
		this.y = y + rand.nextInt(5)-2;
		color[0] = new Color(50,220,50);
		xdir = rand.nextInt(5)-2;
		ydir = rand.nextInt(5)-2;
		maxspeed = 2.5f + rand.nextFloat()*2;
		if (maxspeed>3f) {maxspeed=3f;}
		size *= 1.5; 
		damage = 4;
		distance = 100;
	}

}
