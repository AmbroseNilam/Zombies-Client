package cache;

import java.io.IOException;
import java.io.RandomAccessFile;

import graphics.Texture;

public class TextureLoader {

	public static Texture[] textures;

	public static Texture[] getTextures() {
		return textures;
	}

	public static Texture[] loadTextures() {
		try {
			RandomAccessFile indexFile = new RandomAccessFile("Textures.idx", "rw");
			RandomAccessFile dataFile = new RandomAccessFile("Textures.dat", "rw");
			int length = indexFile.readInt();
			textures = new Texture[length];

			for (int i = 0; i < length; i++) {
				int byteLength = indexFile.readInt();
				String name = indexFile.readUTF();
				byte[] data = new byte[byteLength];

				dataFile.readFully(data);
				textures[i] = new Texture(data, name);
				System.out.println("Loaded: " + i + " " + name);
			}

			indexFile.close();
			dataFile.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return textures;
	}

}
