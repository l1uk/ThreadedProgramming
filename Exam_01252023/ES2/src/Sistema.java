public class Sistema {

    static int NUM_PIANI = 3;
    static int NUM_STALLI = 5;
    static int NUM_AUTOMOBILISTI = 20;

    public static void main(String[] args) {

        GestioneParcheggioImpl gp = new GestioneParcheggioImpl(NUM_PIANI, NUM_STALLI);

        for (int i = 0; i < NUM_AUTOMOBILISTI; i++) {
            new Thread(new Automobilista(gp, i)).start();
        }

    }
}
