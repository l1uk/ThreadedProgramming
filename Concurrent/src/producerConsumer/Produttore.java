package producerConsumer;

public class Produttore extends Thread {
    private final Buffer buf;
    private final String name;

    public Produttore(Buffer b, String name) {
        super(name);
        this.name = name;
        buf = b;
    }

    public void run() {
        while (true) {

            for (int i = 0; i < 10; i++) {
                try {
                    sleep(80);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    buf.insert(i);
                    System.out.println("Prod " + name + " " + i);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            System.out.println("Prod Terminato");
        }
    }
}
