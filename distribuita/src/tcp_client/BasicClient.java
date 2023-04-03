package tcp_client;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class BasicClient implements Runnable {
	Socket s;

	public BasicClient(Socket s) {
		super();
		this.s = s;
	}
	public void run() {
		DataInputStream in = null;
		PrintWriter out = null;
		try {
			in = new DataInputStream(s.getInputStream());

			out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));

			while (true) // ripeti all'infinito
				try {
					int val = in.readInt();
					System.out.println("Read: " + val);
				} catch (EOFException e) { // gestisci l'eccezione eof
					break; // esci dal ciclo
				}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				System.out.println("Client " + Thread.currentThread().getId() + " ended");
		}
	}
}
