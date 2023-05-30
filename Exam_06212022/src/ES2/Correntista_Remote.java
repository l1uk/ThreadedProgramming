package ES2;

import java.rmi.RemoteException;

public class Correntista_Remote implements Runnable {
    String iban;
    String ibanb;
    public SistemaBancaRemoteInterface gest;
    public Character c;

    public Correntista_Remote(String iban, SistemaBancaRemoteInterface gest, Character c) {
        this.iban = iban;
        this.gest = gest;
        this.c = c;
    }

    public Correntista_Remote(String iban, String ibanb, SistemaBancaRemoteInterface gest, Character c) {
        this.iban = iban;
        this.ibanb = ibanb;
        this.gest = gest;
        this.c = c;
    }

    @Override
    public void run() {
        try {
            gest.nuovoConto(iban);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        try {
            if (c == 'a') {
                System.out.println(c + ": " + gest.saldo(iban));
                Thread.sleep(1000);
                gest.versamento(iban, 120);
                Thread.sleep(1000);
                System.out.println(c + ": " + gest.saldo(iban));
                gest.trasferimento(iban, ibanb, 50);
                Thread.sleep(1000);
                System.out.println(c + ": " + gest.saldo(iban));
            } else if (c == 'b') {
                System.out.println(c + ": " + gest.saldo(iban));
                Thread.sleep(1000);
                gest.attendiTrasferimento(iban);
                Thread.sleep(800);
                gest.prelievo(iban, 20);
                Thread.sleep(1000);
                System.out.println(c + ": " + gest.saldo(iban));


            }

        } catch (SistemaBancaRemoteInterface.ContoInesistente e) {
            throw new RuntimeException(e);
        } catch (SistemaBancaRemoteInterface.SommaNegativa e) {
            throw new RuntimeException(e);
        } catch (InterruptedException | RemoteException e) {
            throw new RuntimeException(e);
        } catch (SistemaBancaRemoteInterface.DisponibilitaInsufficiente e) {
            throw new RuntimeException(e);
        }
    }
}
