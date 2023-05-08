package parkingLot;

public interface GestioneParcheggio {
    void ingresso();

    void uscita();

    void parcheggia(int piano) throws PianoInesistente, NoStalliLiberi;

    void partenza(int piano) throws PianoInesistente;

}

