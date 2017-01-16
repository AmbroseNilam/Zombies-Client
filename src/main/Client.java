package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import exceptions.GameException;
import input.KeyManager;

public class Client extends JPanel {

	private Game game;
	private static final long serialVersionUID = 1L;

	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnConnect;
	private JFrame frame;

	private KeyManager keyManager;

	public Client() {
		txtUsername = new JTextField(15);
		txtUsername.setPreferredSize(new Dimension(150, 30));
		txtUsername.setMargin(new Insets(5, 5, 5, 5));
		txtUsername.setHorizontalAlignment(JTextField.CENTER);

		txtPassword = new JPasswordField(15);
		txtPassword.setPreferredSize(new Dimension(150, 30));
		txtPassword.setMargin(new Insets(5, 5, 5, 5));
		txtPassword.setHorizontalAlignment(JTextField.CENTER);

		btnConnect = new JButton("Connect");

		btnConnect.addActionListener((e) -> {
			try {
				this.keyManager = new KeyManager();
				game = new Game(getUsername(), getPassword(), this);
				if (game.isActive) {
					frame.addKeyListener(keyManager);
					frame.setContentPane(this);
					frame.setSize(450, 300);
					frame.setLocationRelativeTo(null);
					frame.validate();
					frame.repaint();
					frame.toFront();
					frame.setState(JFrame.NORMAL);
					frame.setVisible(true);
				}

			} catch (GameException err) {
				err.displayError();
			}
		});

		JLabel lblCreate = new JLabel("<HTML><U>Create Account</U></HTML>");

		frame = new JFrame("Zombies");
		JPanel userPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		gc.insets = new Insets(10, 10, 0, 10);
		gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.WEST;
		gc.gridx = 1;
		gc.gridy = 0;
		userPanel.add(new JLabel("Username:"), gc);
		gc.gridx = 2;
		userPanel.add(txtUsername, gc);
		gc.gridx = 1;
		gc.gridy = 1;
		userPanel.add(new JLabel("Password:"), gc);
		gc.gridx = 2;
		userPanel.add(txtPassword, gc);
		gc.gridx = 0;
		gc.gridy = 2;
		userPanel.add(lblCreate, gc);
		gc.gridx = 3;
		gc.anchor = GridBagConstraints.EAST;
		userPanel.add(btnConnect, gc);

		frame.setContentPane(userPanel);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void update() {
		repaint();
	}

	public String getUsername() {
		return txtUsername.getText();
	}

	public String getPassword() {
		return new String(txtPassword.getPassword());
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (game != null) {
			game.render(g2);
		}
	}

	public void setTitle(String title) {
		this.frame.setTitle(title);
	}

	public static void main(String[] args) {
		new Client();
	}
}
