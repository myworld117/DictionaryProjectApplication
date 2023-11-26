package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DictionaryApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
      //  loadSettingFromFile();
        Parent root = FXMLLoader.load((getClass().getResource("/dictionary/test.fxml")));
        primaryStage.setTitle("Dictionary");
        primaryStage.setScene(new Scene(root, 900, 700));
        primaryStage.show();
      //  primaryStage.setOnCloseRequest(event -> saveSettingToFile());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
