package stringContainer;

public class ProvaEs2 {
    static final int numUtenti = 20;
    static final int contDim = 5;

    public static void main(String[] args) throws InterruptedException {
        ContenitoreEs2 c = new ImplContenitoreEs2(contDim);
        for (int i = 0; i <= numUtenti; i += 1) {
            new RandomReaderEs2(i, c).start();
            Thread.sleep(100 + (long) (Math.random() * 1000));
            new UtenteEs2(i, c).start();
            Thread.sleep(1000 + (long) (Math.random() * 10000));
            new RandomReaderEs2(i * numUtenti + 1, c).start();
            Thread.sleep(100 + (long) (Math.random() * 1000));
            new UtenteEs2(i * numUtenti + 1, c).start();
        }

    }
}
