package Map_reduce;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientReduce implements Runnable {

    private InetAddress serverAddress;
    private int port;
    private List<RainData> rainDataMM;
    private List<RainData> rainDataGG;

    ClientReduce(InetAddress serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;

        this.rainDataMM = new ArrayList<RainData>();
        this.rainDataGG = new ArrayList<RainData>();

    }

    public synchronized void addRainDataMM(RainData data) {
        this.rainDataMM.add(data);
    }

    public synchronized void addRainDataGG(RainData data) {
        this.rainDataGG.add(data);
    }

    private RainData queryServer(RainData[] data, boolean MM) {
        try (Socket socket = new Socket(serverAddress, port)) {
            try (ObjectOutput out = new ObjectOutputStream(socket.getOutputStream()
            )) {
                try (
                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ) {
                    out.writeObject(data);
                    RainData maxMM = (RainData) in.readObject();
                    RainData maxGG = (RainData) in.readObject();
                    if (MM)
                        return maxMM;
                    else
                        return maxGG;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        RainData maxMM = queryServer((RainData[])
                rainDataMM.toArray(new RainData[0]), true);
        RainData maxGG = queryServer((RainData[])
                rainDataGG.toArray(new RainData[0]), false);

        System.out.println("Month with most rain MM: "  + maxMM);

        System.out.println("Month with most consecutive rain days: "  + maxGG);

    }

}
