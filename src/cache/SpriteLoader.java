package cache;

import java.io.IOException;
import java.io.RandomAccessFile;

import graphics.Sprite;

public class SpriteLoader {

	private static RandomAccessFile indexFile, dataFile;
	private static Sprite[] sprites;

	public static Sprite[] getSprites() {
		return sprites;
	}

	public static Sprite[] loadSprites() {
		try {
			indexFile = new RandomAccessFile("Sprites.idx", "rw");
			dataFile = new RandomAccessFile("Sprites.dat", "rw");
			int length = indexFile.readInt();
			sprites = new Sprite[length];

			for (int i = 0; i < length; i++) {
				int byteLength = indexFile.readInt();
				String name = indexFile.readUTF();
				byte[] data = new byte[byteLength];

				dataFile.readFully(data);
				sprites[i] = new Sprite(data, name);
				System.out.println("Loaded: " + i + " " + name);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sprites;
	}

}
