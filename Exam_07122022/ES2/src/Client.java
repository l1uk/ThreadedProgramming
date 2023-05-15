public class Client implements Runnable {
    private Nastro n;

    public Client(Nastro n) {
        this.n = n;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000 + (long) Math.random() * 10000);
                double rand = Math.random();
                if (rand < 0.5) {
                    n.write("Messsaggio di prova " + rand);
                } else if (rand < 0.9) {
                    n.rewind();
                } else {
                    System.out.println(n.read());
                }
            } catch (InterruptedException | Nastro.noWrite | Nastro.noRead e) {
                e.printStackTrace();
            }
        }
    }
}
