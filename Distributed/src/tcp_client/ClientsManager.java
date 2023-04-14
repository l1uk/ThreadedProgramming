package tcp_client;

import java.io.IOException;

public class ClientsManager {
    public static final String HOST = "localhost";
    public static final int PORT = 17777;

    public static void main(String[] args) throws IOException, InterruptedException {
        Integer clientCounter = 0;
        for (int i = 0; i < 100; i++)
            new Thread(new BasicClient(HOST,PORT)).start();

    }
}
