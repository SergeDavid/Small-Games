package knolif.asteroid.entity;

import java.awt.Color;

import knolif.asteroid.Level;

public class ChaserMob extends Monster {
	Player player;
	float maxspeed;
	float xdir;
	float ydir;
	float distance;
	public ChaserMob(Level level, Player player) {
		super(level);
		this.player = player;
		size = 8;
		color[0] = new Color(50,190,50);
		maxspeed = 0.5f + rand.nextFloat() + rand.nextInt(3);
		if (maxspeed>1.65f) {maxspeed=1.65f;}
		speed = 5.1f;
		damage = 7;
		distance = 100;
	}
	protected void move() {
		if (player.x > x + distance) {
			xdir += speed;
			if (xdir>maxspeed) {xdir = maxspeed;}
		}
		if (player.x < x - distance) {
			xdir -= speed;
			if (xdir<-maxspeed) {xdir = -maxspeed;}
		}
		if (player.y > y + distance) {
			ydir += speed;
			if (ydir>maxspeed) {ydir = maxspeed;}
		}
		if (player.y < y - distance) {
			ydir -= speed;
			if (ydir<-maxspeed) {ydir = -maxspeed;}
		}
		
		x += xdir;
		y += ydir;
	}

}
