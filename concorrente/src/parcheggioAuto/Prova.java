package parcheggioAuto;

public class Prova {
	private static final int piani = 3;
	private static final int stalli = 5;
	private static final int auto = 20;

	public static void main(String[] args) throws InterruptedException {
		GestioneParcheggio gp = new ImplGest(piani, stalli);
		for (int i = 0; i < auto; i++) {
			Automobilista a = new Automobilista(i + 1, gp);
			a.start();
			Thread.sleep(500);
		}
	}
}
