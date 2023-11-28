    package root;

    import algo.Trie;

    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.FileReader;
    import java.io.FileWriter;
    import java.util.ArrayList;
    import java.util.Collections;

    public class NewDictionary {
        public static final String  NOT_FOUND = "Từ bạn tìm chưa có trong từ điển.";
        private final String PATH = "src\\main\\resources\\vocab\\eng_vie.txt";
        private static final String SPLITTING_PATTERN = "<html>";
        private static ArrayList<Word> vocab = new ArrayList<>();
        private final Trie trie = new Trie();

        public NewDictionary() {
            loadDataFromHTMLFile(PATH, vocab);
            vocab.sort(new SortPattern());
        }

        public String getPATH() {
            return PATH;
        }

        public ArrayList<Word> getVocab() {
            return vocab;
        }

        public Trie getTrie() {
            return trie;
        }
        public void addWord(Word word) {
            trie.add(word.getWord_target());
            vocab.add(word);
            vocab.sort(new SortPattern());
        }

        public void loadDataFromHTMLFile(String path, ArrayList<Word> temp) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(SPLITTING_PATTERN);
                    String word = parts[0].toLowerCase(); // Chuyển đổi về chữ thường
                    trie.add(word);
                    String definition = SPLITTING_PATTERN + parts[1] + SPLITTING_PATTERN;
                    Word wordObj = new Word(word, definition);
                    temp.add(wordObj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String binaryLookUp(String word) {
            vocab.sort(new SortPattern());
            int index = binaryFind(0, vocab.size() - 1, word.toLowerCase()); // Chuyển đổi về chữ thường
            if (index != -1) {
                return vocab.get(index).getWord_explain();
            } else {
                return NOT_FOUND;
            }
        }

        public void exportToFile(String path) {
            try {
                FileWriter fileWriter = new FileWriter(path);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                for (Word word : getVocab()) {
                    bufferedWriter.write(word.getWord_target()+ word.getWord_explain());
                    bufferedWriter.newLine();
                }
                bufferedWriter.close();
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e);
            }
        }

        public void updateWordToFile(Word word, String path) {
            try {
                FileWriter fileWriter = new FileWriter(path, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(word.getWord_target() + word.getWord_explain());
                bufferedWriter.close();
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e);
            }
        }

        public void updateWord(String word, String meaning, String path) {
            try {
                int index = Collections.binarySearch(vocab, new Word(word, null));
                if (index != -1) {
                    vocab.get(index).setWord_explain(meaning);
                    Word newWord = new Word(word, meaning);
                    updateWordToFile(newWord, path);
                }
            } catch (NullPointerException e) {
                System.out.println("Null Exception.");
            }
        }

        private int binaryFind(int start, int end, String word) {
            if (end < start) {
                return -1;
            }
            int mid = start + (end - start) / 2;
            int compare = word.compareTo(vocab.get(mid).getWord_target());
            if (compare == 0) {
                return mid;
            } else if (compare < 0) {
                return binaryFind(start, mid - 1, word);
            } else {
                return binaryFind(mid + 1, end, word);
            }
        }

        public String getWordFormatted(String word, String pronunciation, String wordType, String meaning ) {
            String definition1 = "<html><i>" + word + "  /" + pronunciation
                    + "/</i><br/><ul><li><b><i>" + wordType
                    + "</i></b><ul><li><font color='#cc0000'><b> " + meaning
                    + " </b></font></li></ul><html>";
            return definition1;
        }

        public void removeWord(String word) {
            int index = Collections.binarySearch(vocab, new Word(word, null));
            if (index != -1) {
                vocab.remove(index);
                trie.delete(word);
            }
        }
    }
