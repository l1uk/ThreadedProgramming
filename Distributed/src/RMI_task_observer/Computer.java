package RMI_task_observer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Computer extends Remote {

    <T> void startTask(Task<T> t) throws RemoteException;


}
