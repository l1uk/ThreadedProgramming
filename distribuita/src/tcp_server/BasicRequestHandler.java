package tcp_server;

import java.io.*;
import java.net.Socket;

public class BasicRequestHandler implements Runnable {
    Socket connectionInterface;

    public BasicRequestHandler(Socket incomingConnection) {
        this.connectionInterface = incomingConnection;
    }

    public void run() {
        System.out.println("### REQEUST HANDLER ID=" + Thread.currentThread().getId()  +" ### Replied to " + connectionInterface.getInetAddress().getHostName());
        if (connectionInterface.isConnected() /* && !connectionInterface.isInputShutdown() */) {

            try (
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(connectionInterface.getInputStream())
                    );
                    PrintWriter out = new PrintWriter(
                            new BufferedWriter(
                                    new OutputStreamWriter(
                                            connectionInterface.getOutputStream()
                                    )
                            ),
                            true // Auto-flush
                    )
            ) {
                serve(in, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void serve(BufferedReader in, PrintWriter out) throws IOException{
        out.println("Hello!");
    }
}
