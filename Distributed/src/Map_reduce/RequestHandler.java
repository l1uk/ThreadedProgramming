package Map_reduce;

import tcp_server.BasicRequestHandler;

import java.io.*;
import java.net.Socket;

public class RequestHandler extends BasicRequestHandler {

    RainData[] yearData;

    public RequestHandler(Socket incomingConnection) {
        super(incomingConnection, true);
    }


    public void serve(InputStream in, OutputStream out) throws IOException {
        RainData maxGG = null, maxMM = null;
        try (
                ObjectInput is = new ObjectInputStream(in);
                ObjectOutput os = new ObjectOutputStream(out)
        ) {
            yearData = (RainData[]) is.readObject();


            for (RainData month : yearData) {
                if (maxGG == null || month.getConsecutiveDays() > maxGG.getConsecutiveDays()) {
                    maxGG = month;
                }
                if (maxMM == null || month.getMm() > maxMM.getMm()) {
                    maxMM = month;
                }
            }

            os.writeObject(maxMM);

            os.writeObject(maxGG);

        } catch (ClassNotFoundException | EOFException e) {
            e.printStackTrace();
        }


    }
}
