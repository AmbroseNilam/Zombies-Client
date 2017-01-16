package exceptions;

import javax.swing.JOptionPane;

import constants.Error;

public class GameException extends Exception {

	private static final long serialVersionUID = 5658812044501801142L;

	private Error error;

	public GameException(Error error) {
		super(error.getMessage());
		this.error = error;
	}

	public void displayError() {
		JOptionPane.showMessageDialog(null, this.error.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
	}

}
