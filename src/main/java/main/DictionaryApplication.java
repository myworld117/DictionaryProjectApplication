package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DictionaryApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load((getClass().getResource("/dictionary/test.fxml")));
        Image icon = new Image("image/logo.png");
        primaryStage.setTitle("NetDict");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 868, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
