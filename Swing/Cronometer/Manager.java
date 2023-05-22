import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Manager implements ActionListener {

    private boolean go;
    private JButton reset;

    ObservableClock c;

    public Manager(CronometerFrame c) {
        go = false;
        reset = c.reset;
        Registry reg = null;
        try {
            reg = LocateRegistry.getRegistry(1099);
            this.c = (ObservableClock) reg.lookup("Clock");
            UnicastRemoteObject.exportObject(c, 1089);
            this.c.addObserver(c);
        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String op = (String) ((JButton) e.getSource()).getText();
        try {
            if (op == "Reset") {
                c.reset();
            } else if (!go) {
                reset.setEnabled(false);
                go = true;
                c.started();
            } else {
                go = false;
                reset.setEnabled(true);
                c.stop();
            }
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void detach(CronometerFrame cronometerFrame) {
        try {
            c.detachObserver(cronometerFrame);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
