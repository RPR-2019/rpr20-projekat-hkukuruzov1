package ba.unsa.etf.rpr.Controller;

import ba.unsa.etf.rpr.Dao;
import ba.unsa.etf.rpr.RadnoVrijeme;
import ba.unsa.etf.rpr.mjestoRada;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class RadnoVrijemeController {
    public Button ulaz;
    public Button izlaz;
    public RadioButton rw;
    public RadioButton ow;
    public boolean aktiv;
    public Label time;
    public RadnoVrijeme rad=new RadnoVrijeme();
    public Controller c1;
    private Dao dao;
    @FXML
    public void initialize() throws SQLException {
        dao=Dao.getInstance();
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            time.setText(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    public void postavi(Controller s){
        c1=s;
    }
    public void start(ActionEvent actionEvent) throws SQLException {
        if (rad.getVrstaRada() != null && !dao.imaLiNule(c1.lbl.getText())) {
            rad.setId(c1.lbl.getText());
            rad.setPocetak(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString());
            rad.setDan(LocalDate.now().getDayOfMonth());
            rad.setMjesec(LocalDate.now().getMonth().toString());
            rad.setGodina(LocalDate.now().getYear());
            rad.setKraj(null);
            dao.ubaciVrijeme1(rad);
            aktiv = true;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            ResourceBundle rb = ResourceBundle.getBundle("Translation", Locale.getDefault());
            alert.setTitle(rb.getString("Upozorenje"));
            alert.setHeaderText(rb.getString("duga"));
            alert.setContentText(rb.getString("duga2"));
            alert.initModality(Modality.APPLICATION_MODAL);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/img/Ikona.png").toString()));
            alert.showAndWait();
        }
    }

    public void kraj(ActionEvent actionEvent) throws SQLException {
        if (dao.imaLiNule(c1.lbl.getText())) {
            rad.setKraj(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString());
            rad.setId(c1.lbl.getText());
            if (aktiv) {
                if (LocalTime.now().getHour() < 16) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    ResourceBundle rb = ResourceBundle.getBundle("Translation", Locale.getDefault());
                    alert.setTitle(rb.getString("Upozorenje"));
                    alert.setHeaderText(rb.getString("uos"));
                    alert.setContentText(rb.getString("preduga"));
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image(this.getClass().getResource("/img/Ikona.png").toString()));
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.showAndWait();
                }
            }
            dao.ubaciVrijeme(rad);
            rad=null;
        }
        else{
            if(rad.getPocetak()==null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                ResourceBundle rb = ResourceBundle.getBundle("Translation", Locale.getDefault());
                alert.setTitle(rb.getString("Upozorenje"));
                alert.setHeaderText(rb.getString("uos"));
                alert.setContentText(rb.getString("rad2"));
                alert.initModality(Modality.APPLICATION_MODAL);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("/img/Ikona.png").toString()));
                alert.showAndWait();
            }
                else if(rad.getVrstaRada()==null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                ResourceBundle rb = ResourceBundle.getBundle("Translation", Locale.getDefault());
                alert.setTitle(rb.getString("Upozorenje"));
                alert.setHeaderText(rb.getString("uovr"));
                alert.setContentText(rb.getString("movr"));
                alert.initModality(Modality.APPLICATION_MODAL);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(this.getClass().getResource("/img/Ikona.png").toString()));
                alert.showAndWait();
            }
        }
    }
    public void firma(ActionEvent actionEvent) {
        rad.setVrstaRada(mjestoRada.ONSITE);
    }
        public void kuci(ActionEvent actionEvent) {
        rad.setVrstaRada(mjestoRada.REMOTE);
    }
    public boolean provjera(RadnoVrijeme s){
        if(s.getId()!=null && s.getKraj()==null && s.getPocetak()!=null && s.getVrstaRada()!=null)
            return true;
        return false;
    }
    public void cya(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ulaz.getScene().getWindow();
        stage.close();
        Stage primaryStage=new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/glavna.fxml"),bundle);
        primaryStage.setTitle("Clockify");
        primaryStage.setScene(new Scene(root, 400, 350));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/img/Ikona.png"));
        primaryStage.show();
    }
}
