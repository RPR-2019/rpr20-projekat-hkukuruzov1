package ba.unsa.etf.rpr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KorisnikDao {
    private Connection conn;
    private static KorisnikDao instance;
    private PreparedStatement dajKorisnika;

    private KorisnikDao() throws SQLException {
        String url="jdbc:sqlite:korisnici.db";
        conn= DriverManager.getConnection(url);
        dajKorisnika=conn.prepareStatement("SELECT * FROM korisnici WHERE username=? and password=?");
    }
    public static KorisnikDao getInstance() throws SQLException {
        if(instance==null){
            instance=new KorisnikDao();
        }
        return instance;
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
}
