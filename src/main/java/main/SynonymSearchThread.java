package main;

import api.SynonymAPI;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SynonymSearchThread extends Thread {
    private final TextField searchWord;
    private final VBox content;

    public SynonymSearchThread(TextField searchWord, VBox content) {
        this.searchWord = searchWord;
        this.content = content;
    }

    @Override
    public void run() {
        JSONObject list;
        try {
            list = SynonymAPI.getSynonyms(searchWord.getText().toLowerCase());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        JSONObject finalList = list;
        Platform.runLater(() -> {
            content.getChildren().clear();
            try {
                takeData(finalList, "synonyms");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void takeData(JSONObject list, String type) throws JSONException {
        JSONArray wordlist = list.getJSONArray(type);
        if (wordlist.length() == 0) return;
        TextFlow wordlistBox = new TextFlow();
        content.getChildren().add(new Label(type));
        content.getChildren().add(wordlistBox);
        for (int i = 0; i < wordlist.length(); i++) {
            String v = wordlist.getString(i);
            Button wordItem = new Button(v);
            wordlistBox.getChildren().add(wordItem);
        }
    }
}
