package RMI_task_observer;

import java.rmi.RemoteException;

public class NotifyCalculator<T> implements Runnable {
    Task<T> task;
    Server server;

    public NotifyCalculator(Task<T> t, Server s) {
        task = t;
        server = s;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        T res = task.execute();
        try {
            server.notifyObservers(res);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
