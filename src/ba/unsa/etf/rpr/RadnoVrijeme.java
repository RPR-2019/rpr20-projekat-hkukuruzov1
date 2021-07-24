package ba.unsa.etf.rpr;

public class RadnoVrijeme  {
    private String pocetak;
    private String kraj;
    private String id;
    private mjestoRada vrstaRada;
    private int dan;
    private String mjesec;
    private int godina;

    public RadnoVrijeme(String pocetak, String kraj, String id, mjestoRada vrstaRada, int dan, String mjesec, int godina) {
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.id = id;
        this.vrstaRada = vrstaRada;
        this.dan = dan;
        this.mjesec = mjesec;
        this.godina = godina;
    }

    public RadnoVrijeme() {
    }

    public void setDan(int dan) {
        this.dan = dan;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }


    public String getMjesec() {
        return mjesec;
    }

    public void setMjesec(String mjesec) {
        this.mjesec = mjesec;
    }

    public int getDan() {
        return dan;
    }

    public int getGodina() {
        return godina;
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
