package Counter_design;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client_proxy implements CounterInterface, AutoCloseable{

    private Socket s;
    private PrintWriter out;

    private BufferedReader in;

    public Client_proxy() throws IOException {
        InetAddress addr = InetAddress.getByName(null);
        System.out.println("addr = " + addr);
        s = new Socket(addr, Server.PORT);
        System.out.println("socket = " + s);

        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())),true);
        }catch(IOException e) {
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

    @Override
    public void reset() {
        out.println("reset");
    }

    @Override
    public void increment() {
        out.println("incr");
    }

    @Override
    public int value() throws IOException {
        out.println("get");
        //sistemare
        System.out.println(in.readLine());

        return Integer.parseInt("1");
    }
    public void close() throws IOException {
        System.out.println("Closing...");
        out.close();
        in.close();
        s.close();
    }
}
