package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class Controller {
    public Button login;
    public TextField lbl;
    public PasswordField pass;
    private KorisnikDao dao;
    @FXML
    void initialize() throws SQLException {
    dao=KorisnikDao.getInstance();
    }
    public void actionExit(ActionEvent actionEvent) {
        System.exit(0);
    }
    public void actionLogin(ActionEvent actionEvent) throws SQLException {
        if(dao.daLiPostojiKorisnik(lbl.getText(),pass.getText()))
        {
            Korisnik k= dao.dajKorisnika(lbl.getText(),pass.getText());
            if(dao.daLiJeAdmin(k.getUsername(),k.getPassword())){
               //otvori admin prozor
            }
            else{
                //otvori radnik prozor
            }
        }
        else{
            lbl.textProperty().setValue("");
            pass.textProperty().setValue("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Greška prilikom autentikacije!");
            alert.setContentText("Netačni podaci!");

            alert.showAndWait();
        }
    }
}
