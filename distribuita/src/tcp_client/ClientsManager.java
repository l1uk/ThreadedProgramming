package tcp_client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientsManager {
	public static String host = "localhost";

	public static void main(String[] args) throws IOException, InterruptedException {

		InetAddress addr = InetAddress.getByName(host);

		Socket s = new Socket(addr, 17777);

		Integer clientCounter = 0;
		for(int i = 0 ; i < 100 ; i++)
			new Thread(new BasicClient(s)).start();

	}
}
