package producerConsumer;

public class ProduttoriConsumatori {
    public static void main(String[] args) {
        Buffer<Integer> buf = new Buffer<Integer>(10);
        new Produttore(buf, "P1").start();
        new Produttore(buf, "P2").start();
        new Produttore(buf, "P3").start();
        new Produttore(buf, "P4").start();

        new Consumatore(buf, "C1").start();
        new Consumatore(buf, "C2").start();
        new Consumatore(buf, "C3").start();
        new Consumatore(buf, "C4").start();
        new Consumatore(buf, "C5").start();
        new Consumatore(buf, "C6").start();
        new Consumatore(buf, "C7").start();
    }
}
