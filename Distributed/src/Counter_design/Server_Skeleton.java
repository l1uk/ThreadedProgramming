package Counter_design;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server_Skeleton implements Runnable{

    final private Server_Counter c;
    final private Socket s;
    public Server_Skeleton(Server_Counter c, Socket s) {
        this.c = c;
        this.s = s;
    }

    @Override
    public void run() {
        System.out.println("Thread: "
                + Thread.currentThread().getName() +" connection accepted ");
        try(
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())),true)
        ){
            serveClient(in,out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void serveClient(BufferedReader in, PrintWriter out) throws IOException {
       String choice;
        while((choice = in.readLine()) != null){
            System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now()) + " Thread: "
                    + Thread.currentThread().getName() +"; Received instruction: " + choice);
            if(choice.equalsIgnoreCase("incr")){
                c.increment();
            }
            else if(choice.equalsIgnoreCase("reset")){
                c.reset();
            }
            else if(choice.equalsIgnoreCase("exit")){
                break;
            }
            else if(!choice.equalsIgnoreCase("get")){
                out.println("Operation not recognized");
            }
            // added this line here for synchronization
            out.println(c.value());
        }
    }
}
