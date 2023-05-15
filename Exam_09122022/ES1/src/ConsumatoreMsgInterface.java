public interface ConsumatoreMsgInterface {
    void signUp(int id) throws GiaRegistrato;

    Msg receive(int id) throws InterruptedException, ConsumatoreSconosciuto;

    class GiaRegistrato extends Exception {
    }

    class ConsumatoreSconosciuto extends Exception {
    }
}
