package knolif.asteroid.image;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PlayerShip extends Image {

	public static BufferedImage imageInvertColor() {
		invertColor();
		BufferedImage img = image();
		invertColor();
		return img;
	}
	public static BufferedImage image() {
		BufferedImage bs = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bs.getGraphics();
		
		g.setColor(c[0]);//lighter
		int xpoints[] = {32, 36, 48, 32, 16, 28};
	    int ypoints[] = {50, 44, 22, 30, 22, 44};
	    int npoints = 6;
	    g.fillPolygon(xpoints, ypoints, npoints);
	    
	    g.setColor(c[1]);//darker
		int xpoint[] = {32, 42, 32, 22};
	    int ypoint[] = {31, 28, 14, 28};
	    int npoint = 4;
	    g.fillPolygon(xpoint, ypoint, npoint);
	    
	    g.setColor(c[2]);//cockpit
	    g.fillOval(32-4, 32, 8, 12);
		return bs;
	}	
}
