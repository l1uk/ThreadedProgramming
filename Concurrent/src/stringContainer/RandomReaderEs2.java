package stringContainer;

public class RandomReaderEs2 extends Thread {
    private static final int numLetture = 10;
    private final ContenitoreEs2 c;
    private final int id;

    public RandomReaderEs2(int id, ContenitoreEs2 c) {
        super();
        this.id = id;
        this.c = c;
    }

    public void run() {
        int lettureEffettuate = 0;
        int currentId = 0;
        while (lettureEffettuate < numLetture) {
            currentId = (int) (Math.random() * ((ImplContenitoreEs2) c).getMaxCapacity());
            try {
                String l = c.recupera(currentId);
                System.out.println("*** RandomReader " + id + "effettuata lettura di stringa " + l
                        + " con identificativo " + currentId);
                c.cancella(currentId);
            } catch (Inesistente e) {
                // TODO Auto-generated catch block
                System.err.println("RandomReader " + id + "fallita lettura di stringa con identificativo " + currentId);
            }
            try {
                Thread.sleep(600 + (long) (Math.random() * 10000));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            lettureEffettuate += 1;
        }
    }
}
