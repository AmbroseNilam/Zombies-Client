package graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture extends Drawable {

	protected static final int HORIZONTAL = 0;
	protected static final int VERTICAL = 1;
	protected BufferedImage image;
	protected int width;
	protected int height;

	public Texture(byte[] data, String name) {
		super(data, name);
		try {
			this.image = ImageIO.read(new ByteArrayInputStream(data));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.width = image.getWidth();
		this.height = image.getHeight();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Texture(byte[] data) {
		super(data);
	}

	public BufferedImage getImage() {
		return this.image;
	}

	public static void renderTiles(Texture tile, int count, int startX, int startY, int dir, Graphics g) {
		int xPos = startX;
		int yPos = startY;

		for (int i = 0; i < count; i++) {
			if (dir == HORIZONTAL) {
				g.drawImage(tile.getImage(), xPos, yPos, null);
				xPos += tile.getImage().getWidth();
			} else {
				g.drawImage(tile.getImage(), xPos, yPos, null);
				yPos += tile.getImage().getWidth();
			}
		}
	}
}
