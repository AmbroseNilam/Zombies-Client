package graphics;

import constants.GameConstants;

public class Camera {
	protected float xPos;
	protected float yPos;
	protected int screenWidth;
	protected int screenHeight;

	public Camera(int screenWidth, int screenHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.xPos = 0F;
		this.yPos = 0F;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public float getxPos() {
		return xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public void update(int playerX, int playerY) {

		this.xPos += (float) (((playerX - (screenWidth / 2)) - this.xPos) * 0.1);
		this.yPos += (float) (((playerY - (screenHeight / 2)) - this.yPos) * 0.1);

		System.out.printf("Player Pos: (%d, %d) - Camera Pos: (%f, %f)\n", playerX, playerY, this.xPos, this.yPos);

		if (this.xPos <= GameConstants.CAMERA_PIVOT_X) {
			this.xPos = GameConstants.CAMERA_PIVOT_X;
		}

		if (this.yPos <= GameConstants.CAMERA_PIVOT_Y) {
			this.yPos = GameConstants.CAMERA_PIVOT_Y;
		}

		if (this.xPos > ((GameConstants.MAP_WIDTH - screenWidth) - 1)) {
			this.xPos = ((GameConstants.MAP_WIDTH - screenWidth) - 1);
		}

		if (this.yPos > ((GameConstants.MAP_HEIGHT - screenHeight) - 1)) {
			this.yPos = ((GameConstants.MAP_HEIGHT - screenHeight) - 1);
		}

	}

}
