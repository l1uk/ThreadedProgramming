package contenitoreStringhe;

public class RandomReader extends Thread {
    private static final int numLetture = 10;
    private final Contenitore c;
    private final int id;

    public RandomReader(int id, Contenitore c) {
        super();
        this.id = id;
        this.c = c;
    }

    public void run() {
        int lettureEffettuate = 0;
        int currentId = 0;
        while (lettureEffettuate < numLetture) {
            currentId = (int) (Math.random() * ((ImplContenitore) c).getMaxCapacity());
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
                Thread.sleep(600 + (long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            lettureEffettuate += 1;
        }
    }
}
