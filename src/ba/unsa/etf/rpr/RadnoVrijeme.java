package ba.unsa.etf.rpr;

public class RadnoVrijeme  {
    private String pocetak;
    private String kraj;
    private String id;
    private mjestoRada vrstaRada;

    public RadnoVrijeme(String pocetak, String kraj, String id, mjestoRada s) {
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.id = id;
        this.vrstaRada=s;
    }

    public RadnoVrijeme() {
    }

    public String getPocetak() {
        return pocetak;
    }

    public mjestoRada getVrstaRada() {
        return vrstaRada;
    }

    public void setVrstaRada(mjestoRada vrstaRada) {
        this.vrstaRada = vrstaRada;
    }

    public void setPocetak(String pocetak) {
        this.pocetak = pocetak;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
