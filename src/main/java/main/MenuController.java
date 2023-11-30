package main;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import root.NewDictionary;
import root.Quotes;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private Button synButton;
    @FXML
    private Text quote;
    @FXML
    private Label time;
    @FXML
    private AnchorPane homePane = new AnchorPane();
    @FXML
    private AnchorPane searchPane;
    @FXML
    private AnchorPane translatePane;
    @FXML
    private AnchorPane gamePane;
    protected TranslateController translateController;
    protected SearchController searchController;
    protected AddController addController;
    protected GameController gameController;
    @FXML
    private AnchorPane addPane;
    private static final Quotes quotes = new Quotes();
    static final String myQuote = quotes.getRandomQuote();
    protected static NewDictionary dictionary;
    protected String source = "src\\main\\resources\\vocab\\eng_vie.txt";

    public MenuController() {
        this.dictionary = new NewDictionary();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timeNow();
        generateQuote();
        try {
            FXMLLoader searchLoader = new FXMLLoader(getClass().getResource("/dictionary/search2.fxml"));
            searchPane = searchLoader.load();
            searchController = searchLoader.getController();
            FXMLLoader translateLoader = new FXMLLoader(getClass().getResource("/dictionary/translate2.fxml"));
            translatePane = translateLoader.load();
            translateController = translateLoader.getController();
            FXMLLoader addLoader = new FXMLLoader(getClass().getResource("/dictionary/add.fxml"));
            addPane = addLoader.load();
            addController = addLoader.getController();
            FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/dictionary/game.fxml"));
            gamePane = gameLoader.load();
            gameController = gameLoader.getController();
            homePane = new FXMLLoader(getClass().getResource("/dictionary/home.fxml")).load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addButton.setOnAction(event-> {
            toAddPanel();
        });
        homeButton.setOnAction(event -> {
            toHomePanel();
        });

        synButton.setOnAction(event -> {
            try {
                toSynPanel();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void generateQuote() {
        quote.setText(myQuote);
    }

    private void timeNow() {
        Thread thread = new Thread(()  ->{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a\ndd/MM/yyyy");
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final String currentTime = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    time.setText(currentTime);
                });
            }
        });
        thread.start();
    }

    @FXML
    public void toAddPanel() {
        addController.clear();
        sideContent.getChildren().clear();
        sideContent.getChildren().setAll(addPane);
    }

    public void toSynPanel() throws IOException {
        AnchorPane SynPane = new FXMLLoader(getClass().getResource("/dictionary/Syn.fxml")).load();
        sideContent.getChildren().setAll(SynPane);
    }
    @FXML
    public void toSearchPanel() {
        searchController.clearSearch();
        sideContent.getChildren().setAll(searchPane);
    }

    @FXML
    public void toTranslatePanel() {
        translateController.clearTranslate();
        sideContent.getChildren().setAll(translatePane);
    }

    @FXML
    public void toGamePanel() {
        sideContent.getChildren().clear();
        sideContent.getChildren().setAll(gamePane);
    }

    @FXML
    public void toHomePanel() {
        sideContent.getChildren().clear();
        sideContent.getChildren().setAll(homePane);
    }

    public NewDictionary getDictionary() {
        return dictionary;
    }

}