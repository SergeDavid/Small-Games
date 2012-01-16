package knolif.galaga;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import knolif.galaga.Level;
import knolif.galaga.entity.Player;
import knolif.galaga.screen.Screen;
import knolif.galaga.screen.SpriteSheet;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	Screen screen;
	Level level;
	Player player;
	InputHandler input;
	private final static String NAME = "Galaga";
	private final static int WIDTH = 400;//3x4
	private final static int HEIGHT = 300;
	private boolean running = false;
	
	public Game() {
		input = new InputHandler(this);
		try {
			screen = new Screen(WIDTH, HEIGHT, new SpriteSheet(ImageIO.read(Game.class.getResourceAsStream("/icon.png"))));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		restart();
	}
	
	public void restart() {
		level = new Level(this,HEIGHT,WIDTH);
		player = new Player(level,input);
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		int frames = 0;
		int ticks = 0;
		long lastTimer1 = System.currentTimeMillis();
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
		screen.setGraphics((Graphics2D) bs.getDrawGraphics(), getWidth(), getHeight());
		
		screen.drawSprite(0, 1, 20, 20);
		level.render(screen);
		
		screen.dispose();
		bs.show();
	}
	
	//Don't need to mess with this stuff
	private void start() {
		running = true;
		new Thread(this).start();
	}
	public void stop() {
		running = false;
	}
	public static void main(String[] args) {
		Game game = new Game();
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		JFrame frame = new JFrame(Game.NAME);
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
