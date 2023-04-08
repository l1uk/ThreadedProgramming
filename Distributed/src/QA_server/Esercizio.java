package QA_server;

import tcp_server.BasicServer;

import java.io.IOException;
import java.net.ServerSocket;

public class Esercizio {
    public static final Integer PORT = 7776;

    public static void main(String[] args) throws IOException {
        try (ServerSocket sock = new ServerSocket(PORT)) {
            new BasicServer(sock, RequestHandler.class).run();
            System.out.println("Server up and running...");
        }

    }
}
