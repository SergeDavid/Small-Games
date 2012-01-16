package knolif.galaga.screen;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	BufferedImage image;
	int cols;//y
	int rows;//x
	/**sprite size*/int size;// height x width of a standard sprite
	
	public SpriteSheet(BufferedImage img) {
		image = img;
		size = 16;
		rows = img.getWidth()/size;
		cols = img.getHeight()/size;
	}
}
