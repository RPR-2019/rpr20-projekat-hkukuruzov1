package ba.unsa.etf.rpr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    private Connection conn;
    private static Dao instance;
    private PreparedStatement dajKorisnika,ubaciRadnoVrijeme,dajSve,dajImeiPrezime,dajDan,dajSveUMjesecu,brisiKorisnika,brisiKorisnika1,ubaciKorisnika,nullKraj,ubaciVrijeme;

    private Dao() throws SQLException {
        String url="jdbc:sqlite:korisnici.db";
        conn= DriverManager.getConnection(url);
        dajKorisnika=conn.prepareStatement("SELECT * FROM korisnici WHERE username=? and password=?");
        ubaciRadnoVrijeme=conn.prepareStatement("UPDATE radnovrijeme SET kraj=? WHERE id=? and kraj IS NULL");
        dajSve=conn.prepareStatement("SELECT * FROM radnovrijeme");
        dajImeiPrezime=conn.prepareStatement("SELECT ime,prezime from korisnici where username=?");
        dajDan=conn.prepareStatement("SELECT * from radnovrijeme where id=? and dan=? and mjesec=? and godina=?");
        dajSveUMjesecu=conn.prepareStatement("SELECT * from radnovrijeme where id=? and mjesec=? and godina=? order by dan");
        brisiKorisnika=conn.prepareStatement("DELETE from korisnici where username=?");
        brisiKorisnika1=conn.prepareStatement("DELETE from radnovrijeme where id=?");
        ubaciKorisnika=conn.prepareStatement("INSERT INTO korisnici(username,password,admin,ime,prezime) values(?,?,?,?,?)");
        nullKraj=conn.prepareStatement("SELECT * from radnovrijeme where id=? and kraj IS NULL");
        ubaciVrijeme=conn.prepareStatement("INSERT INTO radnovrijeme(id,pocetak,kraj,vrstarada,dan,mjesec,godina) values(?,?,?,?,?,?,?)");
    }
    public static Dao getInstance() throws SQLException {
        if(instance==null){
            instance=new Dao();
        }
        return instance;
    }
    public Connection getConnection(){
        return conn;
    }
    public boolean daLiPostojiKorisnik(String s1,String s2) throws SQLException {
        dajKorisnika.setString(1,s1);
        dajKorisnika.setString(2,s2);
        var rs=dajKorisnika.executeQuery();
        while(rs.next()){
            return true;
        }
            return false;

    }
    public boolean daLiJeAdmin(String s1,String s2) throws SQLException {
        dajKorisnika.setString(1,s1);
        dajKorisnika.setString(2,s2);
        var rs=dajKorisnika.executeQuery();
        while(rs.next()){
            if(rs.getInt(3)==1)
            return true;
        }
        return false;
    }
    public Korisnik dajKorisnika(String s1,String s2) throws SQLException {
        dajKorisnika.setString(1,s1);
        dajKorisnika.setString(2,s2);
        var rs=dajKorisnika.executeQuery();
        while(rs.next()){
            return new Korisnik(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5));
        }
        return null;
    }
    public void ubaciVrijeme(RadnoVrijeme rw) throws SQLException {
        ubaciRadnoVrijeme.setString(1,rw.getKraj());
        ubaciRadnoVrijeme.setString(2,rw.getId());
        ubaciRadnoVrijeme.executeUpdate();
    }
    public List<String> dajSve() throws SQLException {
        List<String> lista=new ArrayList<>();
        var f=dajSve.executeQuery();
        while(f.next()){
            dajImeiPrezime.setString(1,f.getString(1));
            var x=dajImeiPrezime.executeQuery();
            lista.add(x.getString(1)+" "+x.getString(2)+" "+f.getString(2)+" "+f.getString(3)+" "+f.getString(4)+" "+f.getInt(5)+" "+f.getString(6)+" "+f.getInt(7));
        }
        return lista;
    }
    public String dajTaj(String i,int d,String m,int g) throws SQLException {
        dajDan.setString(1,i);
        dajDan.setInt(2,d);
        dajDan.setString(3,m);
        dajDan.setInt(4,g);
        var f=dajDan.executeQuery();
        while(f.next()){
            return f.getString(2)+" "+f.getString(3)+" "+f.getString(4);
        }
        return "Korisnik nije radio tog dana!";
    }
    public List<String> dajMjesec(String u,String m,int g) throws SQLException {
        dajSveUMjesecu.setString(1,u);
        dajSveUMjesecu.setString(2,m);
        dajSveUMjesecu.setInt(3,g);
        var f=dajSveUMjesecu.executeQuery();
        List<String> ret=new ArrayList<>();
        while(f.next()){
            ret.add("Dan u mjesecu: "+f.getInt(5)+". "+f.getString(2)+" "+f.getString(3)+" "+f.getString(4));
        }
        return ret;
    }
    public void brisanje(String a) throws SQLException {
        brisiKorisnika1.setString(1,a);
        brisiKorisnika1.executeUpdate();
        brisiKorisnika.setString(1,a);
        brisiKorisnika.executeUpdate();
    }
    public boolean ubaci(Korisnik k){
        try {
            ubaciKorisnika.setString(1, k.getUsername());
            ubaciKorisnika.setString(2, k.getPassword());
            ubaciKorisnika.setInt(3, k.getAdmin());
            ubaciKorisnika.setString(4, k.getIme());
            ubaciKorisnika.setString(5, k.getPrezime());
            ubaciKorisnika.executeUpdate();
        }
            catch (SQLException throwables) {
            return false;
        }
            return true;
    }
    public boolean imaLiNule(String k) throws SQLException {
        nullKraj.setString(1,k);
        var rs=nullKraj.executeQuery();
        while(rs.next()){
            return true;
        }
        return false;
    }
    public void ubaciVrijeme1(RadnoVrijeme k) throws SQLException {
        ubaciVrijeme.setString(1,k.getId());
        ubaciVrijeme.setString(2,k.getPocetak());
        ubaciVrijeme.setString(3,k.getKraj());
        ubaciVrijeme.setString(4,k.getVrstaRada().toString());
        ubaciVrijeme.setInt(5,k.getDan());
        ubaciVrijeme.setString(6,k.getMjesec());
        ubaciVrijeme.setInt(7,k.getGodina());
        ubaciVrijeme.executeUpdate();
    }
}
