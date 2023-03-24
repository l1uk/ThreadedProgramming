package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientsManager {
	public static String host = "localhost";

	public static void main(String[] args) throws IOException {

		InetAddress addr = InetAddress.getByName(host);

		Socket s = new Socket(addr, 17777);

		new Thread(new BasicClient(s)).start();

	}
}
