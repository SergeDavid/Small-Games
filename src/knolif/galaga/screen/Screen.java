package knolif.galaga.screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class Screen {
	private SpriteSheet sheet;
	public int width;
	public int height;
	public int position;//TODO: Decide if this is the top or bottom of the playable area
	public int speed;
	private Graphics2D g;
	
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
		int xx = sheet.getX(offset, size);
		int yy = sheet.getY(offset, size);
		Image img = sheet.image.getSubimage(xx, yy, size, size);
		g.drawImage(img, x, y, size, size, null);
	}
	
	public void drawGUI () {
		g.setColor(Color.white);
		g.drawString("Gui is drawn", 10, 10);
	}

	public void dispose() {
		g.dispose();
	}

	public boolean OnScreen(int x, int y) {
		return !(x < 0 || x > width || y < position || y > position+height);
	}
}
