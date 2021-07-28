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
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Controller {
    public Button login;
    public TextField lbl;
    public PasswordField pass;
    private Dao dao;
    public Label time;
    public MenuItem eng,bs,fr,ab;
    @FXML
    void initialize() throws SQLException {
    dao= Dao.getInstance();
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            time.setText(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
        lbl.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                pass.requestFocus();
            }
        });
        pass.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                login.requestFocus();
                try {
                    actionLogin(null);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
                ResourceBundle bundle = ResourceBundle.getBundle("Translation");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin.fxml"),bundle);
                Parent root=loader.load();
                primaryStage.setTitle("Clockify");
                primaryStage.setScene(new Scene(root, 300, 350));
                primaryStage.setMaximized(true);
                primaryStage.setResizable(false);
                primaryStage.show();
            }
            else{
                //otvori radnik prozor
                Stage stage = (Stage) login.getScene().getWindow();
                // do what you have to do
                stage.close();
                Stage primaryStage=new Stage();
                ResourceBundle bundle = ResourceBundle.getBundle("Translation");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/radnik.fxml"),bundle);
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
    public void engleski(ActionEvent actionEvent){
        Stage stage = (Stage) login.getScene().getWindow();
        Locale.setDefault(new Locale("en", "US"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/glavna.fxml"), ResourceBundle.getBundle("Translation"));
        try {
            stage.setScene(new Scene(loader.load(),400,350));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void bosanski(ActionEvent actionEvent){
        Stage stage = (Stage) login.getScene().getWindow();
        Locale.setDefault(new Locale("bs", "BA"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/glavna.fxml"), ResourceBundle.getBundle("Translation"));
        try {
            stage.setScene(new Scene(loader.load(),400,350));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void francuski(ActionEvent actionEvent){
        Stage stage = (Stage) login.getScene().getWindow();
        Locale.setDefault(new Locale("fr", "FR"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/glavna.fxml"), ResourceBundle.getBundle("Translation"));
        try {
            stage.setScene(new Scene(loader.load(),400,350));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void about(ActionEvent actionEvent) throws IOException {
        Stage primaryStage=new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/about.fxml"),bundle);
        Parent root=loader.load();
        primaryStage.setTitle("About");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root,300,250));
        root.requestFocus();
        primaryStage.show();
    }
}
