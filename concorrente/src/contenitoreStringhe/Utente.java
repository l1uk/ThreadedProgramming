package contenitoreStringhe;

public class Utente extends Thread {
    private static final int numInserimenti = 10;
    private final Contenitore c;
    private final int id;

    public Utente(int id, Contenitore c) {
        super();
        this.id = id;
        this.c = c;
    }

    public void run() {
        int inserimentiEffettuati = 0;
        int currentId = 0;
        while (inserimentiEffettuati < numInserimenti) {
            try {
                currentId = c.inserisci(getName() + inserimentiEffettuati);
                inserimentiEffettuati++;
                System.out.println("Utente " + id + "effettuato " + inserimentiEffettuati + "esimo inserimento");
            } catch (Pieno e) {
                System.err.println("Utente " + id + " " + (inserimentiEffettuati + 1) + "esimo inserimento fallito");
            }
            try {
                Thread.sleep(6000 + (long) (Math.random() * 10000));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                String l = c.recupera(currentId);
                System.out.println(
                        "Utente " + id + "effettuata lettura di stringa " + l + " con identificativo " + currentId);
                c.cancella(currentId);
            } catch (Inesistente e) {
                // TODO Auto-generated catch block
                System.err.println("Utente " + id + "fallita lettura di stringa con identificativo " + currentId);
            }
        }
    }
}
