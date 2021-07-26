package ba.unsa.etf.rpr;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class AdminController {
    public ListView lv,lv2;
    private RadnoVrijemeDao dao;
    public List<String> o;
    public TextField tf1,tf2;
    public DatePicker datum;
    public Button tipka,odoh;
    public Label lb3;
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
        lb3.setText(datum.getValue().getMonth().toString());
        lv2.getItems().clear();
        lv2.getItems().addAll(dao.dajMjesec(tf1.getText(),datum.getValue().getMonth().toString(),datum.getValue().getYear()));
    }
    public void cya(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) tipka.getScene().getWindow();
        // do what you have to do
        stage.close();
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/glavna.fxml"));
        primaryStage.setTitle("Clockify");
        primaryStage.setScene(new Scene(root, 400, 350));
        primaryStage.setMinHeight(275);
        primaryStage.setMinWidth(300);
        primaryStage.show();
    }
}
