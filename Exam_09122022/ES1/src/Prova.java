public class Prova {

    static int numConsumatori = 5;

    public static void main(String[] args) {
        GestoreM gestoreMessaggi = new GestoreM();
        Produttore p = new Produttore(gestoreMessaggi);

        new Thread(p).start();

        for (int i = 0; i < numConsumatori; i++) {
            new Thread(new Consumatore(gestoreMessaggi, i)).start();
        }

    }
}
