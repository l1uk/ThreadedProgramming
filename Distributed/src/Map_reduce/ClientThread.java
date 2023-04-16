package Map_reduce;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ClientThread implements Runnable {

    InetAddress serverAddress;
    private RainData[] yearData;

    private CyclicBarrier barrier;

    private int port;

    public ClientThread(RainData[] yearData, CyclicBarrier barrier, int port, InetAddress serverAddress) {
        this.yearData = yearData;
        this.barrier = barrier;
        this.port = port;
        this.serverAddress = serverAddress;
    }

    @Override
    public void run() {

        try (Socket socket = new Socket(serverAddress, port)) {
            try (// this line gets stuck
                 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                try (ObjectOutput out = new ObjectOutputStream(socket.getOutputStream())) {
                    out.writeObject(yearData);
                    RainData maxMM = (RainData) in.readObject();
                    RainData maxGG = (RainData) in.readObject();
                    System.out.println(maxMM);
                    System.out.println(maxGG);

                    System.out
                            .println("ssad");
                    barrier.await();
                }
            }
        } catch (IOException | InterruptedException | BrokenBarrierException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out
                .println("ssad");
    }
}
