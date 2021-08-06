package ba.unsa.etf.rpr.Controller;

import ba.unsa.etf.rpr.Dao;
import ba.unsa.etf.rpr.WorkTime;
import ba.unsa.etf.rpr.WorkPlace;
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

public class WorkTimeController {
    public Button in;
    public Button out;
    public RadioButton rw;
    public RadioButton ow;
    public boolean active;
    public Label time;
    public WorkTime wk =new WorkTime();
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
    public void setController(Controller s){
        c1=s;
    }
    public void startWorking(ActionEvent actionEvent) throws SQLException {
        if (wk.getTypeOfWork() != null && !dao.workTimeCheck(c1.lbl.getText())) {
            wk.setId(c1.lbl.getText());
            wk.setStart(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString());
            wk.setDay(LocalDate.now().getDayOfMonth());
            wk.setMonth(LocalDate.now().getMonth().toString());
            wk.setYear(LocalDate.now().getYear());
            wk.setEnd(null);
            dao.inputTimeNew(wk);
            active = true;
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

    public void endWorking(ActionEvent actionEvent) throws SQLException {
        if (dao.workTimeCheck(c1.lbl.getText())) {
            wk.setEnd(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString());
            wk.setId(c1.lbl.getText());
            if (active) {
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
            dao.inputTime(wk);
            wk =null;
        }
        else{
            if(wk.getStart()==null){
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
                else if(wk.getTypeOfWork()==null){
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
    public void onSite(ActionEvent actionEvent) {
        wk.setTypeOfWork(WorkPlace.ONSITE);
    }
    public void homeWork(ActionEvent actionEvent) {
        wk.setTypeOfWork(WorkPlace.REMOTE);
    }
    public boolean check(WorkTime s){
        if(s.getId()!=null && s.getEnd()==null && s.getStart()!=null && s.getTypeOfWork()!=null)
            return true;
        return false;
    }
    public void logut(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) in.getScene().getWindow();
        stage.close();
        Stage primaryStage=new Stage();
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"),bundle);
        primaryStage.setTitle("Clockify");
        primaryStage.setScene(new Scene(root, 400, 350));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/img/Ikona.png"));
        primaryStage.show();
    }
}
