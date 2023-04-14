package Counter_design;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Client implements Runnable{

    private final Operation op;

    final int nIncrements = 1000;

    private CyclicBarrier barrier = null;
    public Client(Operation op) {
        this.op = op;
    }

    public Client(Operation op, CyclicBarrier barrier) {
        this.op = op;
        this.barrier = barrier;

    }
    @Override
    public void run() {
        try(Client_proxy proxy = new Client_proxy()){
            switch (this.op) {
                case RESET -> proxy.reset();
                case INCREMENT -> {

                        for (int i = 0; i < nIncrements; i += 1) {
                            proxy.increment();
                        }

                }
                case GET -> System.out.println(Thread.currentThread().getName() + ", Value: " + proxy.value());
            }
        } catch (IOException  e) {
            e.printStackTrace();
        }
        if(barrier != null) {
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
