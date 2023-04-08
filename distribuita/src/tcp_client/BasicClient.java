package tcp_client;

import java.io.*;
import java.net.Socket;

public class BasicClient implements Runnable {
    Socket socket;

    public BasicClient(String host, int port) throws IOException {
        super();
        this.socket = new Socket(host, port);
    }

    public void run() {
        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                PrintWriter out = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream())
                        ),
                        true // Auto-flush
                )
        ) {
            String receive;
            while ((receive = in.readLine()) != null) {
                System.out.println(receive);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }


            System.out.println("##### Client " + Thread.currentThread().getId() + " ended");

    }
}
