package Counter_design;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int PORT = 7777;

    public static void main(String[] args) throws IOException {
        Server_Counter c = new Server_Counter();
        try (
                ServerSocket serverSocket = new ServerSocket(PORT)
        ) {
            while (true) {
                Socket s = serverSocket.accept();
                new Thread(new Server_Skeleton(c, s)).start();
            }
        }
    }
}
