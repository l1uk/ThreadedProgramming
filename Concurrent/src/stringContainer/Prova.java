package stringContainer;

public class Prova {
    static final int numUtenti = 20;
    static final int contDim = 5;

    public static void main(String[] args) throws InterruptedException {
        Contenitore c = new ImplContenitore(contDim);
        for (int i = 0; i <= numUtenti; i += 1) {
            new RandomReader(i, c).start();
            Thread.sleep(100 + (long) (Math.random() * 1000));
            new Utente(i, c).start();
            Thread.sleep(1000 + (long) (Math.random() * 10000));
            new RandomReader(i * numUtenti + 1, c).start();
        }

    }
}
