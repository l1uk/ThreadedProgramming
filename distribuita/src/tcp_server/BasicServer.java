package tcp_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BasicServer implements Runnable {

	ServerSocket listener;

	public BasicServer(ServerSocket listener) {
		this.listener = listener;
	}

	@Override
	public void run() {
		System.out.println("Server " + Thread.currentThread().getId() + " Listening on " + listener.getLocalPort());
		while (true) {
			Socket incomingConnection = null;
			try {
				incomingConnection = listener.accept();
				new Thread(new BasicRequestHandler(incomingConnection)).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}