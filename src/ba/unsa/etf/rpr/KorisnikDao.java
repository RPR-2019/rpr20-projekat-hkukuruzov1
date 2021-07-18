package ba.unsa.etf.rpr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KorisnikDao {
    private Connection conn;
    private static KorisnikDao instance;
    private PreparedStatement dajKorisnika,daLiJeAdmin;

    private KorisnikDao() throws SQLException {
        String url="jdbc:sqlite:korisnici.db";
        conn= DriverManager.getConnection(url);
        dajKorisnika=conn.prepareStatement("SELECT * FROM korisnici WHERE username=? and password=?");
        daLiJeAdmin=conn.prepareStatement("SELECT * FROM korisnici where admin=?");
    }
    public static KorisnikDao getInstance() throws SQLException {
        if(instance==null){
            instance=new KorisnikDao();
        }
        return instance;
    }
    public boolean dajKorisnika(String s1,String s2) throws SQLException {
        dajKorisnika.setString(1,s1);
        dajKorisnika.setString(2,s2);
        var rs=dajKorisnika.executeQuery();
        while(rs.next()){
            return true;
        }
            return false;

    }
    public boolean daLiJeAdmin(int i) throws SQLException {
        daLiJeAdmin.setInt(1,i);
        var rs=daLiJeAdmin.executeQuery();
        while(rs.next()){
            return true;
        }
        return false;
    }
}
