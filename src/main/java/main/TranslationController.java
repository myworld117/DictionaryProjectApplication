package main;

import api.GoogleTranslateAPI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class TranslationController {

    @FXML
    private Button TranslateButton;

    @FXML
    private TextArea fromLang;

    @FXML
    private TextField toLang;

    @FXML
    void translateTo(ActionEvent event) throws IOException {
        if (!Objects.equals(fromLang.getText(), "")) {
            toLang.setText(GoogleTranslateAPI.googleTranslate("en", "vi", fromLang.getText()));
        }
    }

}
