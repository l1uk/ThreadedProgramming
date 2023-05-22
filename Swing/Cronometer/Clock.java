import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Clock extends UnicastRemoteObject implements Runnable, ObservableClock {
    public static void main(String[] args) throws RemoteException {
        Registry reg = LocateRegistry.createRegistry(1099);
        Clock c = new Clock();
        new Thread(c).start();
        reg.rebind("Clock", c);
        System.out.println("Server ready...");
    }

    int hours, minutes, seconds, centiSeconds;
    //JTextField display; // just one display at the time / backend knows frontend
    private boolean go;

    private List<RemoteObserver> observers;

    String time;

    DecimalFormat df = new DecimalFormat("00");

    public Clock() throws RemoteException {
        super();
        go = false;
        observers = new ArrayList<>();
        this.reset();

    }

    public void started() throws RemoteException {
        go = true;
    }

    public void stop() throws RemoteException {
        go = false;
    }

    public void reset() {
        hours = 0;
        minutes = 0;
        seconds = 0;
        centiSeconds = 0;
        time = "0:00:00.00";
        try {
            this.notifyObservers(time);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void incTime() {
        centiSeconds++;
        if (centiSeconds == 100) {
            seconds++;
            centiSeconds = 0;
        }
        if (seconds == 60) {
            seconds = 0;
            minutes += 1;
        }
        if (minutes == 60) {
            minutes = 0;
            hours += 1;
        }
        time = String.valueOf(hours) + ":" + df.format(minutes) + ":" + df.format(seconds) + "." + df.format(centiSeconds);

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
                synchronized (this) {
                    if (go) {
                        this.incTime();
                        notifyObservers(time);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addObserver(RemoteObserver o) throws RemoteException {
        System.out.println("Observer registered...");
        observers.add(o);
        o.inform(this, time);
    }

    @Override
    public void detachObserver(RemoteObserver o) throws RemoteException {
        System.out.println("Observer detached...");
        observers.remove(o);
    }

    @Override
    public void notifyObservers(Object msg) throws RemoteException {
        for (RemoteObserver o : observers) {
            o.inform(this, time);
        }
    }
}
