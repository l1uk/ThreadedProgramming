package client;

import server.NastroRemote;

import java.rmi.RemoteException;

public class User implements Runnable {
    private NastroRemote n;

    public User(NastroRemote n) {
        this.n = n;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000 + (long) Math.random() * 10000);
                double rand = Math.random();
                if (rand < 0.85) {
                    n.write("Messsaggio di prova " + rand);
                } else if (rand < 0.9) {
                    n.rewind();
                } else {
                    System.out.println(n.read());
                }
            } catch (InterruptedException |
                     NastroRemote.noWrite | NastroRemote.noRead | RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
