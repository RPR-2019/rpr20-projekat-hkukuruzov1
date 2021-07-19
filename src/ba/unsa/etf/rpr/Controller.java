package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
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
    public void actionLogin(ActionEvent actionEvent) throws SQLException, IOException {
        if(dao.daLiPostojiKorisnik(lbl.getText(),pass.getText()))
        {
            Korisnik k= dao.dajKorisnika(lbl.getText(),pass.getText());
            if(dao.daLiJeAdmin(k.getUsername(),k.getPassword())){
               //otvori admin prozor
                Stage stage = (Stage) login.getScene().getWindow();
                // do what you have to do
                stage.close();
                Stage primaryStage=new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
                primaryStage.setTitle("Clockify");
                primaryStage.setScene(new Scene(root, 400, 350));
                primaryStage.setMinHeight(275);
                primaryStage.setMinWidth(300);
                primaryStage.show();
            }
            else{
                //otvori radnik prozor
                Stage stage = (Stage) login.getScene().getWindow();
                // do what you have to do
                stage.close();
                Stage primaryStage=new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/radnik.fxml"));
                primaryStage.setTitle("Clockify");
                primaryStage.setScene(new Scene(root, 400, 350));
                primaryStage.setMinHeight(275);
                primaryStage.setMinWidth(300);
                primaryStage.show();
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
