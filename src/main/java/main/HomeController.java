package main;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import root.Quotes;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class HomeController extends MenuController implements Initializable {
    @FXML
    private Text quote;
    @FXML
    private Label time;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generateQuote();
        timeNow();
    }

    private void generateQuote() {
        quote.setText(MenuController.myQuote);
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
}
