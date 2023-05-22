import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;

public class CronometerFrame extends JFrame implements RemoteObserver, WindowListener {
    public JButton start = new JButton("Start/Stop"), reset = new JButton("Reset");
    public JTextField display = new JTextField();
    Manager m;

    public CronometerFrame() throws HeadlessException {

        super("Cronometer");
        this.setSize(250, 100);
        this.setLocation(500, 200);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        GridBagLayout glb = new GridBagLayout();

        JPanel pan = new JPanel(glb);


        GridBagConstraints displ = new GridBagConstraints();
        displ.weightx = 1.0;
        displ.weighty = 1.0;
        displ.fill = GridBagConstraints.BOTH;
        displ.gridwidth = GridBagConstraints.REMAINDER;
        pan.add(display, displ);

        GridBagConstraints startstop = new GridBagConstraints();
        startstop.weightx = 1.0;
        startstop.weighty = 1.0;
        startstop.fill = GridBagConstraints.BOTH;
        startstop.gridwidth = GridBagConstraints.RELATIVE;
        pan.add(start, startstop);

        GridBagConstraints res = new GridBagConstraints();
        res.weightx = 1.0;
        res.weighty = 1.0;
        res.fill = GridBagConstraints.BOTH;
        res.gridwidth = GridBagConstraints.REMAINDER;
        pan.add(reset, res);

        this.add(pan);
        m = new Manager(this);
        start.addActionListener(m);
        reset.addActionListener(m);
        addWindowListener(this);
    }

    public static void main(String[] args) {
        new CronometerFrame().setVisible(true);
    }

    @Override
    public void inform(Object observable, Object updateMsg) throws RemoteException {
        this.display.setText((String) updateMsg);
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        m.detach(this);
    }

    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
