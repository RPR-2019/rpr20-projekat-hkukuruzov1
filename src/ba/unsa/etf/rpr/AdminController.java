package ba.unsa.etf.rpr;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class AdminController {
    public ListView lv;
    private RadnoVrijemeDao dao;
    public List<String> o;
    @FXML
    public void initialize() throws SQLException {
        dao=RadnoVrijemeDao.getInstance();
        var f=dao.dajSve();
        Collections.reverse(f);
        lv.getItems().addAll(f);
    }
}
