package graphics;

import java.awt.Graphics;

import cache.TextureLoader;

public class GameScreen {
	protected Texture[] textures;
	protected Sprite[] sprites;
	protected final int width;
	protected final int height;

	public GameScreen(final int width, final int height) {
		textures = TextureLoader.loadTextures();
		//sprites = SpriteLoader.loadSprites();
		this.width = width;
		this.height = height;
	}

	public void update() {

	}

	public void render(Graphics g) {
		int y = 0;
		for (int i = 0; i < (int) (height / textures[1].getHeight() + 50); i++) {
			Texture.renderTiles(textures[1], (int) (width / textures[1].getWidth()) + 50, 0, y, Texture.HORIZONTAL, g);
			y += textures[1].getHeight();
		}
		//g.drawImage(sprites[0].getImage(), 40, 40, null);
		//g.drawImage(textures[1].getImage(), 0, 0, null);
	}
}
