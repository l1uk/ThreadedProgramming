package stringContainer;

public class ImplContenitore implements Contenitore {

    private final String[] arrValori;
    private final int[] arrIdentificatori;
    private final int maxCapacity;
    private int currentCapacity = 0;
    private int numInserimento = 0;
    public ImplContenitore(int maxCapacity) {
        super();
        this.maxCapacity = maxCapacity;
        this.arrValori = new String[maxCapacity];
        this.arrIdentificatori = new int[maxCapacity];
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public synchronized int inserisci(String s) throws Pieno {
        // TODO Auto-generated method stub
        if (maxCapacity == currentCapacity)
            throw new Pieno();
        arrValori[currentCapacity] = s;
        arrIdentificatori[currentCapacity++] = ++numInserimento;
        return numInserimento;
    }

    @Override
    public synchronized String recupera(int n) throws Inesistente {
        // TODO Auto-generated method stub
        int pos = trovato(n);
        if (pos == -1 || currentCapacity == 0)
            throw new Inesistente();
        return arrValori[pos];
    }

    @Override
    public synchronized void cancella(int n) throws Inesistente {
        // TODO Auto-generated method stub
        int pos = trovato(n);
        if (pos == -1)
            throw new Inesistente();
        shiftArraysLeft(pos);
        currentCapacity -= 1;

    }

    private void shiftArraysLeft(int pos) {
        for (int i = pos; i < maxCapacity - 1; i += 1) {
            arrIdentificatori[i] = arrIdentificatori[i + 1];
            arrValori[i] = arrValori[i + 1];

        }
    }

    private int trovato(int n) {
        boolean trovato = false;
        int i = 0;
        while (i < currentCapacity && !trovato) {
            if (arrIdentificatori[i] == n)
                return i;
            i++;
        }
        return -1;
    }
}
