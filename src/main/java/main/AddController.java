package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import root.NewDictionary;
import root.SortPattern;
import root.Word;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class AddController extends MenuController implements Initializable {

    @FXML
    private TextField meaningArea;

    @FXML
    private TextField pronunArea;

    @FXML
    private TextField wordArea;

    @FXML
    private TextField wordtypeArea;
    @FXML
    private Button confirmAddButton;

    public AddController() {
    }


    public void clear() {
        meaningArea.clear();
        pronunArea.clear();
        wordArea.clear();
        wordtypeArea.clear();
    }

    public Word getAddWord() {
        if (!meaningArea.getText().isEmpty() &&
                !pronunArea.getText().isEmpty() &&
                !wordtypeArea.getText().isEmpty() && !wordArea.getText().isEmpty()) {
            String word = wordArea.getText().toLowerCase();
            String meaning = meaningArea.getText();
            String wordType = wordtypeArea.getText();
            String pronunciation = pronunArea.getText();
            String definition = dictionary.getWordFormatted(word, pronunciation, wordType, meaning);
            Word newWord = new Word(word, definition);

            return newWord;
        } else return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    void handleAddButton() {
        Word word = getAddWord();
        if (word != null && dictionary.binaryLookUp(word.getWord_target()) == NewDictionary.NOT_FOUND) {
            dictionary.addWord(word);
            dictionary.addWordToFile(word, source);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            Image image = new Image(getClass().getResourceAsStream("/image/check.png"));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            alert.setGraphic(imageView);
            alert.setContentText("Bạn đã thêm từ thành công");
            alert.setHeaderText("Chúc mừng");
            alert.showAndWait();
        } else if (dictionary.binaryLookUp(word.getWord_target()) != NewDictionary.NOT_FOUND) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.getDialogPane().setContentText("Thông báo");
            alert.setHeaderText("Oops");
            alert.setContentText("Từ này hiện đã có trong từ điển");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Không thể thêm từ. Hãy điền đầy đủ thông tin.");
            alert.showAndWait();
        }
    }

}