package ES1;

interface SistemaBancaInterface {
    int saldo(IBAN idcc) throws ContoInesistente;

    void versamento(IBAN idcc, int s)
            throws ContoInesistente, SommaNegativa;

    void prelievo(IBAN idcc, int s)
            throws DisponibilitaInsufficiente, SommaNegativa, ContoInesistente;

    void trasferimento(IBAN idccFrom, IBAN idccTo, int s)
            throws DisponibilitaInsufficiente, SommaNegativa, ContoInesistente;

    boolean attendiTrasferimento(IBAN idcc) throws ContoInesistente;

    class ContoInesistente extends Exception {
    }

    class SommaNegativa extends Exception {
    }

    class DisponibilitaInsufficiente extends Exception {
    }

    class IBAN {
        String iban;

        public IBAN(String iban) {
            this.iban = iban;
        }
    }
}