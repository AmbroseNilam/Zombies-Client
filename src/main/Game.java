package main;

import java.awt.Graphics;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

import constants.Error;
import entity.Player;
import exceptions.GameException;
import input.KeyManager;
import net.PacketBuilder;
import net.PacketReader;

/**
 * Authenticates user to server and simultaneously shares the data to be
 * rendered across the {@link Client} class
 *
 * @author Ambrose
 */

public class Game implements Runnable {

	public Player myPlayer;

	private DatagramSocket gameSocket;
	private DatagramPacket serverPacket;

	private InetAddress address;

	private final int PORT = 7777;
	protected String username;

	public static final int FPS = 30;
	private Client client;

	protected HashMap<Integer, Player> players;

	protected long ping = 0;

	public boolean isActive = false;

	public Game(String username, String password, Client client) throws GameException {
		if (username.isEmpty() || password.isEmpty()) {
			throw new GameException(Error.INPUT_ERROR);
		}

		int timeout = 1;
		while (timeout != 8) {
			try {
				//address = InetAddress.getByName("174.117.225.193");
				address = InetAddress.getByName("localhost");
				gameSocket = new DatagramSocket();
				gameSocket.setSoTimeout(3000);

				this.sendData(PacketBuilder.buildGamePacket((byte) 0, username, password).getData());

				byte[] response = this.readData();

				if (response[0] == (byte) 0) {
					throw new GameException(Error.AUTHENTICATION_ERROR);
				}

				this.client = client;
				this.username = username;
				this.myPlayer = new Player();
				this.players = new HashMap<>();
				this.myPlayer = new Player(0, 0);
				this.players.put(gameSocket.getLocalPort(), this.myPlayer);
				this.isActive = true;

				new Thread(this).start();
				gameSocket.setSoTimeout(0);
				break;

			} catch (UnknownHostException e) {
				throw new GameException(Error.OFFLINE_ERROR);
			} catch (IOException e) {
				client.setTitle("Unable to connect ... retrying (" + timeout + ")");
				timeout++;
			}
		}

		if (timeout == 8) {
			client.setTitle("Zombies Login");
			throw new GameException(Error.RESPONSE_ERROR);
		} else {
			client.setTitle("Zombies");

		}

	}

	public synchronized HashMap<Integer, Player> getPlayers() {
		return this.players;
	}

	@Override
	public void run() {
		while (isActive) {

			long time = System.currentTimeMillis();

			update();
			draw();

			time = (1000 / FPS) - (System.currentTimeMillis() - time);

			if (time > 0) {
				try {
					Thread.sleep(time);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Player getPlayer() {
		return myPlayer;
	}

	public byte[] readData() throws IOException {
		byte[] receiveData = new byte[256];
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		gameSocket.receive(receivePacket);
		return receivePacket.getData();
	}

	public void sendData(byte[] data) throws IOException {
		byte[] buf = data;
		if (serverPacket == null) {
			serverPacket = new DatagramPacket(buf, buf.length, address, PORT);
			gameSocket.send(serverPacket);
			return;
		}
		serverPacket.setData(buf);
		gameSocket.send(serverPacket);
	}

	public void update() {
		try {
			KeyManager km = client.getKeyManager();
			long start = System.nanoTime();
			PacketBuilder pb = new PacketBuilder(new byte[64]);
			pb.writeByte((byte) 02);
			pb.writeShort((short) km.getKeys().size());
			for (Integer i : km.getKeys()) {
				pb.writeShort((short) i.intValue());
			}

			byte[] data = pb.getData();

			this.sendData(data);
			int size = (int) this.readData()[0];
			long end = System.nanoTime();
//			System.out.println("Took: " + ((end - start) / 1000000) + "ms " + " to send " + " bytes");
			System.out.println(client.getWidth());
			for (int i = 0; i < size; i++) {
				PacketReader pr = new PacketReader(this.readData());
				int port = pr.readInt();
				int xPos = (int) pr.readShort();
				int yPos = (int) pr.readShort();

				if (!getPlayers().containsKey(new Integer(port))) {
					getPlayers().put(port, new Player(xPos, yPos));
					continue;
				}

				getPlayers().get(port).setPosition(xPos, yPos);
			}

			client.update();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void draw() {

	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void render(Graphics g) {
		players.forEach((k, v) -> {
			v.render(g);
		});
	}

}
