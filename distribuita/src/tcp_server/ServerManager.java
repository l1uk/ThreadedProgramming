package tcp_server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerManager {
	public static ServerSocket listener = null;

	public static void main(String[] args) throws IOException {
		listener = new ServerSocket(17777);
		new Thread(new BasicServer(listener)).start();

	}


}
