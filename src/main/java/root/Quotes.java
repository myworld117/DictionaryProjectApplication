package root;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Quotes {
    private String SOURCE = "src\\main\\resources\\vocab\\quotes.txt";
    private List<String> quotes;

    public Quotes() {
        quotes = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(SOURCE))) {
            while(sc.hasNextLine()) quotes.add(sc.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getRandomQuote(){
        return quotes.get(new Random().nextInt(quotes.size()));
    }
}
