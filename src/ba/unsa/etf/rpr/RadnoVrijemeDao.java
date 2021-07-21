package ba.unsa.etf.rpr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RadnoVrijemeDao {
    private Connection conn;
    private static RadnoVrijemeDao instance;
    private PreparedStatement ubaciRadnoVrijeme;

    private RadnoVrijemeDao() throws SQLException {
        String url="jdbc:sqlite:korisnici.db";
        conn= DriverManager.getConnection(url);
        ubaciRadnoVrijeme=conn.prepareStatement("INSERT INTO radnovrijeme values(?,?,?,?)");
    }
    public static RadnoVrijemeDao getInstance() throws SQLException {
        if(instance==null){
            instance=new RadnoVrijemeDao();
        }
        return instance;
    }
    public void ubaciVrijeme(RadnoVrijeme rw) throws SQLException {
        ubaciRadnoVrijeme.setString(1,rw.getId());
        ubaciRadnoVrijeme.setString(2,rw.getPocetak());
        ubaciRadnoVrijeme.setString(3,rw.getKraj());
        ubaciRadnoVrijeme.setString(4,rw.getVrstaRada().toString());
        ubaciRadnoVrijeme.executeUpdate();
    }

}
