package algo;

import java.util.ArrayList;

public class TrieNode {
    private static final int alphabetSize = 30;
    private ArrayList<TrieNode> children = new ArrayList<>(alphabetSize);
    private boolean endOfWord;
    private String meaningOfWord;

    public TrieNode() {
        for (int i = 0; i < alphabetSize; i++) {
            children.add(null);
        }
        endOfWord = false;
        meaningOfWord = "";
    }

    public ArrayList<TrieNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<TrieNode> children) {
        this.children = children;
    }

    public boolean isEndOfWord() {
        return endOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }

    public String getWord() {
        return meaningOfWord;
    }

    public void setWord(String meaning) {
        this.meaningOfWord = meaning;
    }
}