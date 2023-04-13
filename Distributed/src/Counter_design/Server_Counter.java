package Counter_design;
public class Server_Counter implements CounterInterface {
    private int value = 0;


    public synchronized void reset() { value = 0; }

    public synchronized void increment(){ value++; }

    public synchronized int value(){ return value; }




}