package server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerManager {
	public static ServerSocket listener = null;

	public static void main(String[] args) throws IOException {
		listener = new ServerSocket(17777);
		new Thread(new BasicServer(listener)).start();

	}

	protected void finalize() {
		// Objects created in run method are finalized when
		// program terminates and thread exits
		try {
			System.out.println("Closing socket");
			listener.close();
		} catch (IOException e) {
			System.out.println("Could not close socket");
			System.exit(-1);
		}
	}

}
