package graphics;

import java.awt.Graphics;

import cache.TextureLoader;
import cache.TileLoader;
import constants.GameConstants;

public class GameScreen {
	protected Texture[] textures;
	protected Sprite[] sprites;
	protected final int width;
	protected final int height;
	protected float cameraX;
	protected float cameraY;

	protected Tile[][] map;

	protected final int TILE_W = (GameConstants.SCREEN_WIDTH / 32) + 1;
	protected final int TILE_H = ((GameConstants.SCREEN_HEIGHT + 10) / 32);

	public GameScreen(final int width, final int height) {
		this.textures = TextureLoader.loadTextures();
		this.map = TileLoader.loadTiles();
		//sprites = SpriteLoader.loadSprites();
		this.width = width;
		this.height = height;
	}

	public void render(Graphics g) {

		int startX = (int) (this.cameraX / 32F);
		int startY = (int) (this.cameraY / 32F);

		int endX = startX + TILE_W;
		int endY = startY + TILE_H;

		int mapX = startX * 32;

		for (int i = startX; i < endX; i++) {
			int mapY = startY * 32;
			for (int j = startY; j < endY; j++) {
				g.drawImage(textures[this.map[i][j].getTextureType()].getImage(), mapX, mapY, 31, 31, null);
				mapY += 32;
			}
			mapX += 32;
		}
	}

	public void update() {

	}

	public float getCameraX() {
		return cameraX;
	}

	public void setCameraX(float cameraX) {
		this.cameraX = cameraX;
	}

	public float getCameraY() {
		return cameraY;
	}

	public void setCameraY(float cameraY) {
		this.cameraY = cameraY;
	}
}
