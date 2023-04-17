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

    private InetAddress serverAddress;
    private int port;
    private RainData[] yearData;

    private CyclicBarrier barrier;


    private ClientReduce reducer;

    public ClientThread( InetAddress serverAddress, int port, RainData[] yearData, CyclicBarrier barrier, ClientReduce reducer ) {
        this.serverAddress = serverAddress;
        this.port = port;
        this.yearData = yearData;
        this.barrier = barrier;
        this.reducer = reducer;
    }

    @Override
    public void run() {

        try (Socket socket = new Socket(serverAddress, port)) {
            try (ObjectOutput out = new ObjectOutputStream(socket.getOutputStream()
            )) {
                try (
                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ) {
                    out.writeObject(yearData);
                    RainData maxMM = (RainData) in.readObject();
                    RainData maxGG = (RainData) in.readObject();

                    reducer.addRainDataMM(maxMM);
                    reducer.addRainDataGG(maxGG);

                    barrier.await();
                }
            }
        } catch (IOException | InterruptedException | BrokenBarrierException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
