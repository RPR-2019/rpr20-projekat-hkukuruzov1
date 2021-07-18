package ba.unsa.etf.rpr;

public class Korisnik {
    private String username;
    private String password;
    private int admin;

    public Korisnik(String username, String password, int admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public Korisnik() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
