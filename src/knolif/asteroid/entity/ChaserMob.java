package knolif.asteroid.entity;

import java.awt.Color;

import knolif.asteroid.Level;

public class ChaserMob extends Monster {
	Player player;
	float maxspeed;
	float xdir;
	float ydir;
	public ChaserMob(Level level, Player player) {
		super(level);
		this.player = player;
		size = 8;
		color[0] = new Color(50,255,50);
		maxspeed = 1.5f;
		speed = 0.1f;
		// TODO Auto-generated constructor stub
	}
	
	float distance = 10;
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
