package graphics;

public class Camera {
	protected int xPos;
	protected int yPos;
	protected int screenWidth;
	protected int screenHeight;
	protected int gameWidth;
	protected int gameHeight;

	public Camera(int screenWidth, int screenHeight, int gameWidth, int gameHeight) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.gameHeight = gameHeight;
		this.gameWidth = gameWidth;
		this.xPos = 0;
		this.yPos = 0;
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

	public int getGameWidth() {
		return gameWidth;
	}

	public void setGameWidth(int gameWidth) {
		this.gameWidth = gameWidth;
	}

	public int getGameHeight() {
		return gameHeight;
	}

	public void setGameHeight(int gameHeight) {
		this.gameHeight = gameHeight;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public void update(int playerX, int playerY) {
		if (this.xPos < 0) {
			this.xPos = 0;
		}
		if (this.yPos < 0) {
			this.yPos = 0;
		}
		if (this.xPos > this.gameWidth - screenWidth) {
			this.xPos = this.gameWidth - screenWidth;
		}
		if (this.yPos > this.gameHeight - screenHeight) {
			this.yPos = this.gameHeight - screenHeight;
		}

		this.xPos = playerX - screenWidth / 2;
		this.yPos = playerY - screenHeight / 2;
	}

}
