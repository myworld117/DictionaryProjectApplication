package main;

import algo.Trie;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import root.NewDictionary;
import root.Notification;
import root.Word;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.layout.VBox;
public class SearchController extends MenuController implements Initializable {
    private ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    private Label Header;
    @FXML
    private WebView DefinitionArea;

    @FXML
    private ListView<String> ListWordView;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchWord;
    @FXML
    private Button US;

    @FXML
    private Button UK;

    private Trie trie;

    @FXML
    private Button removeButton;

    @FXML
    private Button editButton;

    @FXML
    private TextArea newMeaningInput;
    @FXML
    private Button updateBtn;
    @FXML
    private TextArea wordTypeInput;
    @FXML
    private AnchorPane EditPane;

    private TextToSpeech textToSpeech = new TextToSpeech();

    public SearchController() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EditPane.setVisible(false);
        trie = dictionary.getTrie();
        startListView();
        searchWord.textProperty().addListener((observableValue, oldValue, newValue) -> {
            updateListView(newValue);
            updateDefinionArea(newValue);
            updateHeader(newValue);
        });
    }

    public void updateHeader(String word) {
        if(dictionary.binaryLookUp(word) == NewDictionary.NOT_FOUND || word =="") {
            Header.setText("");
        }
        else {
            Header.setText(word);
        }
    }
    public void updateDefinionArea(String word) {
        if (word == "" || dictionary.binaryLookUp(word) == NewDictionary.NOT_FOUND) {
            DefinitionArea.getEngine().loadContent("");
        }
         else {
             DefinitionArea.getEngine().loadContent(dictionary.binaryLookUp(word));
        }
    }
    public void startListView() {
        if (searchWord.getText() == "") {
            list.clear();
            list.addAll(NewDictionary.getTmp());
            ListWordView.setItems(list);
        }
    }


    private void updateListView(String prefix) {
        list.clear();
        if (!prefix.isEmpty()) {
            String words = trie.findAllWord(prefix);
            list.addAll(words.split("\n"));
            ListWordView.setItems(list);

        }
    }


    @FXML
    public void handleListViewClick(MouseEvent event) {
        String word = ListWordView.getSelectionModel().getSelectedItem();
        if (word != null) {
            String definition = dictionary.binaryLookUp(word);
            if (definition == "Từ vựng chưa có trong từ điển.") {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Từ bạn tìm chưa tồn tại");
                alert.showAndWait();
            } else {
                searchWord.setText(word);
                Header.setText(word);
                DefinitionArea.getEngine().loadContent(definition, "text/html");
            }
        }
    }


    @FXML
    public void handleSearchButton(ActionEvent event) {
        if (searchWord.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Bạn chưa nhập từ nào");
            alert.showAndWait();
        } else {
            String word = searchWord.getText();
            String definition = dictionary.binaryLookUp(word);
            if (definition != NewDictionary.NOT_FOUND) {
                DefinitionArea.getEngine().loadContent(definition, "text/html");
            } else {
                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmAlert.setContentText("Từ bạn tìm không tồn tại. Bạn có muốn thêm vào từ điển không?");
                confirmAlert.setHeaderText("Cảnh báo");
                ButtonType buttonAdd = new ButtonType("Add");
                ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                confirmAlert.getButtonTypes().setAll(buttonAdd, buttonCancel);

                Optional<ButtonType> result = confirmAlert.showAndWait();

                if (result.isPresent() && result.get() == buttonAdd) {
                    // Hiển thị hộp thoại nhập liệu cho phát âm, nghĩa, và loại từ
                    Dialog<String[]> addDialog = new Dialog<>();
                    addDialog.setTitle("Thêm từ mới");
                    addDialog.setHeaderText(null);
                    // Tạo VBox
                    VBox vbox = new VBox();
                    vbox.setSpacing(20);
                    // Thêm các controls vào VBox
                    TextField pronunciationField = new TextField();
                    pronunciationField.setPromptText("Phát âm");

                    TextField meaningField = new TextField();
                    meaningField.setPromptText("Nghĩa của từ");

                    TextField wordTypeField = new TextField();
                    wordTypeField.setPromptText("Loại từ");

                    vbox.getChildren().addAll(
                            new Label("Phát âm:"), pronunciationField,
                            new Label("Nghĩa của từ:"), meaningField,
                            new Label("Loại từ:"), wordTypeField
                    );


                    addDialog.getDialogPane().setContent(vbox);

                    // Tạo nút "Add" và "Cancel"
                    ButtonType buttonTypeAdd = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
                    ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                    addDialog.getDialogPane().getButtonTypes().addAll(buttonTypeAdd, buttonTypeCancel);

                    // Thiết lập kết quả khi nút "Add" được nhấp
                    addDialog.setResultConverter(dialogButton -> {
                        if (dialogButton == buttonTypeAdd) {
                            String pronunciation = pronunciationField.getText().trim();
                            String meaning = meaningField.getText().trim();
                            String wordType = wordTypeField.getText().trim();
                            return new String[]{pronunciation, meaning, wordType};
                        }
                        return null;
                    });

                    Optional<String[]> resultAdd = addDialog.showAndWait();
                    resultAdd.ifPresent(parts -> {
                        String pronunciation = parts[0];
                        String meaning = parts[1];
                        String wordType = parts[2];
                        String definition1 = dictionary.getWordFormatted(word, pronunciation, wordType, meaning);
                        Word newWord = new Word(word, definition1);
                        dictionary.addWord(newWord);
                        updateListView(word);
                    });
                }
            }
        }
    }



    @FXML
    public void clearSearch() {
        Header.setText("Nghĩa của từ");
        searchWord.clear();
        list.clear();
        DefinitionArea.getEngine().loadContent("");
    }


    @FXML
    public void speakUS() {
        System.out.println("US button clicked");
        // Add these lines for debugging
        System.out.println("Text to speak: " + searchWord.getText());
        if (textToSpeech == null) {
            System.out.println("textToSpeech object is null");
        } else {
            System.out.println("textToSpeech object is not null");
        }
        textToSpeech.speak(searchWord.getText());
    }

    @FXML
    private void handleClickSoundBtn() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
            voice.speak(searchWord.getText());
        } else throw new IllegalStateException("Cannot find voice: kevin16");
    }

    @FXML
    public void handleOnRemoveButton() {
        Notification notification = new Notification();
        notification.forCofirm("Xóa từ", "Bạn có chắc chắn muốn xóa từ này ?");
        dictionary.removeWord(searchWord.getText());
        updateListView(searchWord.getText());
        DefinitionArea.getEngine().loadContent("");
        Header.setText("");
    }

    @FXML
    public void handleClickUpdateBtn(ActionEvent event) {
        if (!searchWord.getText().isEmpty() && !wordTypeInput.getText().isEmpty() && !newMeaningInput.getText().isEmpty()) {
            String word = searchWord.getText();
            String wordType = wordTypeInput.getText();
            String newMeaning = newMeaningInput.getText();
            String path = "src\\main\\resources\\vocab\\dictionaries.txt";
            dictionary.updateWord(word, wordType, newMeaning, path);
        }
        EditPane.setVisible(false);
        DefinitionArea.setVisible(true);
        resetEditPane();
    }
    @FXML
    public void handleEditButton(ActionEvent event) {
        if (!searchWord.getText().isEmpty() && dictionary.binaryLookUp(searchWord.getText()) != NewDictionary.NOT_FOUND) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setContentText("Bạn có muốn sửa từ này không");
            confirmAlert.setHeaderText("Thông báo");
            ButtonType buttonAdd = new ButtonType("Sửa");
            ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmAlert.getButtonTypes().setAll(buttonAdd, buttonCancel);

            Optional<ButtonType> result = confirmAlert.showAndWait();

            if (result.isPresent() && result.get() == buttonAdd) {
                DefinitionArea.setVisible(false);
                EditPane.setVisible(true);
            }
        }
        else if (dictionary.binaryLookUp(searchWord.getText() )== NewDictionary.NOT_FOUND) {
            Notification notification = new Notification();
            notification.forInfo("Thông báo", "Từ bạn tìm không tồn tại");
        } else if (searchWord.getText().isEmpty()) {
            Notification notification = new Notification();
            notification.forWarning("Thông báo", "Bạn chưa nhập từ nào");
        }
    }
    public void  resetEditPane() {
        wordTypeInput.setText("");
        newMeaningInput.setText("");
    }
}
