package api;
import java.io.IOException;


public interface GoogleTranslateAPI {
    String googleTranslate(String langFrom, String langTo, String text) throws IOException;
}

