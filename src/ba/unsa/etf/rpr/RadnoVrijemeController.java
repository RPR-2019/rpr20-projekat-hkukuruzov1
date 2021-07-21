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

import java.sql.SQLException;
import java.time.LocalTime;

public class RadnoVrijemeController {
    public Button ulaz;
    public Button izlaz;
    public RadioButton rw;
    public RadioButton ow;
    public boolean aktiv;
    public Label time;
    public RadnoVrijeme rad=new RadnoVrijeme();
    public Controller c1;
    private RadnoVrijemeDao dao;
    @FXML
    public void initialize() throws SQLException {
        dao=RadnoVrijemeDao.getInstance();
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
    public void postavi(Controller s){
        c1=s;
    }
    public void start(ActionEvent actionEvent) {
        rad.setId(c1.lbl.getText());
        rad.setPocetak(LocalTime.now().toString());
        if(LocalTime.now().getHour()<9) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Obavijest");
            alert.setHeaderText("Obavijest o satnici");
            alert.setContentText("Počeli ste raditi prijevremeno, satnica će vam se uračuniti!");

            alert.showAndWait();
        }
        aktiv=true;
    }

    public void kraj(ActionEvent actionEvent) throws SQLException {
        rad.setKraj(LocalTime.now().toString());
        provjera(rad);
        if(aktiv) {
            if(LocalTime.now().getHour()<16){
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
        dao.ubaciVrijeme(rad);
    }
    public void firma(ActionEvent actionEvent) {
        rad.setVrstaRada(mjestoRada.ONSITE);
    }
        public void kuci(ActionEvent actionEvent) {
        rad.setVrstaRada(mjestoRada.REMOTE);
    }
    public boolean provjera(RadnoVrijeme s){
        if(s.getId()!=null && s.getKraj()!=null && s.getPocetak()!=null && s.getVrstaRada()!=null)
            return true;
        return false;
    }
}
