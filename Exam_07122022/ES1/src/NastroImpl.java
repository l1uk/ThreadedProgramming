import java.util.ArrayList;
import java.util.List;

public class NastroImpl implements Nastro {
    List<String> nastro;
    int currentPosition = 0;

    @Override
    public synchronized void write(String s) throws noWrite {
        //write: inserisce una stringa nel nastro nella posizione corrente.
        //La nuova posizione corrente al termine dell’operazione è quella successiva alla stringa inserita.
        //L’eccezione noWrite viene sollevata qualora prima dell’operazione vi sia una stringa in posizione corrente.
        if (nastro.size() != 0 && nastro.get(currentPosition) != null) {
            throw new noWrite();
        }
        nastro.add(currentPosition++, s);
        nastro.add(currentPosition, null);
    }

    @Override
    public synchronized String read() throws noRead {
        //read: restituisce la stringa in posizione corrente.
        //Al termine dell’operazione la posizione corrente si sposta alla stringa successiva a quello letta.
        //L’eccezione noRead viene sollevata qualora in posizione corrente non vi sia una stringa.
        if (currentPosition >= nastro.size() || nastro.get(currentPosition) == null) {
            throw new noRead();
        }
        return nastro.get(currentPosition++);
    }

    @Override
    public synchronized void rewind() {
        //rewind: riporta la posizione corrente alla prima stringa del nastro.
        currentPosition = 0;
    }

    @Override
    public boolean empty() {
        //empty: restituisce il valore Vero se e solo se il nastro non contiene stringhe.
        return (nastro.size() == 0);
    }

    public NastroImpl() {
        this.nastro = new ArrayList<>();
    }

}
