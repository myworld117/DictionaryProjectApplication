package main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

public class SynonymController implements Initializable {
    @FXML
    private TextField searchWord;
    @FXML
    private VBox content;
    @FXML
    private Button resetButton;
    @FXML
    public void onSubmitSearchBtn() {
        SynonymSearchThread synonymSearchThread = new SynonymSearchThread(searchWord, content);
        synonymSearchThread.start();
        content.getChildren().clear();
        content.getChildren().add(new Label("Loading..."));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetButton.setOnAction(event -> {
            content.getChildren().clear();
            searchWord.clear();
        });
    }
}
