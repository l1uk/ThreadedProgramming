package ES1;

public class Utente implements Runnable {
    static final int NUM_OPERAZIONI = 5;
    final PriorityStorage s;
    final String id;

    public Utente(PriorityStorage s, String id) {
        this.s = s;
        this.id = id;
    }

    private void print(String msg) {
        synchronized (System.out) {
            System.out.println(msg);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_OPERAZIONI; i++) {
            try {
                double rand = Math.random(); // numero casuale da cui dipende il comportamento dell'utente
                Priority p = new Priority((int) (Priority.MIN + Math.random() * Priority.MAX)); // priorità casuale per l'operazione corrente
                Thread.sleep((long) (100 + Math.random() * 1000));

                if (rand < 0.5) { // 50% prob di inserimento messaggio
                    String message = "MESSAGGIO DI PROVA con priorità " + p.value + ", da utente " + id;
                    s.insert(message, p);
                    print("Messaggio con priorità " + p.value + ", scritto da utente " + id + " : \"" + message + "\"");

                } else if (rand < 0.6) { // 10% prob di conteggio messaggi
                    print("Numero di messaggi per priorità " + p.value + ", da utente " + id + " :" + s.num(p));
                } else if (rand < 0.8) { // 20% prob di rimozione messaggio
                    s.remove(p);
                    System.err.println("Messaggio con priorità " + p.value + ", rimosso da utente " + id);
                } else { // 20% prob di lettura messaggio
                    print(id + " in coda per leggere messaggio con priorità " + p.value + "...");
                    print("Messaggio con priorità " + p.value + ", letto da utente " + id + " : \"" + s.get(p) + "\"");
                }

            } catch (PriorityStorage.StorageEmpty | PriorityStorage.StorageFull | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
