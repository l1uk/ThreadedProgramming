public class Consumatore implements Runnable {

    Integer id;
    GestoreM gestoreMessaggi;

    public Consumatore(GestoreM gestoreMessaggi, Integer id) {
        this.gestoreMessaggi = gestoreMessaggi;
        this.id = id;
    }

    @Override
    public void run() {
        synchronized (System.out) {
            System.out.println("Consumatore " + id + " avviato!");
        }
        try {
            gestoreMessaggi.signUp(id);
        } catch (ConsumatoreMsgInterface.GiaRegistrato e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Thread.sleep((long) (100 + Math.random() * 10000));
                synchronized (System.out) {
                    System.out.println("#CONSUMATORE " + id + " waiting for message");
                }
                Msg m = gestoreMessaggi.receive(id);
                synchronized (System.out) {
                    System.out.println("#CONSUMATORE " + id + "Message: " + m.txt);
                }
            } catch (InterruptedException | ConsumatoreMsgInterface.ConsumatoreSconosciuto e) {
                e.printStackTrace();
            }
        }
    }
}
