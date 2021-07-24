package ba.unsa.etf.rpr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RadnoVrijemeDao {
    private Connection conn;
    private static RadnoVrijemeDao instance;
    private PreparedStatement ubaciRadnoVrijeme,dajSve,dajImeiPrezime;

    private RadnoVrijemeDao() throws SQLException {
        String url="jdbc:sqlite:korisnici.db";
        conn= DriverManager.getConnection(url);
        ubaciRadnoVrijeme=conn.prepareStatement("INSERT INTO radnovrijeme values(?,?,?,?,?,?,?)");
        dajSve=conn.prepareStatement("SELECT * FROM radnovrijeme");
        dajImeiPrezime=conn.prepareStatement("SELECT ime,prezime from korisnici where username=?");
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
        ubaciRadnoVrijeme.setInt(5,rw.getDan());
        ubaciRadnoVrijeme.setString(6,rw.getMjesec());
        ubaciRadnoVrijeme.setInt(7,rw.getGodina());
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

}
