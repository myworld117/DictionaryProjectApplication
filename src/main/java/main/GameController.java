package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import root.Word;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GameController {
    @FXML
    private ImageView imageView;
    @FXML
    private Text text;
    @FXML
    private Pane buttons;
    @FXML
    private Text result;
    @FXML
    private Text realWord;
    @FXML
    private Text mistakeText;
    @FXML
    private Text hintText;

    private int mistakes;
    private int correct;
    private final Word word = new Word();
    private String myWord;
    private List<String> myLetters;
    private List<String> answer;

    Image image1 = new Image(getClass().getResourceAsStream("/meme/1.png"));
    Image image2 = new Image(getClass().getResourceAsStream("/meme/2.png"));
    Image image3 = new Image(getClass().getResourceAsStream("/meme/3.png"));
    Image image4 = new Image(getClass().getResourceAsStream("/meme/4.png"));
    Image image5 = new Image(getClass().getResourceAsStream("/meme/5.png"));
    Image image6 = new Image(getClass().getResourceAsStream("/meme/6.png"));
    Image image7 = new Image(getClass().getResourceAsStream("/meme/7.png"));
    Image image8 = new Image(getClass().getResourceAsStream("/meme/8.png"));
    Image image9 = new Image(getClass().getResourceAsStream("/meme/9.png"));


    public GameController() throws FileNotFoundException {
    }

    public void initialize() {
        mistakes=0;
        correct=0;
        imageView.setImage(image1);
        myWord = word.getRandomWord();
        myLetters = Arrays.asList(myWord.split(""));
        answer = Arrays.asList(new String[myLetters.size()*2]);
        for(int i=0; i<myLetters.size()*2; i++){
            if(i%2==0){
                answer.set(i, "_");
            }else{
                answer.set(i, " ");
            }
        }
        String res = String.join("", answer);
        text.setText(res);
        mistakeText.setText("");
        result.setText("");
        realWord.setText("");
        hintText.setText("");
        buttons.setDisable(false);
    }


    public void onClick(ActionEvent event){
        String letter = ((Button)event.getSource()).getText();
        ((Button) event.getSource()).setDisable(true);
        if(myLetters.contains(letter)){
            for (int i = 0; i < myLetters.size(); i++) {
                if (Objects.equals(myLetters.get(i), letter)) {
                    correct++;
                    answer.set(i * 2, letter);
                }
            }
            String res = String.join("", answer);
            text.setText(res);
            if(correct==myWord.length()){
                imageView.setImage(image1);
                result.setText("You Win!");
                realWord.setText("The actual word was " + myWord);
                buttons.setDisable(true);
            }
        }else{
            mistakes++;
            mistakeText.setText(String.format("You made %d/8 mistakes", mistakes));
            setImageBasedOnMistakes();
        }

    }


    public void setImageBasedOnMistakes() {
        switch(mistakes) {
            case 1: imageView.setImage(image2); break;
            case 2: imageView.setImage(image3); break;
            case 3: imageView.setImage(image4); break;
            case 4: imageView.setImage(image5); break;
            case 5: imageView.setImage(image6); break;
            case 6: imageView.setImage(image7);
                    hint();
                    break;
            case 7: imageView.setImage(image8);
                    hint();
                    break;
            case 8: imageView.setImage(image9);
                    result.setText("You Lose!");
                    realWord.setText("The actual word was " + myWord);
                    buttons.setDisable(true);
                    break;
            default: imageView.setImage(image1); break;
        }
    }

    public void hint() {
        for(int i=0; i<28; i++){
            Button b = (Button) buttons.getChildren().get(i);
            String letter = b.getText();
            if (myLetters.contains(letter) && !b.isDisabled()) {
                hintText.setText("Hint: " + letter);
            }
        }
    }
    public void newGame(){
        for(int i=0; i<28; i++){
            buttons.getChildren().get(i).setDisable(false);
        }
        initialize();
    }
}