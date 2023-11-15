package root;

import java.util.ArrayList;

public class Dictionary {
    protected ArrayList<Word> wordArrayList  ;
    public Dictionary() {
       this.wordArrayList = new ArrayList<Word>();
    }

    public ArrayList<Word> getDictionary() {
        return wordArrayList;
    }
    public void addWord(String word_target, String word_explain) {
        Word newWord = new Word(word_target, word_explain);
        wordArrayList.add(newWord);
    }

    public void remove(int index) {
        wordArrayList.remove(index);
    }

}
