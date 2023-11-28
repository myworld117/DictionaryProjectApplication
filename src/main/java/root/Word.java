package root;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Word implements Comparable<Word> {
    private String word_target;
    private String word_explain;

    private static final String WORDS_FILE_PATH = "src\\main\\resources\\vocab\\words.txt";

    private List<String> words;

    public Word() {
        words = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(WORDS_FILE_PATH))) {
            while(sc.hasNextLine()) words.add(sc.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getRandomWord(){
        return words.get(new Random().nextInt(words.size())).toUpperCase();
    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }
    public String getWord_explain() {
        return word_explain;
    }

    public String getWord_target() {
        return word_target;
    }
    public void setWord_target() {this.word_target = word_target;}
    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    @Override
    public int compareTo(Word o) {
        return this.word_target.compareToIgnoreCase(o.word_target);
    }
}


