package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

import java.time.LocalTime;

public class RadnikController {
    public Button ulaz;
    public Button izlaz;
    public RadioButton rw;
    public RadioButton ow;
    public boolean aktiv;
    @FXML
    public void initialize(){

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
