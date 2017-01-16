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

import entity.Player;
import exceptions.GameException;
import graphics.Camera;
import graphics.GameScreen;
import input.KeyManager;

public class Client extends JPanel {

	private Game game;
	private static final long serialVersionUID = 1L;

	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnConnect;
	private JFrame frame;

	private KeyManager keyManager;

	private GameScreen gameScreen;
	private Camera camera;

	public Client() {
		this.keyManager = new KeyManager();
		//Decompressor.decompress("Textures", "Textures.idx", "Textures.dat");
		//Decompressor.decompress("Sprites", "Sprites.idx", "Sprites.dat");
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
				game = new Game(getUsername(), getPassword(), this);
				if (game.isActive) {
					frame.setContentPane(this);
					frame.setSize(450 * 2, 300 * 2);
					frame.setLocationRelativeTo(null);
					frame.validate();
					frame.repaint();
					frame.toFront();
					frame.setState(JFrame.NORMAL);
					frame.setVisible(true);
					gameScreen = new GameScreen(frame.getWidth(), frame.getHeight());
					camera = new Camera(this.getWidth(), this.getHeight(), 5000, 5000);
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
		frame.addKeyListener(keyManager);

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

		Player p = game.getPlayer();
		camera.update(p.getxPos(), p.getyPos());

		int xPos = camera.getxPos();
		int yPos = camera.getyPos();

		g2.translate(-xPos, -yPos);

		if (gameScreen != null) {
			gameScreen.render(g);
		}

		if (game != null) {
			game.render(g2);
		}

		g2.translate(xPos, yPos);
	}

	public void setTitle(String title) {
		this.frame.setTitle(title);
	}

	public static void main(String[] args) {
		new Client();
	}
}
