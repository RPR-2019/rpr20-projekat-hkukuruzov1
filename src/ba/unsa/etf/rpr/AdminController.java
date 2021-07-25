package ba.unsa.etf.rpr;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class AdminController {
    public ListView lv,lv2;
    private RadnoVrijemeDao dao;
    public List<String> o;
    public TextField tf1,tf2;
    public DatePicker datum;
    public Button tipka;

    @FXML
    public void initialize() throws SQLException {
        dao=RadnoVrijemeDao.getInstance();
        var f=dao.dajSve();
        Collections.reverse(f);
        lv.getItems().addAll(f);
    }
    public void submit(ActionEvent actionEvent) throws SQLException {
        if(tf1.getText()!=null && datum.getValue()!=null){
            tf2.setText(dao.dajTaj(tf1.getText(),datum.getValue().getDayOfMonth(),datum.getValue().getMonth().toString(),datum.getValue().getYear()));
        }
        lv2.getItems().addAll(dao.dajMjesec(tf1.getText(),datum.getValue().getMonth().toString(),datum.getValue().getYear()));
    }
}
