package knolif.galaga.screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import knolif.galaga.entity.Player;

public class Screen {
	private SpriteSheet sheet;
	public Player player;
	public int width;
	public int height;
	/**The bottom of the camera*/public int position;
	public int speed;
	private Graphics2D g;
	private int yoffset;
	private int xoffset;
	
	public Screen(int w, int h, SpriteSheet spit) {
		width = w;
		height = h;
		sheet = spit;
		position = 0;
	}
	
	/**Called each render pass to clear itself and draw too*/
	public void setGraphics(Graphics2D gg, int w, int h) {
		g = gg;//greg = good guy
		g.fillRect(0, 0, w, h);
		yoffset = (h-height)/2;
		xoffset = (w-width)/2;
		g.setColor(Color.black);
		g.fillRect(xoffset, yoffset, width, height);
	}
	
	/**
	 * @param offset The location of the image to use (y * rows + x)
	 * @param size How many sprites to grab (2 would be a 2x2 image)
	 */
	//1400 [positon]
	//1500 [y]
	//100 off bottom.
	public void drawSprite(int offset, int size, int x, int y) {
		if (onScreen(x,y,size)) {
			size*=sheet.size;
			int xx = sheet.getX(offset, size);
			int yy = sheet.getY(offset, size);
			y = height-(y-position);
			Image img = sheet.image.getSubimage(xx, yy, size, size);
			g.drawImage(img, x, y, size, size, null);
		}
	}
	
	/**Currently set for positon to be the top, will fix eventually.*/
	public boolean onScreen(int x, int y, int size) {
		size*=sheet.size;
		int xx = x-size/2;//20
		int yy = y-size/2;//50
		//This is kind of weird, but I wanted to make sure you could see it till it left the screen.
		return !(xx+size < (-1*xoffset) ||
				xx > width ||
				yy+size < position-yoffset*2 ||
				yy > position + height);
	}

	public void drawGUI () {
		g.setColor(Color.white);
		g.drawString("position : " + position, 10+xoffset, 10+yoffset);
		g.drawString("player : " + player.y, 10+xoffset, 20+yoffset);
	}

	public void dispose() {
		g.dispose();
	}

}
