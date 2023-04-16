package tcp_server;

import java.io.*;
import java.net.Socket;

public class BasicRequestHandler implements Runnable {
    final Socket connectionInterface;

    boolean wantsStream = false;

    public BasicRequestHandler(Socket incomingConnection) {
        this.connectionInterface = incomingConnection;
    }

    public BasicRequestHandler(Socket connectionInterface, boolean wantsStream) {
        this.connectionInterface = connectionInterface;
        this.wantsStream = wantsStream;
    }

    public void run() {
        System.out.println("### REQEUST HANDLER ID=" + Thread.currentThread().getName() + " ### Replied to " + connectionInterface.getInetAddress().getHostName());
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
                if (!wantsStream)
                    serve(in, out);
                else
                    serve(connectionInterface.getInputStream(),
                            connectionInterface.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void serve(BufferedReader in, PrintWriter out) throws IOException {
        out.println("Hello!");
    }

    public void serve(InputStream in, OutputStream out) throws IOException {
    }
}
