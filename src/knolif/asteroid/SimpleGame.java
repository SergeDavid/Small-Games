package knolif.asteroid;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import knolif.asteroid.entity.Player;

public class SimpleGame extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "Asteroids";
	public static final int HEIGHT = 120;
	public static final int WIDTH = 160;
	private static final int SCALE = 3;
	private boolean running = false;
	
	public int score = 0;
	public int highscore = 0;
	
	Level level;
	public Player player;
	InputHandler input = new InputHandler(this);
	
	private void start() {
		running = true;
		new Thread(this).start();
	}
	public void stop() {
		running = false;
	}
	public void restart() {
		if (score>highscore) {highscore = score;}
		score = 0;
		level = new Level(HEIGHT*SCALE,WIDTH*SCALE, this);
		player = new Player(level,input);
	}
	
	private void init() {
		level = new Level(HEIGHT*SCALE,WIDTH*SCALE, this);
		player = new Player(level,input);
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		int frames = 0;
		int ticks = 0;
		long lastTimer1 = System.currentTimeMillis();
		init();
		while (running) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (unprocessed >= 1) {
				ticks++;
				tick();
				unprocessed -= 1;
				shouldRender = true;
			}

			try {Thread.sleep(2);} 
			catch (InterruptedException e) {e.printStackTrace();}

			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}
		}
	}
	private void tick() {
		input.tick();
		level.tick();
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());
		
		level.render(g);
		g.setColor(new Color(255,255,255));
		
	    String hsm = (score>=highscore) ? "! High Score !" : "highscore : " + highscore ;
		g.drawString("Score :" + score, 10, 15);
		g.drawString(hsm, 10, 30);
		g.drawString("Health :" + player.life, 10, 45);
		if (player.remove == true) {
			g.drawString("Game Over", 200, 200);
		}
		
		g.setColor(new Color(120,120,120));
		g.fillRect(4, HEIGHT*SCALE-6, 200, 6);
		g.setColor(new Color(255,0,0));
		g.fillRect(4, HEIGHT*SCALE-6, (int)(player.maxdelaybomb-player.delaybomb), 6);
		
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		SimpleGame game = new SimpleGame();
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(SimpleGame.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}
}
