package ES1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class SistemaBancaInterfaceImpl implements SistemaBancaInterface {

    Map<IBAN, Integer> conti;

    public SistemaBancaInterfaceImpl() {
        this.conti = new HashMap<>();
    }

    public void nuovoConto(IBAN idcc) {
        conti.computeIfAbsent(idcc, k -> 0);
    }

    @Override
    public int saldo(IBAN idcc) throws ContoInesistente {

        Integer saldo;
        synchronized (this.conti) {
            saldo = this.conti.get(idcc);
        }

        if (saldo == null)
            throw new ContoInesistente();
        return saldo;

    }

    @Override
    public void versamento(IBAN idcc, int s) throws ContoInesistente, SommaNegativa {
        if (s <= 0) throw new SommaNegativa();
        AtomicBoolean existing = new AtomicBoolean(false);
        synchronized (this.conti) {
            this.conti.computeIfPresent(idcc, (k, v) -> {
                        existing.set(true);
                        return v + s;
                    }
            );
        }
        if (!existing.get()) throw new ContoInesistente();
    }

    @Override
    public void prelievo(IBAN idcc, int s) throws DisponibilitaInsufficiente, SommaNegativa, ContoInesistente {
        if (s <= 0) throw new SommaNegativa();
        AtomicBoolean existing = new AtomicBoolean(false);
        AtomicBoolean enough = new AtomicBoolean(false);
        synchronized (this.conti) {
            this.conti.computeIfPresent(idcc, (k, v) -> {
                        Integer newValue = v;
                        existing.set(true);
                        if (v >= s) {
                            newValue -= s;
                            enough.set(true);
                        }
                        return newValue;
                    }
            );
        }
        if (!existing.get()) throw new ContoInesistente();
        if (!enough.get()) throw new DisponibilitaInsufficiente();
    }

    @Override
    public synchronized void trasferimento(IBAN idccFrom, IBAN idccTo, int s) throws DisponibilitaInsufficiente, SommaNegativa, ContoInesistente {
        AtomicBoolean existing = new AtomicBoolean(false);
        AtomicBoolean enough = new AtomicBoolean(false);
        if (s <= 0) throw new SommaNegativa();
        conti.computeIfPresent(idccFrom, (k, v) -> {

            conti.computeIfPresent(idccTo, (k1, v1) -> {
                existing.set(true);
                if (v > s) {
                    enough.set(true);
                    return v1 + s;
                }
                return v1;
            });
            if (enough.get())
                return v - s;
            return v;
        });

        if (!existing.get()) throw new ContoInesistente();
        if (!enough.get()) throw new DisponibilitaInsufficiente();
        notifyAll();
    }

    @Override
    public synchronized boolean attendiTrasferimento(IBAN idcc) throws ContoInesistente {
        Integer oldAmount = conti.get(idcc);
        if (oldAmount == null) throw new ContoInesistente();
        try {
            wait(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return !oldAmount.equals(conti.get(idcc));
    }
}
