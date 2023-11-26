package root;

import java.util.Comparator;

public class SortPattern implements Comparator<Word>{
    @Override
    public int compare(Word a, Word b) {
        return a.getWord_target().compareToIgnoreCase(b.getWord_target());
    }
}
