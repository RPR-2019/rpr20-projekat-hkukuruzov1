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
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController {
    public ListView lv,lv2;
    private Dao dao;
    public List<String> o;
    public TextField tf1,tf2,tfdlt;
    public DatePicker datum;
    public Button tipka,odoh,add,dlt,iz;
    public Label lb3;
    @FXML
    public void initialize() throws SQLException {
        dao=Dao.getInstance();
        var f=dao.dajSve();
        Collections.reverse(f);
        lv.getItems().addAll(f);
    }
    public void submit(ActionEvent actionEvent) throws SQLException {
        if (tf1.getText() != null && datum.getValue() != null) {
            tf2.setText(dao.dajTaj(tf1.getText(), datum.getValue().getDayOfMonth(), datum.getValue().getMonth().toString(), datum.getValue().getYear()));

            lb3.setText(datum.getValue().getMonth().toString());
            lv2.getItems().clear();
            lv2.getItems().addAll(dao.dajMjesec(tf1.getText(), datum.getValue().getMonth().toString(), datum.getValue().getYear()));
        }
    }
    public void cya(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) tipka.getScene().getWindow();
        // do what you have to do
        stage.close();
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/glavna.fxml"));
        primaryStage.setTitle("Clockify");
        primaryStage.setScene(new Scene(root, 400, 350));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void brisi(ActionEvent actionEvent) throws IOException, SQLException {
        if(tfdlt.getText()!=null){
            dao.brisanje(tfdlt.getText());
            var f=dao.dajSve();
            Collections.reverse(f);
            lv.getItems().clear();
            lv.getItems().addAll(f);
        }
    }
    public void dodaj(ActionEvent actionEvent) throws IOException {
        Stage primaryStage=new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/forma.fxml"),bundle);
        Parent root=loader.load();
        primaryStage.setTitle("Clockify");
        primaryStage.setScene(new Scene(root, 400, 350));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void izvjestaj(ActionEvent actionEvent){
        try {
            new Izvjestaj().showReport(dao.getConnection());
        } catch (JRException e1) {
            e1.printStackTrace();
        }

    }
}
