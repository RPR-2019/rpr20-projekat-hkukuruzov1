package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class FormaController {
    public Button ex,sb;
    public TextField tf1,tf2,tf3;
    public CheckBox cb;
    public PasswordField p1,p2;
    private Dao dao;
    @FXML
    void initialize(){
        dao=Dao.getInstance();
    }
    public void izlaz(ActionEvent actionEvent){
        Stage stage = (Stage) ex.getScene().getWindow();
        stage.close();
    }
    public void submit(ActionEvent actionEvent){
        if(tf1.getText()!=null && tf2.getText()!=null && tf3.getText()!=null){
            if(p1.textProperty().get().equals(p2.textProperty().get())){

            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Upozorenje");
                alert.setHeaderText("Upozorenje o šifri");
                alert.setContentText("Šifre se ne poklapaju!");

                alert.showAndWait();
            }
        }
    }
}
