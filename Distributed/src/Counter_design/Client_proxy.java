package Counter_design;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client_proxy implements CounterInterface, AutoCloseable{

    private final Socket s;
    private PrintWriter out;

    private BufferedReader in;

    public Client_proxy() throws IOException {
        InetAddress addr = InetAddress.getByName(null);
        s = new Socket(addr, Server.PORT);

        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())),true);
        }catch(IOException e) {
            e.printStackTrace();
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            s.close();
            throw e;
        }



    }

    public void reset() throws IOException {
        out.println("reset");
        in.readLine();
    }

    public void increment() throws IOException {
        out.println("incr");
        in.readLine();
    }

    public int value() throws IOException {
        out.println("get");
        return Integer.parseInt(in.readLine());
    }
    public void close() throws IOException {
        out.close();
        in.close();
        s.close();
    }
}
