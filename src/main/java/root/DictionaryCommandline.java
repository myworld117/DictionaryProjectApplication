package root;

import java.util.Scanner;

public class DictionaryCommandline {
    private DictionaryManagement DictionaryManage = new DictionaryManagement();
    public void showAllWords() {
        StringBuilder output = new StringBuilder();
        output.append(String.format("%-6s%c %-15s%c %-20s%n", "No", '|', "English", '|', "Vietnamese"));
        for (int i = 0; i < DictionaryManage.getDictionary().size(); i++) {
            output.append(String.format("%-6d%c %-15s%c %-15s%n", i + 1, '|', DictionaryManage.getDictionary().get(i).getWord_target(), '|', DictionaryManage.getDictionary().get(i).getWord_explain()));
        }
        System.out.println(output.toString());
    }

    public void dictionaryBasic() {
        DictionaryManage.insertFromCommandline();
        this.showAllWords();
    }

    public void dictionaryAdvanced() {
        while (true) {
            String[] menuOptions = {
                    "Exit",
                    "Add",
                    "Remove",
                    "Update",
                    "Display",
                    "Lookup",
                    "Search",
                    "Import from file",
                    "Export to file"
            };

            System.out.println("Welcome to Advanced Dictionary:");
            for (int i = 0; i < menuOptions.length; i++) {
                System.out.println("[" + i + "] " + menuOptions[i]);
            }
            System.out.print("Your action: ");
            Scanner scanner = new Scanner(System.in);
            String action = scanner.nextLine();
            switch (action) {
                case "0":
                    System.out.println("Exited");
                    return;
                case "1":
                    // insert from commandline "Add"
                    DictionaryManage.insertFromCommandline();
                    break;

                case "2":
                    // "remove"
                    System.out.println("Enter word to remove");
                    String word2 = scanner.nextLine();
                    DictionaryManage.deleteFromDict(word2);
                    break;
                case "3":
                    System.out.println("Enter word to change");
                    String ex_word = scanner.nextLine();
                    System.out.println("New meaning:");
                    String new_meaning = scanner.nextLine();
                    DictionaryManage.updateWord(ex_word, new_meaning);
                    break;


                case "4":
                    // Display all words
                    showAllWords();
                    break;

                case "5":
                    //lookup
                    System.out.println("Enter word to lookup");
                    String word5 = scanner.nextLine();
                    System.out.println(DictionaryManage.dictionaryLookupPlus(word5));
                    break;
                case "6":
                    //search
                    System.out.println("Enter word to search");
                    String word6 = scanner.nextLine();
                    System.out.println(DictionaryManage.dictionarySearcher(word6));
                    break;
                case "7" :
                    //import from file
                    System.out.println("Name of the file:");
                    String word7 = scanner.nextLine();
                    DictionaryManage.insertFromFile("src\\" + word7);
                    System.out.println("insert successful");
                    break;

                case "8" :
                    //export to file
                    System.out.println("Exporting  to:");
                    String path = scanner.nextLine();
                    DictionaryManage.exportToFile(path);
                    break;

                default:
                    System.out.println("Invalid action. Please enter a number between 0 and 9.");
                    break;
            }
        }
    }
}

