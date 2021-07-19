package ba.unsa.etf.rpr;

public class Radnik extends Korisnik {
    private boolean naPoslu;
    private mjestoRada mjestoR;

    public Radnik(String username, String password, int admin) {
        super(username, password, admin);
        naPoslu=false;
        mjestoR=null;
    }

    public boolean isNaPoslu() {
        return naPoslu;
    }

    public void setNaPoslu(boolean naPoslu) {
        this.naPoslu = naPoslu;
    }

    public mjestoRada getMjestoR() {
        return mjestoR;
    }

    public void setMjestoR(mjestoRada mjestoR) {
        this.mjestoR = mjestoR;
    }
}
