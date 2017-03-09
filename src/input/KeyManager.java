package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class KeyManager implements KeyListener {

	/**
	 * Variables to store the keys
	 */

	protected LinkedList<Integer> keys;
	protected int keyCode;
	protected String keyChar;

	/**
	 * Settings default values of the keys
	 */

	public KeyManager() {
		this.keys = new LinkedList<>();
		this.keyCode = -1;
		this.keyChar = "";
	}

	/**
	 * Returns true if the key specified is pressed
	 */

	public boolean isPressed(int keyCode) {
		for (Integer i : getKeys()) {
			if (i == keyCode) {
				return true;
			}
		}
		if (keyCode == -1) {
			return false;
		}
		return false;
	}

	/**
	 * Returns the key pressed as a string
	 */
	public String getKeyChar() {
		return keyChar;
	}

	/**
	 * Handles events on they keyboard, Used for multiple key supports
	 */

	public void keyPressed(KeyEvent e) {
		keyCode = e.getKeyCode();
		keyChar = Character.toString(e.getKeyChar());
		if (!keys.contains(new Integer(keyCode))) {
			keys.add(new Integer(keyCode));
		}
		//System.out.println(keys.toString());
	}

	/**
	 * When key is released, remove the key from the list
	 */

	public void keyReleased(KeyEvent e) {
		keys.remove(new Integer(e.getKeyCode()));
		keyChar = "";
		keyCode = -1;
	}

	public void keyTyped(KeyEvent e) {

	}

	/**
	 * Returns the key pressed as a code
	 */

	public int getKeyCode() {
		return keyCode;
	}

	public boolean isEmpty() {
		return this.keys.isEmpty();
	}

	public synchronized LinkedList<Integer> getKeys() {
		return this.keys;
	}

}