package ES1;

public class Prova {
    static final int NUM_CLIENTS = 5;

    public static void main(String[] args) {

        Storage s = new Storage();
        for (int i = 0; i < NUM_CLIENTS; i++)
            new Thread(new Utente(s, "UTENTE_" + i)).start();

    }
}