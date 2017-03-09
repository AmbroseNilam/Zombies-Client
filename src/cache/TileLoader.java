package cache;

import java.io.IOException;
import java.io.RandomAccessFile;

import graphics.Tile;

public class TileLoader {

	public static Tile[][] loadTiles() {
		Tile[][] grid = new Tile[30 * 4][20 * 4];
		try {
			RandomAccessFile map = new RandomAccessFile("Map.dat", "r");
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					int textureType = map.readInt();
					int xPos = map.readInt();
					int yPos = map.readInt();
					System.out.printf("Texture %d at (%d, %d)\n", textureType, xPos, yPos);
					grid[xPos][yPos] = new Tile(xPos, yPos, textureType);
				}
			}
			map.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return grid;
	}
}
