package ba.unsa.etf.rpr;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Controller {
    public Button login;
    public TextField lbl;
    public PasswordField pass;
    private KorisnikDao dao;
    public Label time;
    @FXML
    void initialize() throws SQLException {
    dao=KorisnikDao.getInstance();
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            time.setText(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
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
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/admin.fxml"));
                Parent root=loader.load();
                primaryStage.setTitle("Clockify");
                primaryStage.setScene(new Scene(root, 300, 350));
                primaryStage.setMinHeight(300);
                primaryStage.setMinWidth(300);
                primaryStage.show();
            }
            else{
                //otvori radnik prozor
                Stage stage = (Stage) login.getScene().getWindow();
                // do what you have to do
                stage.close();
                Stage primaryStage=new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/radnik.fxml"));
                Parent root=loader.load();
                RadnoVrijemeController novoooo=loader.getController();
                novoooo.postavi(this);
                primaryStage.setTitle("Clockify");
                primaryStage.setScene(new Scene(root, 350, 200));
                primaryStage.setMinHeight(200);
                primaryStage.setMinWidth(350);
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
