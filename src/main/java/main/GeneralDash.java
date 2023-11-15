package main;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class GeneralDash {

    @FXML
    private JFXButton SearchButton;

    @FXML
    private AnchorPane sideContent;

    @FXML
    private JFXButton translateButton;
    @FXML
    private AnchorPane translatePanel;
    @FXML
    private AnchorPane searchPane;
    @FXML
    void toSearchButton(ActionEvent event) {

    }

    private void setMainContent(AnchorPane anchorPane) {
        sideContent.getChildren().setAll(anchorPane);
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
    void toTranslatePanel(ActionEvent event) {
        translateButton.getStyleClass().add("active");
        setMainContent(translatePanel);
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
            translatePanel = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("setting.fxml"));
//            settingPane = loader.load();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}

