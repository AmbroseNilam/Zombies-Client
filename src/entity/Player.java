package entity;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	private int xPos, yPos;

	public Player() {
		this.xPos = 0;
		this.yPos = 0;
	}

	public Player(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public void setPosition(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
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

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(xPos, yPos, 32, 32);
	}

}
