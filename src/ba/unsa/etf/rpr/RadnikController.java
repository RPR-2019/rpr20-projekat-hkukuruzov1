package ba.unsa.etf.rpr;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.time.LocalTime;

public class RadnikController {
    public Button ulaz;
    public Button izlaz;
    public RadioButton rw;
    public RadioButton ow;
    public boolean aktiv;
    public Label time;
    @FXML
    public void initialize() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            String s;
            s=currentTime.getHour()<10?"0":"";
            time.setText(s +currentTime.getHour()+ ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void start(ActionEvent actionEvent) {
        LocalTime s=LocalTime.now();
        if(s.getHour()<9) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Obavijest");
            alert.setHeaderText("Obavijest o satnici");
            alert.setContentText("Počeli ste raditi prijevremeno, satnica će vam se uračuniti!");

            alert.showAndWait();
        }
        aktiv=true;
    }

    public void kraj(ActionEvent actionEvent) {
        if(aktiv) {
            LocalTime s=LocalTime.now();
            if(s.getHour()<16){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Upozorenje");
                alert.setHeaderText("Upozorenje o satnici");
                alert.setContentText("Odjavili ste se prije kraja radnog vremena, satnica će vam biti zabilježena!");

                alert.showAndWait();
            }
                else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Obavijest");
                alert.setHeaderText("Obavijest o satnici");
                alert.setContentText("Radite prekovremeno, satnica će vam se uračuniti!");

                alert.showAndWait();
            }
        }
    }
}
