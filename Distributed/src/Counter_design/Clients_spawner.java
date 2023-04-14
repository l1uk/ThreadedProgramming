package Counter_design;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CyclicBarrier;

public class Clients_spawner {

    final static int nClient = 1000;

    public static void main(String[] args) throws InterruptedException {

        CyclicBarrier b = new CyclicBarrier(nClient, () -> {
            System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now()) +" Barrier Broken");
            new Thread(new Client(Operation.GET)).start();
        });

        Thread t = new Thread(new Client(Operation.RESET));

        t.start();
        t.join();

        t = new Thread(new Client(Operation.GET));

        t.start();
        t.join();

        for(int i = 0 ; i < nClient ; i++) {
            new Thread(new Client(Operation.INCREMENT, b)).start();
        }

    }
}
