package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class BasicRequestHandler implements Runnable {
	Socket connectionInterface;

	public BasicRequestHandler(Socket incomingConnection) {
		this.connectionInterface = incomingConnection;
	}

	public void run() {
		BufferedReader in = null;
		DataOutputStream out = null;
		System.out.println("Replied to " + connectionInterface.getInetAddress().getHostName());
		if (connectionInterface.isConnected() /* && !connectionInterface.isInputShutdown() */) {

			try {
				out = new DataOutputStream(connectionInterface.getOutputStream());
				in = new BufferedReader(new InputStreamReader(connectionInterface.getInputStream()));
				out.writeInt(123);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (in != null)
						in.close();
					if (out != null)
						out.close();
					if (connectionInterface != null && !connectionInterface.isClosed())
						connectionInterface.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
}