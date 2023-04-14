package parcheggioAuto;

public class Piano {
    final int numStalli;
    int occupati;

    public Piano(int d) {
        numStalli = d;
        occupati = 0;
    }

    public void parcheggia() throws NoStalliLiberi {
        if (occupati == numStalli)
            throw new NoStalliLiberi();
        occupati += 1;
    }

    public void partenza() {
        occupati -= 1;
    }

    public int stalliLiberi() {
        return numStalli - occupati;
    }
}
