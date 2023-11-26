package main;
import api.GoogleTranslateAPI;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Translate2 implements Initializable {

    @FXML
    private ChoiceBox<String> inputLangChoiceBox;
    @FXML
    private ChoiceBox<String> outputLangChoiceBox;
    @FXML
    private TextArea inputTextArea, outputTextArea;

    @FXML
    public void onTranslateButtonClick() throws IOException {
        outputTextArea.clear();
        String input = inputTextArea.getText();
            String output = null;
            try {
                if (inputLangChoiceBox.getValue().equals("English")) {
                    output = GoogleTranslateAPI.googleTranslate("en", "vi", input);
                } else {
                    output = GoogleTranslateAPI.googleTranslate("vi", "en", input);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
          String finalOutput = output;
            outputTextArea.setPromptText("Đang dịch...");
          outputTextArea.setText(finalOutput);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inputLangChoiceBox.setItems(FXCollections.observableArrayList("English", "Vietnam"));
        outputLangChoiceBox.setItems(FXCollections.observableArrayList("English", "Vietnam"));
        inputLangChoiceBox.setValue("English");
        outputLangChoiceBox.setValue("Vietnam");
    }
}
