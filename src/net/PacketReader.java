package net;

public class PacketReader {
	protected byte[] data;
	protected int count;

	public PacketReader(byte[] data) {
		this.data = data;
		this.count = 0;
	}

	public int readInt() {
		return this.data[count++] << 24 | (this.data[count++] & 0xFF) << 16 | (this.data[count++] & 0xFF) << 8
				| (this.data[count++] & 0xFF);
	}

	public short readShort() {
		return (short) ((short) (this.data[count++] & 0xFF) << 8 | (this.data[count++] & 0xFF));
	}

	public byte readByte() {
		return this.data[count++];
	}

	public String readUTF() {
		byte[] arr = new byte[256];
		int counter = 0;

		byte b = readByte();

		while (b != (byte) 0) {
			arr[counter++] = b;
			b = readByte();
		}

		return new String(arr, 0, counter);

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (byte b : this.data) {
			sb.append(String.format("[%d] ", b));
		}
		return sb.toString();
	}
}
