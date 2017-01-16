package cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

public class Decompressor {

	public static void decompress(String folderName, String idxName, String datName) {

		if (new File(idxName).exists() || new File(datName).exists()) {
			System.out.println("File Already exists");
			return;
		}

		int newID = 0;
		File[] files = new File(folderName).listFiles();
		try {

			RandomAccessFile indexFile = new RandomAccessFile(idxName, "rw");
			RandomAccessFile dataFile = new RandomAccessFile(datName, "rw");

			indexFile.writeInt(files.length);
			for (int i = 0; i < files.length; i++) {
				if (files[i].getAbsolutePath().contains("png") || files[i].getAbsolutePath().contains("jpg")) {
					byte[] data = Files.readAllBytes(files[i].toPath());
					dataFile.write(data);
					indexFile.writeInt(data.length);
					String name = getFileNameWithoutExtension(files[i].toString());
					indexFile.writeUTF(name);
					System.out.println("Data Written for " + data.length + " bytes [Img ID: " + newID + "] [Img Name: "
							+ name + "]");
					newID++;
				}
			}
			indexFile.close();
			dataFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getFileNameWithoutExtension(String fileName) {
		File tmpFile = new File(fileName);
		tmpFile.getName();
		int whereDot = tmpFile.getName().lastIndexOf('.');
		if (0 < whereDot && whereDot <= tmpFile.getName().length() - 2) {
			return tmpFile.getName().substring(0, whereDot);
		}
		return "";
	}

	public static byte[] fileToByteArray(File file) {
		try {
			final byte[] fileData = new byte[(int) file.length()];
			final FileInputStream fis = new FileInputStream(file);
			fis.read(fileData);
			fis.close();
			return fileData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
