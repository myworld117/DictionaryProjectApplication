package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class generalController {

    @FXML
    private Button AntonymButton;

    @FXML
    private AnchorPane mainScence;

    @FXML
    private VBox sea;

    @FXML
    private Button searchButton;

    @FXML
    private Button settingButton;

    @FXML
    private Button synonymButton;

    @FXML
    private Button translateButton;

    private AnchorPane searchPane;
    private AnchorPane translatePane;
    private AnchorPane sysnonymPane;
    private AnchorPane antonymPane;


    private void setMainContent(AnchorPane anchorPane) {
        mainScence.getChildren().setAll(anchorPane);
    }

    @FXML
    void showSettingPane(ActionEvent event) {

    }

    @FXML
    void toAntonymButton(ActionEvent event) {

    }

    @FXML
    void toSearchPane(ActionEvent event) {

    }

    @FXML
    void toSynonymButton(ActionEvent event) {

    }

    @FXML
    void toTranslatePane(ActionEvent event) {
        translateButton.getStyleClass().add("active");
        setMainContent(translatePane);
    }

    public void initialize() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dictionary/search.fxml"));
//            searchPane = loader.load();
//            searchController = loader.getController();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("history.fxml"));
//            historyPane = loader.load();
//            historyController = loader.getController();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookmark.fxml"));
//            bookmarkPane = loader.load();
//            bookmarkController = loader.getController();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dictionary/translation.fxml"));
            translatePane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("setting.fxml"));
//            settingPane = loader.load();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        searchButton.getStyleClass().add("active");

    }
}
