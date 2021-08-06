package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"), bundle);
        primaryStage.setTitle("Clockify");
        Scene scena=new Scene(root, 400, 350);
        primaryStage.setScene(scena);
        primaryStage.setResizable(false);
        scena.getStylesheets().add(getClass().getResource("/css/font.css").toExternalForm());
        primaryStage.getIcons().add(new Image("/img/Ikona.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
