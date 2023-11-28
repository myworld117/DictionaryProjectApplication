package main;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import root.NewDictionary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private AnchorPane sideContent;
    @FXML
    private Button translateButton;
    @FXML
    private Button addButton;
    @FXML
    private Button gameButton;
    @FXML
    private Button homeButton;
    @FXML
    private AnchorPane homePane;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private AnchorPane translatePane;
    @FXML
    private AnchorPane gamePane;
    protected TranslationController translationController;
    protected Translate2 translate2;
    protected SearchController searchController;
    protected AddController addController;
    protected GameController gameController;
    @FXML
    private AnchorPane addPane;
    protected NewDictionary dictionary;
    protected String source = "src\\main\\resources\\vocab\\eng_vie.txt";

    public MenuController() {
        this.dictionary = new NewDictionary();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader searchLoader = new FXMLLoader(getClass().getResource("/dictionary/search2.fxml"));
            searchPane = searchLoader.load();
            searchController = searchLoader.getController();
            FXMLLoader translateLoader = new FXMLLoader(getClass().getResource("/dictionary/translate2.fxml"));
            translatePane = translateLoader.load();
            //translationController = translateLoader.getController();
            translate2 = translateLoader.getController();
            FXMLLoader addLoader = new FXMLLoader(getClass().getResource("/dictionary/add.fxml"));
            addPane = addLoader.load();
            addController = addLoader.getController();
            FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/dictionary/game.fxml"));
            gamePane = gameLoader.load();
            gameController = gameLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addButton.setOnAction(event-> {
            toAddPanel();
        });
    }

    @FXML
    public void toAddPanel() {
        addController.clear();
        sideContent.getChildren().clear();
        sideContent.getChildren().setAll(addPane);
    }

    @FXML
    public void toSearchPanel() {
        //searchController.clearSearch();
        sideContent.getChildren().clear();
        sideContent.getChildren().setAll(searchPane);
    }

    @FXML
    public void toTranslatePanel() {
        sideContent.getChildren().clear();
        //translationController.clearFromLang();
        sideContent.getChildren().setAll(translatePane);
    }

    @FXML
    public void toGamePanel() {
        sideContent.getChildren().clear();
        sideContent.getChildren().setAll(gamePane);
    }

    @FXML
    public void toHomePanel() {

    }

    public NewDictionary getDictionary() {
        return dictionary;
    }

}