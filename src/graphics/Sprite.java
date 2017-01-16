package graphics;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite extends Drawable {

	protected BufferedImage image;
	protected int width;
	protected int height;

	public Sprite(byte[] data) {
		super(data);
	}

	public BufferedImage getImage() {
		return this.image;
	}

	public Sprite(byte[] data, String name) {
		super(data, name);
		try {
			this.image = ImageIO.read(new ByteArrayInputStream(data));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.width = image.getWidth();
		this.height = image.getHeight();
	}

}
