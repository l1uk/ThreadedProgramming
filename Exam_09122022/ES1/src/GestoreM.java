import java.util.*;

public class GestoreM implements ConsumatoreMsgInterface, ProduttoreMsgInterface {

    List<Integer> consumatoriRegistrati = new ArrayList<>();

    Map<Integer, Stack<Msg>> mapMsgConsumatore = new HashMap<>();

    public static int MAX_MSG = 10;

    @Override
    public synchronized void signUp(int id) throws GiaRegistrato {
        if (consumatoriRegistrati.contains(id)) {
            throw new GiaRegistrato();
        }
        consumatoriRegistrati.add(id);
    }

    @Override
    public synchronized Msg receive(int id) throws InterruptedException, ConsumatoreSconosciuto {
        Msg result;
        if (!consumatoriRegistrati.contains(id)) {
            throw new ConsumatoreSconosciuto();
        }
        while (mapMsgConsumatore.get(id) == null || mapMsgConsumatore.get(id).size() == 0) {
            wait();
        }
        result = mapMsgConsumatore.get(id).pop();
        return result;
    }

    @Override
    public synchronized void send(Msg m) throws DestinatarioPieno {
        int numMsg;

        mapMsgConsumatore.computeIfAbsent(m.dest, k -> new Stack<>());
        numMsg = mapMsgConsumatore.get(m.dest).size();


        if (numMsg >= MAX_MSG) {
            throw new DestinatarioPieno(m.dest);
        } else {
            mapMsgConsumatore.get(m.dest).push(m);
        }
        notifyAll();
    }
}
