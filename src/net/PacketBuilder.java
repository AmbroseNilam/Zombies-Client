package net;

public class PacketBuilder {
	protected byte[] data;
	protected int count;

	public PacketBuilder(byte[] data) {
		this.data = data;
		this.count = 0;
	}

	public static PacketBuilder buildGamePacket(byte packetType, int... data) {
		PacketBuilder pb = new PacketBuilder(new byte[1 + (4 * data.length)]);
		pb.writeByte(packetType);
		for (int i : data) {
			pb.writeInt(i);
		}
		return pb;
	}

	public static PacketBuilder buildGamePacket(byte packetType, String... data) {
		PacketBuilder pb = new PacketBuilder(new byte[1 + (String.join("", data).length()) + data.length]);
		pb.writeByte(packetType);
		for (String i : data) {
			pb.writeUTF(i);
		}
		return pb;
	}

	public void writeShort(short value) {
		this.data[count++] = (byte) (value >> 8);
		this.data[count++] = (byte) (value);
	}

	public void writeInt(int value) {
		this.data[count++] = (byte) (value >> 24);
		this.data[count++] = (byte) (value >> 16);
		this.data[count++] = (byte) (value >> 8);
		this.data[count++] = (byte) (value);
	}

	public void writeByte(byte value) {
		this.data[count++] = value;
	}

	public void writeUTF(String s) {
		byte[] array = s.getBytes();
		for (byte b : array) {
			this.data[count++] = b;
		}
		this.data[count++] = (byte) ((int) '\0');
	}

	public byte[] getData() {
		byte[] toReturn = new byte[this.count];
		System.arraycopy(this.data, 0, toReturn, 0, this.count);
		return toReturn;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (byte b : getData()) {
			sb.append(String.format("[%d] ", b));
		}
		return sb.toString();
	}
}
