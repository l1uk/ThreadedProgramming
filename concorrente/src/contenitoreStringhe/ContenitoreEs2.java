package contenitoreStringhe;

public interface ContenitoreEs2 {
	int inserisci(String s) throws InterruptedException;

	String recupera(int n) throws Inesistente;

	void cancella(int n) throws Inesistente;
}
