package stringContainer;

public class UtenteEs2 extends Thread {
    private static final int numInserimenti = 10;
    private final ContenitoreEs2 c;
    private final int id;

    public UtenteEs2(int id, ContenitoreEs2 c) {
        super();
        this.id = id;
        this.c = c;
    }

    public void run() {
        int inserimentiEffettuati = 0;
        int currentId = 0;
        while (inserimentiEffettuati < numInserimenti) {
            try {
                System.out.println("Utente " + id + "in coda per " + (inserimentiEffettuati + 1) + "esimo inserimento");
                currentId = c.inserisci(getName() + inserimentiEffettuati);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            inserimentiEffettuati++;
            System.out.println("Utente " + id + "effettuato " + inserimentiEffettuati + "esimo inserimento");

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
