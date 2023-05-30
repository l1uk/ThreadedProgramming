package ES1;

public class Prova {
    public static void main(String args[]) throws InterruptedException {
        SistemaBancaInterfaceImpl gest = new SistemaBancaInterfaceImpl();
        SistemaBancaInterface.IBAN iban_a = new SistemaBancaInterface.IBAN("abc");
        SistemaBancaInterface.IBAN iban_b = new SistemaBancaInterface.IBAN("bcd");
        new Thread(
                new Correntista(iban_a, iban_b, gest, 'a')
        ).start();
        Thread.sleep(800);
        new Thread(
                new Correntista(iban_b, gest, 'b')
        ).start();

    }
}
