package root;

public class Word implements Comparable<Word> {
    private String word_target;
    private String word_explain;

    public Word() {}

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


