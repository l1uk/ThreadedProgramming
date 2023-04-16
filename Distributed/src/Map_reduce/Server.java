package Map_reduce;

import tcp_server.BasicServer;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public static int PORT = 7777;
    public static void main(String[] args) throws IOException {
        try (ServerSocket sock = new ServerSocket(PORT)) {
            new BasicServer(sock, RequestHandler.class).run();
            System.out.println("Server up and running...");
        }
    }

}
