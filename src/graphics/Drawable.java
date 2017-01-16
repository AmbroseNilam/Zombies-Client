package graphics;

public class Drawable {
	private byte[] data;
	private String name;

	public Drawable(byte[] data) {
		this.data = data;
	}

	public Drawable(byte[] data, String name) {
		this.data = data;
		this.name = name;
	}

	public byte[] getImageData() {
		return data;
	}

	public String getImageName() {
		return name;
	}

}
