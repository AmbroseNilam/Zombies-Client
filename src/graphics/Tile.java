package graphics;

public class Tile {

	protected int xPos;
	protected int yPos;
	protected int textureType;

	public Tile(int xPos, int yPos, int textureType) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.textureType = textureType;
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

	public int getTextureType() {
		return textureType;
	}

	public void setTextureType(int textureType) {
		this.textureType = textureType;
	}

}
