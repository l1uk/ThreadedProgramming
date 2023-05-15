public class Produttore implements Runnable {

    GestoreM gestoreMessaggi = null;

    public Produttore(GestoreM gestoreMessaggi) {
        this.gestoreMessaggi = gestoreMessaggi;
    }

    public Msg generaMessaggio() {
        int randomDest = (int) (Math.random() * 100);
        Msg m = new Msg(randomDest, "Messaggio di prova");
        return m;
    }

    @Override
    public void run() {
        synchronized (System.out) {
            System.out.println("Produttore avviato!");
        }
        while (true) {
            try {
                Thread.sleep((long) (100 + Math.random() * 150));
                gestoreMessaggi.send(generaMessaggio());
            } catch (InterruptedException | ProduttoreMsgInterface.DestinatarioPieno e) {
                e.printStackTrace();
            }
        }
    }
}
