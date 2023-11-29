package main;
import api.GoogleTranslateAPI;
import api.VoiceRSS;
import com.voicerss.tts.Languages;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController implements Initializable {

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
        GoogleTranslateAPI gg = new GoogleTranslate();
        try {
            if (inputLangChoiceBox.getValue().equals("English")) {
                output = gg.googleTranslate("en", "vi", input);
            } else {
                output = gg.googleTranslate("vi", "en", input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String finalOutput = output;
        outputTextArea.setPromptText("Đang dịch...");
        outputTextArea.setText(finalOutput);

    }


    @FXML
    void handleFromLangButton(ActionEvent event) throws Exception {
        String fromLang = inputTextArea.getText();
        if (inputLangChoiceBox.getSelectionModel().getSelectedItem() == "Vietnam") {
            FPTTextToSpeech fptTextToSpeech = new FPTTextToSpeech();
            fptTextToSpeech.getAudioUrl(fromLang);
        } else {
            VoiceRSS.speak(fromLang, Languages.English_UnitedStates);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inputLangChoiceBox.setItems(FXCollections.observableArrayList("English", "Vietnam"));
        outputLangChoiceBox.setItems(FXCollections.observableArrayList("English", "Vietnam"));
        inputLangChoiceBox.setValue("English");
        outputLangChoiceBox.setValue("Vietnam");
    }

    @FXML
    void handleToLangButton(ActionEvent event) throws Exception {
        String fromLang = outputTextArea.getText();
        if (inputLangChoiceBox.getSelectionModel().getSelectedItem() == "English") {
            FPTTextToSpeech fptTextToSpeech = new FPTTextToSpeech();
            fptTextToSpeech.getAudioUrl(fromLang);
        } else {
            VoiceRSS.speak(fromLang, Languages.English_UnitedStates);
        }
    }

    public void clearTranslate() {
        inputTextArea.clear();
        outputTextArea.clear();
    }
}
