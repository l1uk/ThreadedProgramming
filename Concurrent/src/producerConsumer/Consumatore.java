package producerConsumer;

public class Consumatore extends Thread {
    private final Buffer buf;
    private final String name;

    public Consumatore(Buffer b, String name) {
        super(name);
        this.name = name;
        buf = b;
    }

    public void run() {
        while (true) {
            try {
                sleep(200);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                try {
                    int v = (int) buf.get();
                    System.out.println("Cons " + name + " " + v);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            System.out.println("Cons Terminato");
        }
    }
}
