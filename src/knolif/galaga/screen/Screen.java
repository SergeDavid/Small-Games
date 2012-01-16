package knolif.galaga.screen;

import java.awt.Graphics2D;
import java.awt.Image;

public class Screen {
	SpriteSheet sheet;
	public int width;
	int height;
	Graphics2D g;
	
	public Screen(int w, int h, SpriteSheet spit) {
		width = w;
		height = h;
		sheet = spit;
	}
	
	/**Called each render pass to clear itself and draw too*/
	public void setGraphics(Graphics2D gg, int w, int h) {
		g = gg;//greg = good guy
		g.fillRect(0, 0, w, h);
	}
	
	public void drawSprite(int offset, int size, int x, int y) {
		size*=sheet.size;
		Image img = sheet.image.getSubimage(0, 0, size, size);
		g.drawImage(img, x, y, size, size, null);
	}
	
	public void drawGUI () {
		g.drawString("Gui is drawn", 10, 10);
	}

	public void dispose() {
		g.dispose();
	}
}
