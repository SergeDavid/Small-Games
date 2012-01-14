package knolif.asteroid.image;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Image {
	protected static int size = 11;
	protected static Color[] c = {
		new Color(180,0,0),
		new Color(120,0,0),
		new Color(160,200,230)
	};
	
	public static int size() {return size;}
	
	public static BufferedImage rotate(BufferedImage image, double dir) {
		
		AffineTransform tx = new AffineTransform();
		try {
			tx.rotate(dir, image.getWidth()/2, image.getHeight()/2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		return op.filter(image, null);
	}
	
	protected static void invertColor() {
		for (int i = 0; i < c.length; i++) {
			Color cc = c[i];
			int r = 255 - cc.getRed();
			int g = 255 - cc.getGreen();
			int b = 255 - cc.getBlue();
			c[i] = new Color(r,b,g);
		}
	}
	
}
