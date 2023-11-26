package root;

import algo.Trie;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    private final String in_path = "src\\main\\java\\root\\dictionaries.txt";
    private final String out_path = "src\\main\\java\\root\\out.txt";
    public DictionaryManagement() {
        this.insertFromFile(in_path);
    }
    public void insertFromCommandline() {
        Scanner inputForInt = new Scanner(System.in);
        int nums = inputForInt.nextInt();
        Scanner inputForString = new Scanner(System.in);
        while (0 < nums) {
            String line = inputForString.nextLine();
            String[] rows = line.split("\t");
            String word_target = rows[0];
            String word_explain = rows[1];
            if (binaryFind(word_target) == -1) {
                System.out.println("This word has already stored in dictionary");
                return;
            } else {
                super.addWord(word_target, word_explain);
                nums--;
            }
        }
    }

    public void insertFromFile(String filePath) {
        String line;
        String splitBy = "\t";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                String[] row = line.split(splitBy);
                row[0] = row[0].toLowerCase();
                super.addWord(row[0], row[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        super.addWord("hello", "Xin chào.");
        super.addWord("java", "phim nhật.");
    }

    public String dictionaryLookup(String word_find) {
        word_find = word_find.toLowerCase();
        for (Word word : super.getDictionary()) {
            String res = word.getWord_target().trim().toLowerCase();
            if (res.equals(word_find)) {
                return word.getWord_explain();
            }
        }
        return "NOT FOUND WORD \n";
    }

    public ArrayList<String> dictionarySearcher(String prefix) {
        ArrayList<Word> arrayList = getDictionary();
        Trie wordsWithPrefix = new Trie();
        for (Word word : arrayList) {
            String key = word.getWord_target().toLowerCase();
            wordsWithPrefix.add(key);
        }
        return new ArrayList<>(Arrays.asList(wordsWithPrefix.findAllWord(prefix).split("\n")));
    }

    public String dictionaryLookupPlus(String word_find) {
        word_find = word_find.toLowerCase();
        int index = binaryFind(word_find);
        if (index != -1 && index < getDictionary().size()) {
            return getDictionary().get(index).getWord_explain();
        } else return "NOT FOUND WORD \n";
    }
        public void deleteFromDict(String target){
            target = target.toLowerCase();
            for (int i = 0; i < getDictionary().size(); i++) {
                  String delete_key = getDictionary().get(i).getWord_target().toLowerCase();
                if (delete_key.equals(target)) super.remove(i);
            }
        }

    public int binaryFind (String keyWord){
        try {
       //     this.wordArrayList.sort(new ComparatorWord());
            int left = 0;
            int right = getDictionary().size() - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int res = getDictionary().get(mid).getWord_target().compareTo(keyWord);
                if (res == 0) return mid;
                if (res < 0) left = mid + 1;
                else right = mid - 1;
            }
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
        return -1;
    }


    public void exportToFile(String path) {
        try {
            FileWriter fileWriter = new FileWriter(out_path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : getDictionary()) {
                bufferedWriter.write("|" + word.getWord_target()+ "\n" + word.getWord_explain());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }

    public void updateWord(String word, String meaning) {
        try {
            int index = binaryFind(word);
            getDictionary().get(index).setWord_explain(meaning);
            exportToFile(out_path);
        } catch (NullPointerException e) {
            System.out.println("Null Exception.");
        }
    }

    }
