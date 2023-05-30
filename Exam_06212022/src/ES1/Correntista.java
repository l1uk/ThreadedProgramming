package ES1;

public class Correntista implements Runnable {
    public SistemaBancaInterface.IBAN iban;
    ES1.SistemaBancaInterface.IBAN ibanb;
    public SistemaBancaInterfaceImpl gest;
    public Character c;

    public Correntista(SistemaBancaInterface.IBAN iban, SistemaBancaInterfaceImpl gest, Character c) {
        this.iban = iban;
        this.gest = gest;
        this.c = c;
    }

    public Correntista(SistemaBancaInterface.IBAN iban, SistemaBancaInterface.IBAN ibanb, SistemaBancaInterfaceImpl gest, Character c) {
        this.iban = iban;
        this.ibanb = ibanb;
        this.gest = gest;
        this.c = c;
    }

    @Override
    public void run() {
        gest.nuovoConto(iban);
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

        } catch (SistemaBancaInterface.ContoInesistente e) {
            throw new RuntimeException(e);
        } catch (SistemaBancaInterface.SommaNegativa e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (SistemaBancaInterface.DisponibilitaInsufficiente e) {
            throw new RuntimeException(e);
        }
    }
}
